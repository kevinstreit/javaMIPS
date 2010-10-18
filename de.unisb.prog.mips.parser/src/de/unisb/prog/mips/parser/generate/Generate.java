package de.unisb.prog.mips.parser.generate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.PolymorphicDispatcher;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelAlreadyDefinedException;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Offset;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.assembler.generators.InstructionGenerator;
import de.unisb.prog.mips.assembler.generators.OperandInstance;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.parser.mips.Addr;
import de.unisb.prog.mips.parser.mips.Align;
import de.unisb.prog.mips.parser.mips.Ascii;
import de.unisb.prog.mips.parser.mips.Asciiz;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.parser.mips.Byte;
import de.unisb.prog.mips.parser.mips.Const;
import de.unisb.prog.mips.parser.mips.DataDecl;
import de.unisb.prog.mips.parser.mips.DataItem;
import de.unisb.prog.mips.parser.mips.DataSegment;
import de.unisb.prog.mips.parser.mips.Expression;
import de.unisb.prog.mips.parser.mips.Extern;
import de.unisb.prog.mips.parser.mips.Global;
import de.unisb.prog.mips.parser.mips.Half;
import de.unisb.prog.mips.parser.mips.Insn;
import de.unisb.prog.mips.parser.mips.LabelDef;
import de.unisb.prog.mips.parser.mips.Minus;
import de.unisb.prog.mips.parser.mips.Mul;
import de.unisb.prog.mips.parser.mips.Plus;
import de.unisb.prog.mips.parser.mips.SetAt;
import de.unisb.prog.mips.parser.mips.Shl;
import de.unisb.prog.mips.parser.mips.Shr;
import de.unisb.prog.mips.parser.mips.Shra;
import de.unisb.prog.mips.parser.mips.Space;
import de.unisb.prog.mips.parser.mips.TextItem;
import de.unisb.prog.mips.parser.mips.TextSegment;
import de.unisb.prog.mips.parser.mips.Word;
import de.unisb.prog.mips.parser.util.EObjectPosition;
import de.unisb.prog.mips.simulator.Type;
import de.unisb.prog.mips.util.Option;

public class Generate {

	private final Assembly assembly;

	private PolymorphicDispatcher<Element> elementDispatcher =
		PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this);

	private PolymorphicDispatcher<Void> voidDispatcher =
		PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this);

	private Generators generators = Generators.getInstance();

	public Generate(Assembly assembly) {
		this.assembly = assembly;
	}

	public Void generate(Asm asm) {
		for (EObject o : asm.getElem())
			voidDispatcher.invoke(o);
		return null;
	}

	public void generate(DataSegment s) {
		for (EObject i : s.getItems())
			voidDispatcher.invoke(i, assembly.getData());
	}

	public void generate(Global global, Segment seg) {
		assembly.makeGlobal(global.getLabel().getName());
	}

	public void generate(Extern extern, Segment seg) {
		// this is only here to make the xtext resolver happy ;)
	}

	public void generate(SetAt dummy, Segment seg) {
	}

	private void labelElement(LabelDef label, EObject item, Element elm) {
		Position pos = new EObjectPosition(item);
		if (label != null) {
			String name = label.getLabel().getName();
			elm.setLabel(name);
			try {
				assembly.defineLabel(elm);
			} catch (LabelAlreadyDefinedException e) {
				assembly.getReporter().error(String.format("label \"%s\" is multiply defined", name), pos);
			}
		}
		elm.setPosition(pos);
	}

	public void generate(DataItem item, Segment s) {
		Element elm = elementDispatcher.invoke(item.getData(), assembly.getData());
		labelElement(item.getLabel(), item, elm);
	}

	public Element generate(DataDecl decl, Segment s) {
		return elementDispatcher.invoke(decl.getItem(), s);
	}

	public Element generate(Asciiz str, Segment seg) {
		return assembly.getData().string(str.getVal(), true);
	}

	public Element generate(Ascii str, Segment seg) {
		return assembly.getData().string(str.getVal(), false);
	}

	public Element generate(Word w, Segment seg) {
		List<Addr> addrs = w.getVals().getVals();
		List<Expr> exprs = new ArrayList<Expr>(addrs.size());

		for (Addr a : w.getVals().getVals()) {
			Offset off = generate(a);
			exprs.add(Expressions.absolute(off));
		}
		return seg.word(exprs);
	}

	public Element generate(Half w, Segment s) {
		return addData(w.getVals().getVals(), Type.HALF);
	}

	public Element generate(Byte w, Segment s) {
		return addData(w.getVals().getVals(), Type.BYTE);
	}

	private Element addData(List<Integer> vals, Type ty) {
		List<Expr> exprs = new ArrayList<Expr>(vals.size());
		for (Integer v : vals)
			exprs.add(Expressions.constantInt(v));
		return assembly.getData().values(exprs, ty);
	}

	public void generate(TextSegment s) {
		for (EObject i : s.getItems())
			voidDispatcher.invoke(i, assembly.getText());
	}

	public void generate(TextItem i, Segment s) {
		Element elm = elementDispatcher.invoke(i.getItem(), assembly.getText());
		labelElement(i.getLabel(), i, elm);
	}

	public Element generate(Space space, Segment s) {
		return s.space(space.getBytes());
	}

	public Element generate(Align a, Segment s) {
		return s.align(a.getAlign());
	}

	public Element generate(Insn i, Segment seg) {
		List<Reg> regs = new ArrayList<Reg>(i.getRegs().size());
		for (String s : i.getRegs()) {
			Reg r = Reg.parse(s);
			regs.add(r);
		}

		Option<Reg> base = Option.empty(Reg.class);
		if (i.getBase() != null)
			base = new Option<Reg>(Reg.parse(i.getBase()));

		Offset off = generate(i.getAddr());
		OperandInstance op = new OperandInstance(regs, off, base);
		InstructionGenerator gen = op.select(generators.get(i.getOpcode()));

		Element e = gen.generate(assembly.getText(), i.getOpcode(), op);
		e.setPosition(new EObjectPosition(i));
		return e;
	}

	private final PolymorphicDispatcher<Expr> exprDispatcher =
		PolymorphicDispatcher.createForSingleTarget("exprGen", 1, 2, this);

	private Offset generate(Addr a) {
		Option<LabelRef> label = Option.empty(LabelRef.class);
		Option<Expr> expr      = Option.empty(Expr.class);

		if (a != null) {
			if (a.getExpr() != null)
				expr = new Option<Expr>(exprDispatcher.invoke(a.getExpr()));

			if (a.getLabel() != null) {
				String name = a.getLabel().getName();
				label = new Option<LabelRef>(assembly.createRef(name));
			}
		}

		return new Offset(label, expr);
	}

	private Expr exprGenBinOp(Expressions.IntOp op, Expression left, Expression right) {
		Expr l = exprDispatcher.invoke(left);
		Expr r = exprDispatcher.invoke(right);
		return Expressions.binary(op, l, r);
	}

	public Expr exprGen(Plus e) {
		return exprGenBinOp(Expressions.IntOp.ADD, e.getLeft(), e.getRight());
	}

	public Expr exprGen(Minus e) {
		return exprGenBinOp(Expressions.IntOp.SUB, e.getLeft(), e.getRight());
	}

	public Expr exprGen(Mul e) {
		return exprGenBinOp(Expressions.IntOp.MUL, e.getLeft(), e.getRight());
	}

	public Expr exprGen(Shl e) {
		return exprGenBinOp(Expressions.IntOp.SHL, e.getLeft(), e.getRight());
	}

	public Expr exprGen(Shr e) {
		return exprGenBinOp(Expressions.IntOp.SHR, e.getLeft(), e.getRight());
	}

	public Expr exprGen(Shra e) {
		return exprGenBinOp(Expressions.IntOp.SHRA, e.getLeft(), e.getRight());
	}

	public Expr exprGen(Const cnst) {
		return Expressions.constantInt(cnst.getCnst());
	}

}

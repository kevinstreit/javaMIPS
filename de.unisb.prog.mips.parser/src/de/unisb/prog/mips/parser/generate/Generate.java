package de.unisb.prog.mips.parser.generate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.PolymorphicDispatcher;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Offset;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.assembler.generators.InstructionGenerator;
import de.unisb.prog.mips.assembler.generators.OperandInstance;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.parser.mips.Addr;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.parser.mips.DataSegment;
import de.unisb.prog.mips.parser.mips.Expression;
import de.unisb.prog.mips.parser.mips.Insn;
import de.unisb.prog.mips.parser.mips.Label;
import de.unisb.prog.mips.parser.mips.MipsPackage;
import de.unisb.prog.mips.parser.mips.TextItem;
import de.unisb.prog.mips.parser.mips.TextSegment;
import de.unisb.prog.mips.util.Option;

public class Generate {
	
	private final Assembly assembly;
	
	private PolymorphicDispatcher<Element> elementDispatcher = 
		PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this);
	
	private PolymorphicDispatcher<Void> voidDispatcher = 
		PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this);
	
	private Generators generators = new Generators();
	
	public Generate(Assembly assembly) {
		this.assembly = assembly;
	}
	
	public Void generate(Asm asm) {
		for (EObject o : asm.getElem())
			voidDispatcher.invoke(o);
		return null;
	}
	
	public void generate(DataSegment s) {
	}
	
	public void generate(TextSegment s) {
		for (TextItem i : s.getItems())
			generate(i);
	}
	
	public void generate(TextItem i) {
		Element e = elementDispatcher.invoke(i.getItem());
		Label label = i.getLabel();
		if (label != null) {
			e.setLabel(label.getName());
			assembly.addLabel(e);
		}
		// TODO Add line number
	}
	
	public Element generate(Insn i) {
		List<Reg> regs = new ArrayList<Reg>(i.getRegs().size());
		for (String s : i.getRegs()) {
			Reg r = Reg.parse(s);
			regs.add(r);
		}
		
		Option<Reg> base = Option.empty(Reg.class);
		if (i.getBase() != null)
			base = new Option<Reg>(Reg.parse(i.getBase()));
		
		Option<Expr> expr = Option.empty(Expr.class);
		Option<LabelRef> label = Option.empty(LabelRef.class);
		Addr a = i.getAddr();
		if (a != null) {
			if (a.getExpr() != null)
				expr = new Option<Expr>(Expressions.constantInt(22));
			if (a.getLabel() != null) 
				label = new Option<LabelRef>(assembly.createRef(a.getLabel().getName()));
				
		}
		
		OperandInstance op = new OperandInstance(regs, label, expr, base);
		InstructionGenerator gen = generators.get(i.getOpcode());
		return gen.generate(assembly.getText(), i.getOpcode(), op);
	}
	
	
	private Expr generate(Expression e) {
		return Expressions.constantInt(0);
	}
	
}

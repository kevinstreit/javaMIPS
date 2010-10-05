package de.unisb.prog.mips.parser.generate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.PolymorphicDispatcher;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Offset;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Factory;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.parser.mips.Addr;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.parser.mips.DataSegment;
import de.unisb.prog.mips.parser.mips.Expression;
import de.unisb.prog.mips.parser.mips.IAddrForm;
import de.unisb.prog.mips.parser.mips.IArithForm;
import de.unisb.prog.mips.parser.mips.IBranchForm;
import de.unisb.prog.mips.parser.mips.Insn;
import de.unisb.prog.mips.parser.mips.Label;
import de.unisb.prog.mips.parser.mips.RForm;
import de.unisb.prog.mips.parser.mips.SyscallForm;
import de.unisb.prog.mips.parser.mips.TextItem;
import de.unisb.prog.mips.parser.mips.TextSegment;
import de.unisb.prog.mips.util.Option;

public class Generate {
	
	private final Assembly assembly;
	
	private PolymorphicDispatcher<Element> elementDispatcher = 
		PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this);
	
	private PolymorphicDispatcher<Void> voidDispatcher = 
		PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this);
	
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
		return elementDispatcher.invoke(i.getForm());
	}
	
	private Address generate(Addr a) {
		return new Offset(assembly.createRef(a.getLabel().getName()), generate(a.getExpr()));
	}
	
	private Expr generate(Expression e) {
		return Expressions.constantInt(0);
	}
	
	public Element generate(RForm form) {
		Instruction insn = assembly.parse(form.getOpcode());
		System.out.println(form.getOpcode());
		Reg rd = Reg.parse(form.getRd());
		Reg rs = Reg.parse(form.getRs());
		Reg rt = Reg.parse(form.getRt());
		return Factory.threeReg(insn).create(assembly.getText(), insn, rs, rt, rd);
	}
	
	public Element generate(IAddrForm form) {
		Instruction insn = assembly.parse(form.getOpcode());
		System.out.println(form.getOpcode());
		Reg rs = form.getRs() != null ? Reg.parse(form.getRs()) : null;
		Reg rt = Reg.parse(form.getRt());
		Address addr = generate(form.getAddr());
		return Factory.regAddr(insn).create(assembly.getText(), insn, rt, new Option<Reg>(rs), addr);
	}
	
	public Element generate(IArithForm form) {
		Instruction insn = assembly.parse(form.getOpcode());
		System.out.println(form.getOpcode());
		Reg rs = Reg.parse(form.getRs());
		Reg rt = Reg.parse(form.getRt());
		int imm = form.getImm();
		return Factory.regConst(insn).create(assembly.getText(), insn, rs, rt, imm);
	}
	
	public Element generate(IBranchForm form) {
		Instruction insn = assembly.parse(form.getOpcode());
		System.out.println(form.getOpcode());
		Reg rs = Reg.parse(form.getRs());
		Reg rt = Reg.parse(form.getRt());
		LabelRef ref = assembly.createRef(form.getLabel().getName());
		return Factory.branch(insn).create(assembly.getText(), insn, rs, rt, ref);
	}
	
	public Element generate(SyscallForm form) {
		return assembly.getText().normal(IntFunct.syscall, Reg.zero, Reg.zero, Reg.zero, 0);
	}

}

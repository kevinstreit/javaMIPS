package de.unisb.prog.mips.parser.validation;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.validation.Check;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Immediate;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Instructions;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.parser.mips.IAddrForm;
import de.unisb.prog.mips.parser.mips.IArithForm;
import de.unisb.prog.mips.parser.mips.IBranchForm;
import de.unisb.prog.mips.parser.mips.MipsPackage;
import de.unisb.prog.mips.parser.mips.RForm;

public class MipsJavaValidator extends AbstractMipsJavaValidator {

	/*
		RForm:          opcode=ID rd=Reg rs=Reg rt=Reg;
		IArithForm:     opcode=ID rt=Reg rs=Reg imm=PNInt;
		IExpForm:       opcode=ID rt=Reg imm=PNInt '(' rs=Reg ')';
		ILabelForm:     opcode=ID reg=Reg label=[Label];
		IBr2Form:       opcode=ID rt=Reg rs=Reg label=[Label];
		JExpForm:       opcode=ID addr=INT;
		JLabelForm:     opcode=ID label=[Label];
	 */
	
	private static final Set<Instruction> IFORM = new HashSet<Instruction>();
	
	
	
	
	static {
		IFORM.addAll(EnumSet.range(Opcode.addi, Opcode.lui));
		IFORM.add(IntFunct.sll);
		IFORM.add(IntFunct.sra);
		IFORM.add(IntFunct.srl);
	}
	
	@Check
	public void form(RForm form) {
		String opc = form.getOpcode();
		Instruction insn = Instructions.get(opc);
		checkReg(form.getRd(), MipsPackage.RFORM__RD);
		checkReg(form.getRs(), MipsPackage.RFORM__RS);
		checkReg(form.getRt(), MipsPackage.RFORM__RT);
		if (!insn.valid() || insn.getKind() != Kind.THREE_REG) 
			error("not a valid R-form instruction: " + opc, MipsPackage.RFORM__OPCODE);
	}
	
	private void checkImmediate(int imm, Instruction insn, Integer feature) {
		int low = 0, high = 0;
		if (insn.getKind() == Kind.SHAMT)
			high = 31;
		else if (insn.getImmediate() == Immediate.ZEXT_16) 
			high = 65535;
		else if (insn.getImmediate() == Immediate.SEXT_16) low = -32768;
			high = 32767;
		if (imm < low || imm > high) 
			error(String.format("immediate out of range: %d..%d", low, high), feature);
	}
	
	@Check
	public void form(IArithForm form) {
		String opc = form.getOpcode();
		Instruction insn = Instructions.get(opc);
		checkReg(form.getRs(), MipsPackage.IARITH_FORM__RS);
		checkReg(form.getRt(), MipsPackage.IARITH_FORM__RT);
		if (!IFORM.contains(insn)) {
			error("not a valid I-Form arithmetic instruction: " + opc, MipsPackage.IARITH_FORM__OPCODE);
			return;
		}
		checkImmediate(form.getImm(), insn, MipsPackage.IARITH_FORM__IMM);
	}
	
	@Check
	public void form(IAddrForm form) {
		String opc = form.getOpcode();
		Instruction insn = Instructions.get(opc);
		checkReg(form.getRs(), MipsPackage.IADDR_FORM__RS);
		checkReg(form.getRt(), MipsPackage.IADDR_FORM__RT);
		if (insn.getKind() != Kind.LOAD_STORE || insn.getOpcode() != Opcode.regimm)
			error("not a valid I-Form address instruction: " + opc, MipsPackage.IADDR_FORM__OPCODE);
	}
	
	@Check
	public void form(IBranchForm form) {
		String opc = form.getOpcode();
		Instruction insn = Instructions.get(opc);
		checkReg(form.getRs(), MipsPackage.IBRANCH_FORM__RS);
		checkReg(form.getRt(), MipsPackage.IBRANCH_FORM__RT);
		if (insn.getKind() != Kind.REL_JUMP || insn.getOpcode() == Opcode.regimm)
			error("not a valid I-Form two register branch instruction: " + opc, MipsPackage.IADDR_FORM__OPCODE);
	}
	
	private void checkReg(String name, int feature) {
		try {
			Reg.parse(name);
		}
		catch (IllegalArgumentException e) {
			error(e.getMessage(), feature);
		}
	}
	

}

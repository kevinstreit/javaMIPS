package de.unisb.prog.mips.parser.validation;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.validation.Check;

import de.unisb.prog.mips.insn.Attribute;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Instructions;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.parser.mips.IArithForm;
import de.unisb.prog.mips.parser.mips.IExpForm;
import de.unisb.prog.mips.parser.mips.MipsPackage;
import de.unisb.prog.mips.parser.mips.RForm;
import de.unisb.prog.mips.parser.mips.Reg;
 
import static de.unisb.prog.mips.insn.Opcode.*;
import static de.unisb.prog.mips.insn.IntFunct.*;
import static de.unisb.prog.mips.insn.RegImm.*;

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
		if (!insn.valid() || !insn.has(Attribute.THREE_REG))
			error("not a valid R-form instruction: " + opc, MipsPackage.RFORM__OPCODE);
	}
	
	private void checkImmediate(int imm, Set<Attribute> attr, Integer feature) {
		int low = 0, high = 0;
		if (attr.contains(Attribute.SHAMT)) 
			high = 31;
		else if (attr.contains(Attribute.ZEXT_IMM_16)) 
			high = 65535;
		else if (attr.contains(Attribute.SEXT_IMM_16)) {
			low = -32768;
			high = 32767;
		}
		if (imm < low || imm > high) 
			error(String.format("immediate out of range: %d..%d", low, high), feature);
	}
	
	@Check
	public void form(IArithForm form) {
		String opc = form.getOpcode();
		Instruction insn = Instructions.get(opc);
		if (!IFORM.contains(insn)) {
			error("not a valid I-Form arithmetic instruction: " + opc, MipsPackage.IARITH_FORM__OPCODE);
			return;
		}
		checkImmediate(form.getImm(), insn.attributes(), MipsPackage.IARITH_FORM__IMM);
	}
	
	@Check
	public void form(IExpForm form) {
		String opc = form.getOpcode();
		Instruction insn = Instructions.get(opc);
		if (! insn.attributes().contains(Attribute.LOAD_STORE))
			error("not a valid I-Form address instruction: " + opc, MipsPackage.IEXP_FORM__OPCODE);
		checkImmediate(form.getImm(), insn.attributes(), MipsPackage.IEXP_FORM__IMM);
	}
	
	@Check
	public void registerName(Reg r) {
		String reg = r.getReg().substring(1);
		try {
			int val = Integer.parseInt(reg);
			if (val < 0 || val > 31) 
				error("Illegal register ($0-$31)", MipsPackage.REG__REG);
		} 
		catch (NumberFormatException e) {
			try {
				de.unisb.prog.mips.assembler.Reg.valueOf(reg);
			}
			catch (IllegalArgumentException f) {
				error("Illegal register", MipsPackage.REG__REG);
			}
		}
	}

}

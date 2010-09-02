package de.unisb.prog.mips.assembler.text;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Segment;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Text extends Segment {
	
	public void normal(IntFunct f, int rt, int rs, int rd, int shamt) {
		add(new Insn(Encode.r(f, rt, rs, rd, shamt)));
	}
	
	public void imm(Opcode opc, int rt, int rs, int imm) {
		add(new Insn(Encode.i(opc, rt, rs, imm)));
	}

	public void loadstore(Opcode opc, int rt, LabelRef ref) {
	}

	public void condjump(Opcode opc, int rt, int rs, LabelRef ref) {
	}

	public void condjump(Opcode r, int rs, LabelRef ref) {
	}

	public void condjump(RegImm r, int rs, LabelRef ref) {
	}
	
}

package de.unisb.prog.mips.assembler.text;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.Opcode;

public class Data extends LabelRefInsn {

	Data(Opcode opc, int rs, int rt, LabelRef ref) {
		super(Encode.i(opc, rs, rt, 0), ref);
	}

}

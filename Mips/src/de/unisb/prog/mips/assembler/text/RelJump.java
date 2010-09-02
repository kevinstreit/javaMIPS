package de.unisb.prog.mips.assembler.text;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

class RelJump extends LabelRefInsn {
	
	RelJump(Opcode opc, int rs, int rt, LabelRef ref) {
		super(Encode.i(opc, rs, rt, 0), ref);
	}
	
	RelJump(RegImm ri, int rs, LabelRef ref) {
		super(Encode.i(ri, rs, 0), ref);
	}

}

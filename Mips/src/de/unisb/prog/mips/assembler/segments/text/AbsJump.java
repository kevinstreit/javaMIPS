package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.Opcode;

class AbsJump extends LabelRefInsn {
	
	AbsJump(Opcode opc, Expr<Integer> exp) {
		super(Encode.j(opc, 0), exp);
	}
	
	public void rewrite(int startAddress) {
		
	}

}

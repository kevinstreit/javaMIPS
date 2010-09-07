package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

abstract class LabelRefInsn extends Insn {
	
	protected final Expr<Integer> exp;
	
	LabelRefInsn(int enc, Expr<Integer> exp) {
		super(enc);
		this.exp = exp;
	}
	
}

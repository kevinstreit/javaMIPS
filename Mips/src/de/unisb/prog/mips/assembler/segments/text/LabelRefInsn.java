package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;

abstract class LabelRefInsn extends Insn {
	
	protected final Expr<Integer> exp;
	
	LabelRefInsn(int enc, Expr<Integer> exp) {
		super(enc);
		this.exp = exp;
	}

}

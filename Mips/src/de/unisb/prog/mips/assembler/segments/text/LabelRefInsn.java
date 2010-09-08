package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Expr;

abstract class LabelRefInsn extends Insn {
	
	protected final Expr<Integer> exp;
	
	LabelRefInsn(int enc, Expr<Integer> exp) {
		super(enc);
		this.exp = exp;
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		super.appendInternal(app);
		app.append(" ; label ref: ");
		exp.append(app);
	}
	
	
	
}

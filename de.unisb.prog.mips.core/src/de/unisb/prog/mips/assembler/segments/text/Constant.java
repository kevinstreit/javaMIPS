package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Opcode;

public class Constant extends ImmGen<Expr> {

	public Constant(Segment seg, Reg rt, Expr expr) {
		super(seg, Opcode.addiu, rt, expr);
	}

	public Constant(Segment seg, Reg rt, int value) {
		this(seg, rt, Expressions.constantInt(value));
	}
	
	protected void rewrite() {
		setGenImm(Reg.zero, rt);
	}

}

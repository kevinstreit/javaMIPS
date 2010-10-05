package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;

public class Constant extends ImmGen<Expr> {

	public Constant(Reg rt, Expr expr) {
		super(Opcode.addi, rt, expr);
	}

	public Constant(Reg rt, int value) {
		this(rt, Expressions.constantInt(value));
	}
	
	protected void rewrite() {
		insertImmGen(Reg.zero, rt);
	}

}

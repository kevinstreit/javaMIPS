package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;

public class Constant extends AddrGen<Expr<Integer>> {

	public Constant(Reg rt, Expr<Integer> exp) {
		super(Opcode.addi, rt, exp);
	}

	public Constant(Reg rt, int value) {
		super(Opcode.addi, rt, Expressions.constantInt(value));
	}
	
	protected void rewrite() {
		insertAddrGen(Reg.zero, rt);
	}

}

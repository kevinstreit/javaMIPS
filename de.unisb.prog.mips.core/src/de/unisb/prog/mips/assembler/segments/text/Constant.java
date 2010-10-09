package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class Constant extends ImmGen<Expr> {
	
	private static final Option<Reg> zero = new Option<Reg>(Reg.zero);

	public Constant(Segment seg, Reg rt, Expr expr) {
		super(seg, "li", Opcode.addiu, rt, zero, expr);
	}

	public Constant(Segment seg, Reg rt, int value) {
		this(seg, rt, Expressions.constantInt(value));
	}
	
	protected void rewrite() {
		set(genImm(Reg.zero, rt));
	}

}

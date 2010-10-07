package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Segment.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class LoadAddress extends ImmGen<Address> {
	
	private final Option<Reg> base;
	
	public LoadAddress(Segment seg, Reg rt, Option<Reg> reg, Address addr) {
		super(seg, Opcode.addi, rt, addr);
		this.base = reg;
	}

	@Override
	protected void rewrite() {
		Reg b = base.otherwise(expr.getSegment().getKind() == Kind.TEXT ? Reg.zero : Reg.gp);
		setGenImm(b, rt);
	}

}

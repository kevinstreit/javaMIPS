package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Segment.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class LoadAddress extends ImmGen<Address> implements Relocateable {
	
	private final Option<Reg> base;
	
	public LoadAddress(Segment seg, Reg rt, Option<Reg> reg, Address addr) {
		super(seg, Opcode.addiu, rt, addr);
		this.base = reg;
	}

	@Override
	protected void rewrite() {
		if (expr.getSegment().getKind() == Kind.DATA) {
			Reg b = base.otherwise(Reg.gp);
			setGenImm(b, rt);
		}
	}

	@Override
	public void relocate(int startAddress) throws JumpTargetOutOfRange {
		throw new UnsupportedOperationException("cannot take address of text segment elements yet!");
	}

}

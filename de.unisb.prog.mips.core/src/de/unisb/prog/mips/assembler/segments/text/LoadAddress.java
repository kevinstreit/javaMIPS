package de.unisb.prog.mips.assembler.segments.text;

import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Segment.Kind;
import de.unisb.prog.mips.insn.IntFunct;
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
			set(genImm(b, rt));
		}
		
		else {
			// produce dummy address generation code 
			// to represent the code size correctly for relocation
			// which comes *after* rewriting
			List<Insn> dummy = new ArrayList<Insn>(2);
			dummy.add(new Insn(segment, 0));
			dummy.add(new Insn(segment, 0));
			set(dummy);
		}
	}

	@Override
	public void relocate(int startAddress) throws JumpTargetOutOfRange {
		System.out.println("segment: " + expr.getSegment().getKind().name());
		if (expr.getSegment().getKind() == Kind.DATA)
			return;
		List<Insn> res = new ArrayList<Insn>(2);
		produceImmediate(res, rt, startAddress + expr.eval());
		// if address generation takes less than two instructions
		// insert nops to pad it to the right size
		System.out.format("relocate to: %x\n", startAddress + expr.eval());
		for (int i = res.size(); i < 2; i++)
			res.add(new Insn(segment, 0));
		set(res);
	}

}

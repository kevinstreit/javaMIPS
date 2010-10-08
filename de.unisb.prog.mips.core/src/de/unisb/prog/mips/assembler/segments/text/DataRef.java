package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class DataRef extends ImmGen<Address> {
	
	private Reg base;
	
	DataRef(Segment seg, Opcode opc, Reg rt, Option<Reg> base, Address addr) {
		super(seg, opc, rt, addr);
		this.base = base.otherwise(Reg.gp);
	}
	
	protected void rewrite() {
		set(genImm(base, Reg.at));
	}
	

}

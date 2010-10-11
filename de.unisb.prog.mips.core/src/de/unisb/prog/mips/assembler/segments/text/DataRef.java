package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class DataRef extends ImmGen<Address> {
	
	DataRef(Segment seg, Opcode opc, Reg rt, Option<Reg> base, Address addr) {
		super(seg, opc, rt, base, addr);
	}
	
	protected void rewrite() {
		Reg b     = base.otherwise(Reg.gp);
		Address a = base.ifthenelse(expr, Expressions.offset(expr, -32768));
		set(genImm(b, Reg.at, a));
	}
	

}

package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class LoadAddress extends ImmGen<Address> {
	
	private final Option<Reg> base;
	
	public LoadAddress(Reg rt, Option<Reg> reg, Address addr) {
		super(Opcode.addi, rt, addr);
		this.base = reg;
	}

	@Override
	protected void rewrite() {
		Reg b = base.otherwise(expr.isText() ? Reg.zero : Reg.gp);
		insertImmGen(b, rt);
	}

}

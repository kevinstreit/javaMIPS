package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;

public class Constant extends AddrGen {

	public Constant(Reg rt, Address addr) {
		super(Opcode.addi, rt, addr);
	}

	public Constant(Reg rt, int value) {
		super(Opcode.addi, rt, new Address(Expressions.constantInt(value)));
	}
	
	protected void rewrite() {
		insertAddrGen(Reg.zero, rt);
	}

}

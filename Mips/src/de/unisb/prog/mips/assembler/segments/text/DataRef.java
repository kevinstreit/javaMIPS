package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;

public class DataRef extends AddrGen {
	
	DataRef(Opcode opc, int rt, Expr<Integer> e) {
		super(opc, rt, e);
	}
	
	protected void rewrite() {
		insertAddrGen(Reg.gp.ordinal(), Reg.at.ordinal());
	}
	

}

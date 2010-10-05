package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;

public class DataRef extends AddrGen {
	
	DataRef(Opcode opc, Reg rt, Address addr) {
		super(opc, rt, addr);
	}
	
	protected void rewrite() {
		insertAddrGen(Reg.gp, Reg.at);
	}
	

}

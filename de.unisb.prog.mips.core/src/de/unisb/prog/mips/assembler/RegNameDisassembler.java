package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.insn.Disassembler;

public class RegNameDisassembler extends Disassembler {

	@Override
	protected String regName(int reg) {
		if (reg < 0 || reg > 31)
			return "???";
		return reg == 0 ? "$0" : "$" + Reg.values()[reg].name();
	}
	
	public static final RegNameDisassembler INSTANCE = new RegNameDisassembler();

}

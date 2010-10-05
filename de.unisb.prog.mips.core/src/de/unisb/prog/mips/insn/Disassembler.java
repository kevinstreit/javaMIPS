package de.unisb.prog.mips.insn;

public class Disassembler implements Handler<String> {
	
	public static final Disassembler INSTANCE = new Disassembler();
	
	@Override
	public String i(Opcode opc, int rs, int rt, int imm) {
		imm = opc.extendImm(imm);
		return String.format("%7s %3s %3s %-6d", opc, regName(rt), regName(rs), imm);
	}

	@Override
	public String i(RegImm ri, int rs, int imm) {
		return String.format("%7s %3s %-6d", ri, rs, imm);
	}

	@Override
	public String j(Opcode opc, int imm) {
		return String.format("%7s %08x", opc, imm);
	}

	@Override
	public String r(IntFunct funct, int rs, int rt, int rd, int shamt) {
		return String.format("%7s %3s %3s %3s", funct, regName(rd), regName(rs), regName(rt));
	}
	
	protected String regName(int reg) {
		return String.format("$%d", reg);
	}
	
	public String disasm(int word) {
		try {
			return Instructions.decode(word, this);
		} catch (IllegalOpcodeException e) {
			return String.format("%7s %08x", ".word", word);
		}
	}

}

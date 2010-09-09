package de.unisb.prog.mips.insn;

public class Disassembler implements Handler<String> {
	
	@Override
	public String i(Opcode opc, int rs, int rt, int imm) {
		imm = opc.extendImm(imm);
		return String.format("%5s %3s, %3s, %-6d", opc, regName(rt), regName(rs), imm);
	}

	@Override
	public String i(RegImm ri, int rs, int imm) {
		return String.format("%5s %3s, %-6d", rs, imm);
	}

	@Override
	public String j(Opcode opc, int imm) {
		return String.format("%5s %08x", opc, imm);
	}

	@Override
	public String r(IntFunct funct, int rs, int rt, int rd, int shamt) {
		return String.format("%5s %3s, %3s, %3s", funct, regName(rd), regName(rs), regName(rt));
	}
	
	protected String regName(int reg) {
		return String.format("$%d", reg);
	}
	
	public String disasm(int word) {
		try {
			return Decode.decode(word, this);
		} catch (IllegalOpcodeException e) {
			return String.format(".word %08x", word);
		}
	}

}

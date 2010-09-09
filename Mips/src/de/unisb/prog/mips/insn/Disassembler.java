package de.unisb.prog.mips.insn;

public class Disassembler implements Handler<String> {
	
	@Override
	public String f(int fmt, int ft, int fs, int fd, int funct) {
		throw new UnsupportedOperationException("no floating point support yet");
	}

	@Override
	public String i(int op, int rs, int rt, int imm) {
		Opcode opc = Opcode.values()[op];
		imm = opc.extendImm(imm);
		return String.format("%5s %3s, %3s, %-6d", opc.name(), regName(rt), regName(rs), imm);
	}

	@Override
	public String j(int op, int imm) {
		return String.format("%5s %08x", Opcode.values()[op].toString(), imm);
	}

	@Override
	public String r(int rs, int rt, int rd, int shamt, int funct) {
		return String.format("%5s %3s, %3s, %3s", IntFunct.values()[funct].toString(), regName(rd), regName(rs), regName(rt));
	}
	
	protected String regName(int reg) {
		return String.format("$%d", reg);
		
	}
	
	public String disasm(int word) {
		return Decode.decode(word, this);
	}

}

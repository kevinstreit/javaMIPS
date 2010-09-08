package de.unisb.prog.mips.insn;



public class Disassembler implements Handler<String> {
	
	@Override
	public String f(int fmt, int ft, int fs, int fd, int funct) {
		throw new UnsupportedOperationException("no floating point support yet");
	}

	@Override
	public String i(int op, int rs, int rt, int imm) {
		return String.format("%5s $%2d, $%2d, %5d", Opcode.values()[op].toString(), rs, rt, imm);
	}

	@Override
	public String j(int op, int imm) {
		return String.format("%5s %8x", Opcode.values()[op].toString(), imm);
	}

	@Override
	public String r(int rs, int rt, int rd, int shamt, int funct) {
		return String.format("%5s $%2d, $%2d, $%2d", IntFunct.values()[funct].toString(), rs, rt, rd);
	}

}

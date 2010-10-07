package de.unisb.prog.mips.insn;

public enum Immediate {
	NO_IMM  { @Override public boolean fits(int imm) { return false; } },
	SEXT_16 { @Override public boolean fits(int imm) { return -32768 <= imm && imm < 32768; } },
	ZEXT_16 { @Override public boolean fits(int imm) { return 0 <= imm && imm < 65536; } },
	ZEXT_26 { @Override public boolean fits(int imm) { return 0 <= imm && imm < (1 << 26); } };
	
	public abstract boolean fits(int imm);
}
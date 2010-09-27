package de.unisb.prog.mips.insn;

public enum Attribute {
	CHANGES_PC,
	INDIR_JUMP,
	CMP_AGAINST_0,
	SEXT_IMM_16,
	ZEXT_IMM_16,
	ZEXT_IMM_26,
	SHAMT,
	HALT,
}
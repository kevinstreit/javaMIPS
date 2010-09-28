package de.unisb.prog.mips.insn;

public enum Attribute {
	THREE_REG,
	CHANGES_PC,
	INDIR_JUMP,
	CMP_AGAINST_0,
	SEXT_IMM_16,
	ZEXT_IMM_16,
	ZEXT_IMM_26,
	SHAMT,
	LOAD_STORE,
	HALT,
}
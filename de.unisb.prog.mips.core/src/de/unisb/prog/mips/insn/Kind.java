package de.unisb.prog.mips.insn;

public enum Kind {
	THREE_REG,
	SHAMT,
	IMM,
	LOAD_STORE,
	REL_JUMP(true),
	ABS_JUMP(true),
	INDIR_JUMP(true);
	
	private final boolean changesPc;
	
	Kind(boolean changesPc) {
		this.changesPc = changesPc;
	}
	
	Kind() {
		this(false);
	}
	
	public boolean changesPc() {
		return changesPc;
	}
	
	
}
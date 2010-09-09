package de.unisb.prog.mips.simulator;

public enum Type {
	BYTE, HALF, WORD;
	
	public int alignMask() { 
		return -sizeof();
	}
	
	public int sizeof() {
		return 1 << ordinal();
	}
	
	public int roundDown(int addr) {
		return addr & alignMask();
	}
	
	public int valueMask() {
		return (1 << (sizeof() * 8)) - 1;
	}
	
	public boolean isAligned(int addr) {
		return addr == roundDown(addr);
	}
	
	public int signExtend(int v) {
		int shmt = 32 - sizeof() * 8;
		return (v << shmt) >> shmt;
	}
	
}
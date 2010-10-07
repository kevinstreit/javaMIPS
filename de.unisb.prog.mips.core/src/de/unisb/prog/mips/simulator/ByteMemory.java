package de.unisb.prog.mips.simulator;

public interface ByteMemory {
	
	void reset();
	byte load(int addr);
	void store(int addr, byte val);

}

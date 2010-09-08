package de.unisb.prog.mips.simulator;

public interface ByteMemory {
	
	byte load(int addr);
	void store(int addr, byte val);

}

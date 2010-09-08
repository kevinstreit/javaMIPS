package de.unisb.prog.mips.simulator;

public interface Memory {
	
	byte load(int addr);
	void store(int addr, byte val);

}

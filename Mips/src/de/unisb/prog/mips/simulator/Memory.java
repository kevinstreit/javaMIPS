package de.unisb.prog.mips.simulator;

public interface Memory {
	
	int width();
	void load(int addr, byte[] contents);
	void store(int addr, byte[] contents);

}

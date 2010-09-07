package de.unisb.prog.mips.simulator;

public interface Memory {
	
	int load(int addr);
	void store(int addr, int word);

}

package de.unisb.prog.mips.simulator;

public interface System {
	
	void syscall(ProcessorState state, int id);

}

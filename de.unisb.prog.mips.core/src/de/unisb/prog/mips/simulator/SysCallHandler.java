package de.unisb.prog.mips.simulator;


public interface SysCallHandler {
	
	void syscall(ProcessorState state, Memory mem);

}

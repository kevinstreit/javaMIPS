package de.unisb.prog.mips.simulator;

public interface ExceptionHandler {

	void breakpoint(ProcessorState state, Memory mem);
	void illegalInstruction(ProcessorState state, Memory mem, int addr);
	void unalignedMemory(ProcessorState state, Memory mem, int addr);

}

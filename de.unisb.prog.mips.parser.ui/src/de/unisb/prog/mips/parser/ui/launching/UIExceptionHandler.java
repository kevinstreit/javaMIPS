package de.unisb.prog.mips.parser.ui.launching;

import de.unisb.prog.mips.simulator.ExceptionHandler;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;

public class UIExceptionHandler implements ExceptionHandler {

	public void breakpoint(ProcessorState state, Memory mem) {
		// Nothing to do currently
	}

	public void illegalInstruction(ProcessorState state, Memory mem, int addr) {
		// Nothing to do currently
	}

	public void unalignedMemory(ProcessorState state, Memory mem, int addr) {
		// TODO Auto-generated method stub

	}

}

package de.unisb.prog.mips.os;

import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;

public interface OS {
	
	void syscall(ProcessorState state, Memory mem);

}

package de.unisb.prog.mips.os;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.SysCallHandler;

public class SysCallDispatcher implements SysCallHandler {
	
	private final SysCallImplementation impl;
	
	public SysCallDispatcher(SysCallImplementation impl) {
		super();
		this.impl = impl;
	}
	
	@Override
	public void syscall(ProcessorState state, Memory mem) {
		int v0 = Reg.v0.get(state.gp);
		int a0 = Reg.a0.get(state.gp);
		if (v0 < 0 || v0 >= SysCallID.values().length)
			return;
		SysCallID c = SysCallID.values()[v0];
		switch (c) {
		case print_int: 
			impl.print(a0);
			break;
		case print_string:
			impl.print(mem.loadString(a0));
			break;
		case print_char:
			impl.print((char) a0);
			break;
		case exit: 
		case exit2: 
			state.state = ExecutionState.HALTED;
			return;
		}
	}


}

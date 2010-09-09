package de.unisb.prog.mips.os;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;
import de.unisb.prog.mips.simulator.SysCallHandler;
import de.unisb.prog.mips.simulator.Type;

public class DefaultOS implements SysCallHandler {
	
	private void printInt(ProcessorState state) {
		int a0 = Reg.a0.get(state.gp);
		System.out.print(a0);
	}
	
	private void printStr(ProcessorState state, Memory mem) {
		int a0 = Reg.a0.get(state.gp);
		char ch = (char) mem.load(a0, Type.BYTE);
		for (int i = 1; ch != 0; i++) {
			System.out.print(ch);
			ch = (char) mem.load(a0 + i, Type.BYTE);
		}
	}

	@Override
	public void syscall(ProcessorState state, Memory mem) {
		int v0 = Reg.v0.get(state.gp);
		Syscall c = Syscall.values()[v0];
		switch (c) {
		case print_int: printInt(state); return;
		case print_string: printStr(state, mem); return;
		case exit: return;
		case exit2: return;
		}
	}


}
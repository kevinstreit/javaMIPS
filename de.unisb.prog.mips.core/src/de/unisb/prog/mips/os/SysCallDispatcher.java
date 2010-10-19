package de.unisb.prog.mips.os;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.SysCallHandler;
import de.unisb.prog.mips.simulator.Type;

public class SysCallDispatcher implements SysCallHandler {

	private final SysCallImplementation impl;

	public SysCallDispatcher(SysCallImplementation impl) {
		super();
		this.impl = impl;
	}

	public void syscall(ProcessorState state, Memory mem) {
		int v0 = Reg.v0.get(state.gp);
		int a0 = Reg.a0.get(state.gp);
		if (v0 < 0 || v0 >= SysCallID.values().length)
			return;
		SysCallID c = SysCallID.values()[v0];
		switch (c) {
		case print_int:
			this.impl.print(a0);
			break;
		case print_string:
			this.impl.print(mem.loadString(a0));
			break;
		case print_char:
			this.impl.print((char) a0);
			break;


		case read_int:
			state.gp[Reg.v0.ordinal()] = this.impl.readInt();
			break;
		case read_string: {
			int a1 = Reg.a1.get(state.gp);
			byte[] data = new byte[a1];
			state.gp[Reg.v0.ordinal()] = this.impl.readString(data);
			for (int i = 0; i < a1; i++)
				mem.store(a0 + i, data[i], Type.BYTE);
		}
			break;
		case read_char:
			state.gp[Reg.v0.ordinal()] = this.impl.readChar();
			break;

		case exit:
			state.state = ExecutionState.HALTED;
			this.impl.exit(0);
			return;
		case exit2:
			state.state = ExecutionState.HALTED;
			this.impl.exit(a0);
			return;
		}
	}


}

package de.unisb.prog.mips.cmdline;

import java.io.IOException;
import java.io.PrintStream;

import de.unisb.prog.mips.simulator.ExceptionHandler;
import de.unisb.prog.mips.simulator.MemDumpFormatter;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;

public class CLIExceptionHandler implements ExceptionHandler {

	private final PrintStream ps;

	public CLIExceptionHandler(PrintStream ps) {
		this.ps = ps;
	}

	private void print(String kind, ProcessorState state, Memory mem, int addr) {
		try {
			ps.format("@%08x ");
			mem.dump(ps, addr, 1, MemDumpFormatter.DISASM);
			ps.format(": %s\n", kind);
		} catch (IOException e) {
		}
	}

	public void illegalInstruction(ProcessorState state, Memory mem, int addr) {
		print("illegal instruction", state, mem, addr);
	}

	public void unalignedMemory(ProcessorState state, Memory mem, int addr) {
		print("unaligned memory", state, mem, addr);
	}

	public void overflow(ProcessorState state, Memory mem, int addr) {
		print("overflow", state, mem, addr);
	}

	public void breakpoint(ProcessorState state, Memory mem) {
	}

}

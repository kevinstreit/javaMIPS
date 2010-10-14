package de.unisb.prog.mips.simulator;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.Reg;

public class Sys implements MemoryLayout {

	private final ByteMemory vm = new VirtualMemory(12);
	private final Memory mem;
	private final Processor sim;

	public Sys(int memPages, ExceptionHandler exch, SysCallHandler sys) {
		this.mem = new Memory(vm, true);
		this.sim = new Processor(mem, exch, sys);
	}

	public void load(Assembly asm, ErrorReporter<Position> reporter) {
		vm.reset();
		asm.prepare(this, reporter);
		asm.writeToMem(mem, this);
		sim.gp[Reg.gp.ordinal()] = dataStart() + 32768;
		sim.gp[Reg.sp.ordinal()] = stackStart();
		sim.pc = textStart();
	}

	public Memory getMemory() {
		return mem;
	}

	public Processor getProcessor() {
		return sim;
	}

	@Override public int dataStart()       { return 0x10000000; }
	@Override public int textStart()       { return 0x400000; }
	@Override public int stackStart()      { return 0x7ffffffc; }

}

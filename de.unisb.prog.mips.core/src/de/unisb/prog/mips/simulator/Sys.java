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
		this.mem = new Memory(this.vm, true);
		this.sim = new Processor(this.mem, exch, sys);
	}

	public void load(Assembly asm, ErrorReporter<Position> reporter) {
		this.vm.reset();
		asm.prepare(this, reporter);
		asm.writeToMem(this.mem, this);
		this.sim.gp[Reg.gp.ordinal()] = dataStart() + 32768;
		this.sim.gp[Reg.sp.ordinal()] = stackStart();
		this.sim.pc = textStart();
	}

	public Memory getMemory() {
		return this.mem;
	}

	public Processor getProcessor() {
		return this.sim;
	}

	public int dataStart()       { return 0x10000000; }
	public int textStart()       { return 0x400000; }
	public int stackStart()      { return 0x7ffffffc; }

}

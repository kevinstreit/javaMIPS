package de.unisb.prog.mips.simulator;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.Reg;

public class Sys implements MemoryLayout {
	
	private final Memory mem;
	private final Processor sim;
	private final SysCallHandler sys;
	
	public Sys(int memPages, SysCallHandler sys) {
		ByteMemory vm = new VirtualMemory(12);
		this.mem = new Memory(vm, true);
		this.sim = new Processor(mem, null, sys);
		this.sys = sys;
	}
	
	public void run(Assembly asm) {
		asm.writeToMem(mem, this);
		try {
			mem.dump(System.out, dataStart(), 100, MemDumpFormatter.DATA);
			mem.dump(System.out, textStart(), 60, MemDumpFormatter.DISASM);
		} catch (IOException e) {
		}
		sim.gp[Reg.gp.ordinal()] = dataStart() - dataStartOffset();
		sim.gp[Reg.sp.ordinal()] = stackStart();
		sim.pc = textStart();
		sim.run();
	}

	@Override public int dataStart()       { return 0x10000000; }
	@Override public int textStart()       { return 0x400000; }
	@Override public int stackStart()      { return 0x7ffffffc; }
	@Override public int dataStartOffset() { return -0x8000; }
	@Override public int textStartOffset() { return 0; }

}

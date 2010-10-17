package de.unisb.prog.mips.simulator;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.LabelNotDefinedException;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;

public class Sys implements MemoryLayout {

	private final ByteMemory vm = new VirtualMemory(12);
	private final Memory mem;
	private final Processor sim;

	public Sys(int memPages, ExceptionHandler exch, SysCallHandler sys) {
		this.mem = new Memory(this.vm, true);
		this.sim = new Processor(this.mem, exch, sys);
	}

	public boolean load(Assembly asm) {
		this.vm.reset();
		asm.relocate(this);
		asm.writeToMem(this.mem);

		this.sim.gp[Reg.gp.ordinal()] = dataStart() + 32768;
		this.sim.gp[Reg.sp.ordinal()] = stackStart();
		this.sim.pc = textStart();

		try {
			asm.getData().dump(System.out, mem, MemDumpFormatter.DATA);
			asm.getText().dump(System.out, mem, MemDumpFormatter.DISASM);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			Element main = asm.lookupElement("main");
			this.sim.pc = main.addressOf();
			return true;
		} catch (LabelNotDefinedException e) {
			asm.getReporter().warning("global label \"main\" not found");
			this.sim.pc = textStart();
			return false;
		}
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

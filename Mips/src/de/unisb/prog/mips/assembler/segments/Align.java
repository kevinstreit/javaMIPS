package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;

public class Align extends Element {
	
	private final int powerOfTo;
	
	public Align(int powerOfTo, Reg relative) {
		super(relative);
		this.powerOfTo = powerOfTo;
	}

	@Override
	public int nextElementOffset(int pos) {
		int v = 1 << powerOfTo;
		int off = (pos + (v - 1)) & -v;
		return off;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		app.append(String.format(".align %d", powerOfTo));
	}

}

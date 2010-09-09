package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;


public class Space extends Element {
	
	private final int bytes;

	public Space(int bytes, Reg relative) {
		super(relative);
		this.bytes = bytes;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + bytes;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		app.append(String.format(".space %d", bytes));
	}

}

package de.unisb.prog.mips.assembler.segments;

import de.unisb.prog.mips.simulator.CoarseMemory;


public class Space extends Element {
	
	private final int bytes;

	public Space(int bytes) {
		super();
		this.bytes = bytes;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + bytes;
	}

	@Override
	public void writeToMem(CoarseMemory mem, int addr) {
	}

}

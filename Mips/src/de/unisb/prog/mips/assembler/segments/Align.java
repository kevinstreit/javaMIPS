package de.unisb.prog.mips.assembler.segments;

public class Align extends Element {
	
	private final int powerOfTo;
	
	public Align(int powerOfTo) {
		super();
		this.powerOfTo = powerOfTo;
	}

	@Override
	public int nextElementOffset(int pos) {
		int v = 1 << powerOfTo;
		int off = (pos + (v - 1)) & -v;
		return off - pos;
	}

}

package de.unisb.prog.mips.assembler.segments;


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

}

package de.unisb.prog.mips.assembler.segments;

import de.unisb.prog.mips.simulator.Memory;

public abstract class Element extends ListItem<Element> {
	
	private int offset;

	public Element me() {
		return this;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	static Element createDummy() {
		return new Element() {
			@Override
			public int nextElementOffset(int pos) {
				return pos;
			}

			@Override
			public void writeToMem(Memory mem, int addr) {
			}
		};
	}
	
	public abstract int nextElementOffset(int pos);
	public abstract void writeToMem(Memory mem, int addr);
	
}

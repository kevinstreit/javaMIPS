package de.unisb.prog.mips.assembler.segments;

import de.unisb.prog.mips.assembler.ByteBuffer;

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
	
	public abstract int nextElementOffset(int pos);
	
	static Element createDummy() {
		return new Element() {
			@Override
			public int nextElementOffset(int pos) {
				return pos;
			}
		};
	}
	
}

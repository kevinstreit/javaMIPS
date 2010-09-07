package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.assembler.segments.Segment;

public class Label {
	
	private final String name;
	private final Segment seg;
	private int offset;
	
	public Label(String name, Segment seg) {
		this.name = name;
		this.seg  = seg;
	}
	
	public Segment getSegment() {
		return seg;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public final boolean equals(Object o) {
		if (!(o instanceof Label))
			return false;
		return name.equals(((Label) o).name);
	}
	
	public final int hashCode() {
		return name.hashCode();
	}
	
	public final String toString() {
		return name;
	}
	
}

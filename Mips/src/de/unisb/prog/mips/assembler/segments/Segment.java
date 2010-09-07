package de.unisb.prog.mips.assembler.segments;

public abstract class Segment {
	
	private final Element root = Element.createDummy();
	
	protected final Element add(Element e) {
		root.addBeforeMe(e);
		return e;
	}
	
	protected final void assignOffsets(int start) {
		int ofs = start;
		
		for (Element e = root.next(); e != root; e = e.next()) {
			e.setOffset(ofs);
			ofs = e.nextElementOffset(ofs);
		}
	}
	
	public static enum Type { DATA, TEXT }
	
	public abstract Type getType();
	
}

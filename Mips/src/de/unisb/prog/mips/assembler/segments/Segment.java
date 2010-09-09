package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import de.unisb.prog.mips.simulator.Memory;

public abstract class Segment implements Iterable<Element> {
	
	private final Element.Root root = Element.createRoot();
	
	protected final Element add(Element e) {
		root.prepend(e);
		return e;
	}
	
	
	public final void assignOffsets(int startOffset) {
		int ofs = startOffset;
		
		for (Element e = root.next(); e != root; e = e.next()) {
			e.setOffset(ofs);
			ofs = e.nextElementOffset(ofs);
		}
	}
	
	public final void writeToMem(Memory mem, int addr) {
		relocate(addr);
		for (Element e : this) 
			e.writeToMem(mem, addr + e.getOffset());
	}
	
	public void collectLabels(Map<String, Element> labels) {
		for (Element e : this) {
			String l = e.getLabel();
			if (! l.isEmpty()) 
				labels.put(l, e);
		}
	}
	
	protected abstract void relocate(int startAddress);

	@Override
	public Iterator<Element> iterator() {
		return root.iterator();
	}
	
	public void append(Appendable app) throws IOException {
		for (Element e : this)
			e.append(app);
	}
	
}

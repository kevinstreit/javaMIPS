package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;

public abstract class Segment implements Iterable<Element> {
	
	private final Element.Root root = Element.createRoot();
	private int startAddress = 0;
	
	protected final Element add(Element e) {
		root.prepend(e);
		return e;
	}
	
	public void relocate(int startAddress) {
		this.startAddress = startAddress;
	}
	
	public final void assignOffsets(int startOffset) {
		int ofs = startOffset;
		
		for (Element e = root.next(); e != root; e = e.next()) {
			e.setOffset(ofs);
			ofs = e.nextElementOffset(ofs);
		}
	}
	
	public final void writeToMem(Memory mem) {
		for (Element e : this) 
			e.writeToMem(mem, startAddress + e.getOffset());
	}
	
	public void collectLabels(Map<String, Element> labels) {
		for (Element e : this) {
			String l = e.getLabel();
			if (! l.isEmpty()) 
				labels.put(l, e);
		}
	}

	@Override
	public Iterator<Element> iterator() {
		return root.iterator();
	}
	
	public void append(Appendable app) throws IOException {
		for (Element e : this)
			e.append(app);
	}
	
}

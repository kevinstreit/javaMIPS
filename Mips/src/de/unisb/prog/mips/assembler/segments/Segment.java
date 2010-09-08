package de.unisb.prog.mips.assembler.segments;

import java.util.Iterator;
import java.util.Map;

public abstract class Segment implements Iterable<Element> {
	
	private final Element.Root root = Element.createRoot();
	
	protected final Element add(Element e) {
		root.prepend(e);
		return e;
	}
	
	public final void assignOffsets(int start) {
		int ofs = start;
		
		for (Element e = root.next(); e != root; e = e.next()) {
			e.setOffset(ofs);
			ofs = e.nextElementOffset(ofs);
		}
	}
	
	public Element getLastInserted() {
		return root.prev();
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
	
}

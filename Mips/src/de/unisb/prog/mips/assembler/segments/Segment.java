package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
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
		
		for (Element e : this) {
			e.setOffset(ofs);
			ofs = e.nextElementOffset(ofs);
		}
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

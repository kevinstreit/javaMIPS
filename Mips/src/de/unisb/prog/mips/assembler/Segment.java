package de.unisb.prog.mips.assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Segment extends ArrayList<Element> {
	
	private final Map<Element, Label> labels  = new HashMap<Element, Label>();
	
	public void add(String label, Element element) {
		add(element);
		Label l = null;
		labels.put(element, l);
	}

}

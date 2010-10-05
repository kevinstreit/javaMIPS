package de.unisb.prog.mips.assembler;

import java.util.HashMap;
import java.util.Map;

import de.unisb.prog.mips.assembler.segments.Element;

public class Scope {
	
	private final Map<String, Element> entries = new HashMap<String, Element>();
	private final Map<String, LabelRef> refs = new HashMap<String, LabelRef>();
	private final Scope parent;
	
	public Scope(Scope parent) {
		this.parent = parent;
	}
	
	public Scope() {
		this(null);
	}
	
	public LabelRef createRef(String name) {
		LabelRef ref = refs.get(name);
		if (ref == null) {
			ref = new LabelRef(name);
			refs.put(name, ref);
		}
		Element elm = entries.get(name);
		if (elm != null)
			ref.connectToElement(elm);
		return ref;
	}
	
	public void add(String label, Element elm) {
		entries.put(label, elm);
		LabelRef ref = refs.get(label);
		if (ref != null)
			ref.connectToElement(elm);
	}
	
	public Element lookup(String name) throws LabelNotDefinedException {
		Element elm = entries.get(name);
		if (elm == null && parent != null)
			elm = parent.lookup(name);
		if (elm == null)
			throw new LabelNotDefinedException();
		return elm;
	}
	
	public Scope push() {
		return new Scope(this);
	}

}

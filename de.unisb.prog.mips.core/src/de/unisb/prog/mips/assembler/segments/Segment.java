package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.segments.text.JumpTargetOutOfRange;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.Type;

public abstract class Segment implements Iterable<Element> {
	
	private static final long serialVersionUID = -4901720327612312193L;
	
	private final List<Element> elements = new ArrayList<Element>(1024);
	private final Assembly assembly;
	protected int baseAddress = 0;
	
	private int size = -1;
	
	public static enum Kind {
		DATA, TEXT, NULL
	}
	
	protected Segment(Assembly asm) {
		this.assembly = asm;
	}
	
	public final Assembly getAssembly() {
		return assembly;
	}
	
	public final int getBase() {
		return baseAddress;
	}
	
	protected final Element add(Element e) {
		elements.add(e);
		return e;
	}
	
	public final void assignOffsets(int startOffset) {
		int ofs = startOffset;
		
		for (Element e : this) {
			e.setOffset(ofs);
			ofs = e.nextElementOffset(ofs);
		}
		
		size = ofs - startOffset;
	}
	
	public final void writeToMem(Memory mem, int addr) throws JumpTargetOutOfRange {
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
	
	public Element align(int powerOfTwo) {
		Element res = new Align(this, powerOfTwo);
		add(res);
		return res;
	}
	
	public Element space(int bytes) {
		Element res = new Space(this, bytes);
		add(res);
		return res;
	}
	
	public Element word(List<Expr> vals) {
		Element res = new Values(this, vals, Type.WORD);
		add(res);
		return res;
	}
	

	public final void append(Appendable app) throws IOException {
		for (Element e : this)
			e.append(app);
	}
	
	public final int size() {
		return size;
	}
	
	public final boolean isInside(int address) {
		assembly.assertState(Assembly.State.RELOACTED);
		int offset = address - getBase();
		return 0 <= offset && offset < size();
	}
	
	/**
	 * Get element that sits at a given memory offset.
	 * @param offset The offset of the element in the segment. 
	 * This is not the absolute address but the offset relative to the segment base address.
	 * @return The element at that address, or null if there is no such element.
	 */
	public Element getElementAt(final int addr) {
		Element dummy = new Element(this) {
			@Override protected void appendInternal(Appendable app) throws IOException { }
			@Override public int nextElementOffset(int pos) { return pos; }
			@Override public void writeToMem(Memory mem, int addr) {  }
		};
		int offset = addr - getBase();
		dummy.setOffset(offset);
		
		int index = Collections.binarySearch(elements, dummy, new Comparator<Element>() {
			@Override
			public int compare(Element p, Element q) {
				int l = p.getOffset();
				int r = q.getOffset();
				return (r < l ? 1 : 0) - (l < r ? 1 : 0);
			}
		});
		
		if (index < 0)
			index = -index - 1;
		
		return elements.get(index);
	}
	
	@Override
	public Iterator<Element> iterator() {
		return elements.iterator();
	}
	
	public void relocate(int startAddress) throws JumpTargetOutOfRange {
		this.baseAddress = startAddress;
		relocateInternal(startAddress);
	}
	
	protected abstract void relocateInternal(int startAddress) throws JumpTargetOutOfRange;
	public abstract Kind getKind();
	
}

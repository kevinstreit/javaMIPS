package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.ArrayList;
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
	

	public void append(Appendable app) throws IOException {
		for (Element e : this)
			e.append(app);
	}
	
	public int size() {
		return size;
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

package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.LabelAlreadyDefinedException;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.simulator.MemDumpFormatter;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.Type;

public abstract class Segment implements Iterable<Element> {

	private static final long serialVersionUID = -4901720327612312193L;

	private final List<Element> elements = new ArrayList<Element>(1024);
	protected int baseAddress = 0;
	private int size = -1;
	protected State state = State.BUILDING;

	private final Assembly assembly;


	public static enum Kind {
		DATA { @Override public int startAddress(MemoryLayout layout) { return layout.dataStart(); } },
		TEXT { @Override public int startAddress(MemoryLayout layout) { return layout.textStart(); } },
		NULL { @Override public int startAddress(MemoryLayout layout) { return 0; } };

		public abstract int startAddress(MemoryLayout layout);
	}

	public static enum State {
		BUILDING,                   // the initial state
		PSEUDOS_EXPANDED,           // pseudo-ops are expanded to a real instruction sequence
		// most important: the length of the pseudo is determined
		OFFSETS_ASSIGNED,           // every element in a segment is assigned an offset
		// To this end, every element must know of its exact length.
		RELATIVE_ADDRESSES_PATCHED, // When we have offsets, we can perform relative addressing.
		BASE_ADDRESS_SET,           // Base address is specified
		RELOCATED                   // Relocation is necessary for absolute addresses
	}

	protected Segment(Assembly asm) {
		this.assembly = asm;
	}

	public void addAll(Segment s) {
		for (Element e : s.elements) {
			elements.add(e);
			e.setSegment(this);
		}
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public final void assertState(State s) {
		if (this.state != s)
			throw new IllegalStateException("segment must be in state " + s.name() + " but is in state " + this.state.name());
	}

	public final void assertStateAtLeast(State s) {
		if (this.state.ordinal() < s.ordinal())
			throw new IllegalStateException("segment must be at least in state " + s.name() + " but is in state " + this.state.name());
	}

	public final Assembly getAssembly() {
		return this.assembly;
	}

	public final int getBase() {
		assertStateAtLeast(State.BASE_ADDRESS_SET);
		return this.baseAddress;
	}

	public void setBase(int baseAddress) {
		this.baseAddress = baseAddress;
		state = State.BASE_ADDRESS_SET;
	}

	protected final Element add(Element e) {
		assertState(State.BUILDING);
		this.elements.add(e);
		return e;
	}

	public final boolean validateElements(ErrorReporter<Position> reporter) {
		boolean res = true;
		for (Element e : this.elements)
			res &= e.validate(reporter);
		return res;
	}

	public final void assignOffsets() {
		assertState(State.PSEUDOS_EXPANDED);
		int ofs = 0;
		boolean automaticAlignment = true;

		for (Element e : this) {
			e.setOffset(ofs, automaticAlignment);
			ofs = e.nextElementOffset(e.getOffset());
			if (e instanceof Align) {
				Align a = (Align) e;
				automaticAlignment &= !a.turnsOffAlignment();
			}
		}

		this.size = ofs;
		this.state = State.OFFSETS_ASSIGNED;
	}

	public final void writeToMem(Memory mem) {
		assertState(State.RELOCATED);
		for (Element e : this)
			e.writeToMem(mem, baseAddress + e.getOffset());
	}

	public void relocate(ErrorReporter<Position> reporter) {
		assertState(State.BASE_ADDRESS_SET);
		relocateInternal(baseAddress, reporter);
		this.state = State.RELOCATED;
	}

	public final void append(Appendable app) throws IOException {
		for (Element e : this)
			e.append(app);
	}

	public final int size() {
		assertStateAtLeast(State.PSEUDOS_EXPANDED);
		return this.size;
	}

	public final boolean isInside(int address) {
		assertStateAtLeast(Segment.State.RELOCATED);
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
		assertStateAtLeast(Segment.State.RELOCATED);
		Element dummy = new Element(this) {
			@Override protected void appendInternal(Appendable app) throws IOException { }
			@Override public int nextElementOffset(int pos) { return pos; }
			@Override public void writeToMem(Memory mem, int addr) {  }
		};
		int offset = addr - getBase();
		dummy.setOffset(offset, false);

		int index = Collections.binarySearch(this.elements, dummy, new Comparator<Element>() {
			public int compare(Element p, Element q) {
				int l = p.getOffset();
				int r = q.getOffset();
				return (r < l ? 1 : 0) - (l < r ? 1 : 0);
			}
		});

		if (index < 0)
			index = -index - 2;

		if (index >= this.elements.size())
			throw new IllegalStateException("could not find element at address " + Integer.toHexString(addr));

		return this.elements.get(index);
	}

	public Iterator<Element> iterator() {
		return this.elements.iterator();
	}

	public Element align(int powerOfTwo) {
		assertState(Segment.State.BUILDING);
		Element res = new Align(this, powerOfTwo);
		add(res);
		return res;
	}

	public Element space(int bytes) {
		assertState(Segment.State.BUILDING);
		Element res = new Space(this, bytes);
		add(res);
		return res;
	}

	public Element word(List<Expr> vals) {
		assertState(Segment.State.BUILDING);
		Element res = new Values(this, vals, Type.WORD);
		add(res);
		return res;
	}

	public Label label(String name) throws LabelAlreadyDefinedException {
		assertState(Segment.State.BUILDING);
		Label label = new Label(this, name);
		add(label);
		assembly.defineLabel(label);
		return label;
	}

	public Label global(String name) throws LabelAlreadyDefinedException {
		Label l = label(name);
		assembly.makeGlobal(l);
		return l;
	}

	public final <T> void dump(T out, Memory mem, MemDumpFormatter<T> fmt) throws IOException {
		mem.dump(out, getBase(), size(), fmt);
	}

	protected abstract void relocateInternal(int addr, ErrorReporter<Position> reporter);
	public abstract void prepare(ErrorReporter<Position> reporter);
	public abstract Kind getKind();

	public State getState() {
		return state;
	}

}

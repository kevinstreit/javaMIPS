package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import de.unisb.prog.mips.simulator.Memory;

public class ProxyElement<T extends Element> extends Element {

	protected List<? extends T> elements = Collections.emptyList();

	protected ProxyElement(Segment seg) {
		super(seg);
	}

	public final void set(List<? extends T> elements) {
		this.elements = elements;
	}

	protected final int proxyElements() {
		return elements.size();
	}

	@Override
	public void setOffset(int offset, boolean automaticallyAlign) {
		super.setOffset(offset, automaticallyAlign);
		for (T element : elements) {
			element.setOffset(offset, automaticallyAlign);
			offset = element.nextElementOffset(offset);
		}
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		String sep = "";
		for (T element : elements) {
			app.append(sep);
			element.appendInternal(app);
			sep = "\n";
		}
	}

	@Override
	public int nextElementOffset(int pos) {
		for (T element : elements)
			pos = element.nextElementOffset(pos);
		return pos;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
		for (T element : elements) {
			element.writeToMem(mem, addr);
			addr = element.nextElementOffset(addr);
		}
	}
}

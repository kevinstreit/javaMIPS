package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import de.unisb.prog.mips.simulator.Memory;

public class ProxyElement<T extends Element> extends Element {

	protected List<? extends T> elements = Collections.emptyList();
	private final String comment;
	
	protected ProxyElement(Segment seg) {
		this(seg, "");
	}

	protected ProxyElement(Segment seg, String comment) {
		super(seg);
		this.comment = comment;
	}
	
	public final void set(List<? extends T> elements) {
		this.elements = elements;
	}
	
	@Override
	public void setOffset(int offset) {
		super.setOffset(offset);
		for (T element : elements) {
			element.setOffset(offset);
			offset = element.nextElementOffset(offset);
		}
	}
	
	@Override
	protected void appendInternal(Appendable app) throws IOException {
		if (comment.length() > 0)
			app.append("; begin " + comment + "\n");
		String sep = "";
		for (T element : elements) {
			app.append(sep);
			element.appendInternal(app);
			sep = "\n";
		}
		if (comment.length() > 0)
			app.append("; end " + comment + "\n");
	}

	@Override
	public int nextElementOffset(int pos) {
		for (T element : elements)
			pos = element.nextElementOffset(pos);
		return pos;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
		int base = addr - getOffset();
		for (T element : elements) {
			element.writeToMem(mem, base + element.getOffset());
		}
	}
}

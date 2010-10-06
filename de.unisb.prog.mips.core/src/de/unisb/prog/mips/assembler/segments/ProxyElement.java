package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import de.unisb.prog.mips.simulator.Memory;

public class ProxyElement<T extends Element> extends Element {

	protected List<T> elements = Collections.emptyList();
	
	protected ProxyElement(boolean text) {
		super(text);
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		for (T element : elements)
			element.append(app);
	}

	@Override
	public int nextElementOffset(int pos) {
		for (T element : elements)
			pos = element.nextElementOffset(pos);
		return pos;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
		if (elements.size() > 0) {
			T start = elements.get(0);
			int base = addr - start.getOffset();
			for (T element : elements) 
				element.writeToMem(mem, base + element.getOffset());
		}
	}
}

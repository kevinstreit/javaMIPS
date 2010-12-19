package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.simulator.Memory;

public class Label extends Element {

	private final String label;

	public String getLabel() {
		return label;
	}

	public Label(Segment seg, String label) {
		super(seg);
		this.label = label;
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		app.append(label);
		app.append(':');
	}

	@Override
	public int nextElementOffset(int pos) {
		// we do not consume any space
		return pos;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
		// nothing to do
	}

	public LabelRef getRef() {
		return segment.getAssembly().createRef(label);
	}

}

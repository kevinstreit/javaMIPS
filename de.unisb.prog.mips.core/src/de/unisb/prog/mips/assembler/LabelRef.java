package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;

public class LabelRef implements Address {

	private Element target;
	private final String name;

	LabelRef(Element target) {
		this.target = target;
		this.name = target.getLabel();
	}

	LabelRef(Assembly asm, String label) {
		this.target = Null.getElement();
		this.name = label;
	}

	public Element getElement() {
		if (this.target == null)
			throw new IllegalStateException("tried to reference label \"" + this.name + "\"");
		return this.target;
	}

	void connectToElement(Element elm) {
		assert elm != null;
		this.target = elm;
	}

	String getLabel() {
		return this.name;
	}

	public int eval() {
		return this.target.getOffset();
	}

	public void append(Appendable app) throws IOException {
		app.append(this.name);
	}

	public Segment getSegment() {
		return target.getSegment();
	}

	public boolean isValid() {
		return this.target != null;
	}

}

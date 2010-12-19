package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Label;
import de.unisb.prog.mips.assembler.segments.Segment;

public class LabelRef implements Address {

	private Label target;
	private final String name;

	LabelRef(Label target) {
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

	void connectToElement(Label l) {
		assert l != null;
		this.target = l;
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

	public Label makeLabel(Segment s) throws LabelAlreadyDefinedException {
		return s.label(name);
	}

}

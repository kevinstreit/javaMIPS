package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Space;

public class LabelRef implements Address {

	private static final Assembly NULL_ASSEMBLY = new Assembly();
	private static final Segment NULL_SEGMENT = new Segment(NULL_ASSEMBLY) {
		{
			this.state = State.RELOCATED;
		}
		@Override public Kind getKind() { return Kind.NULL; }
		@Override protected void relocateInternal(int addr, ErrorReporter<Position> reporter) { }
		@Override public void prepare(ErrorReporter<Position> reporter) { }
	};

	public static final LabelRef NULL = new LabelRef(new Space(NULL_SEGMENT, 0));

	private Element target;
	private final String name;

	LabelRef(Element target) {
		this.target = target;
		this.name = target.getLabel();
	}

	LabelRef(String label) {
		this.target = null;
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
		return getElement().getSegment();
	}

	public boolean isValid() {
		return this.target != null;
	}

}

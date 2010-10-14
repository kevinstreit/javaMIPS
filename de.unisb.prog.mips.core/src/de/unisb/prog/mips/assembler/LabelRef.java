package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Space;

public class LabelRef implements Address {

	private static final Assembly NULL_ASSEMBLY = new Assembly();
	private static final Segment NULL_SEGMENT = new Segment(NULL_ASSEMBLY) {
		{
			state = State.RELOCATED;
		}
		@Override public Kind getKind() { return Kind.NULL; }
		@Override protected void relocateInternal(int addr, ErrorReporter<Position> reporter) { }
		@Override public void prepare(ErrorReporter<Position> reporter) { }
	};

	public static final LabelRef NULL = new LabelRef(new Space(NULL_SEGMENT, 0));

	private Element elm;
	private String name;

	LabelRef(Element elm) {
		this.elm = elm;
		this.name = elm.getLabel();
	}

	LabelRef(String label) {
		this.elm = null;
		this.name = label;
	}

	public Element getElement() {
		if (elm == null)
			throw new IllegalStateException("tried to reference label \"" + name + "\"");
		return elm;
	}

	void connectToElement(Element elm) {
		assert elm != null;
		this.elm = elm;
		elm.addReferrer(this);
	}

	String getLabel() {
		return name;
	}

	@Override
	public int eval() {
		return elm.getOffset();
	}

	@Override
	public void append(Appendable app) throws IOException {
		app.append(name);
	}

	@Override
	public Segment getSegment() {
		return getElement().getSegment();
	}

	@Override
	public boolean isValid() {
		return elm != null;
	}

}

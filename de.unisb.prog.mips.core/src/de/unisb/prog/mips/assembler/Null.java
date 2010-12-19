package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.assembler.segments.Label;
import de.unisb.prog.mips.assembler.segments.Segment;

public class Null {

	private static final Assembly NULL_ASSEMBLY = new Assembly();
	private static final Segment NULL_SEGMENT = new Segment(NULL_ASSEMBLY) {
		@Override
		protected void relocateInternal(int addr, ErrorReporter<Position> reporter) {
		}

		@Override
		public void prepare(ErrorReporter<Position> reporter) {
			setBase(0);
			state = State.BASE_ADDRESS_SET;
		}

		@Override
		public Kind getKind() {
			return Kind.NULL;
		}
	};

	private static final String NULL_LABEL = "@null";
	private static Label NULL_ELEMENT = null;
	private static LabelRef NULL_REF = null;

	public static Label getElement() {
		if (NULL_ELEMENT == null) {
			NULL_ELEMENT = new Label(NULL_SEGMENT, NULL_LABEL);
			NULL_SEGMENT.prepare(ErrorReporter.POSITION_STD_REPORTER);
			NULL_SEGMENT.relocate(ErrorReporter.POSITION_STD_REPORTER);
		}
		return NULL_ELEMENT;
	}

	public static LabelRef getRef() {
		if (NULL_REF == null)
			NULL_REF = new LabelRef(getElement());
		return NULL_REF;
	}
}

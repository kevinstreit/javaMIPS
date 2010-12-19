package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;

import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.simulator.Memory;

public abstract class Element implements Expr {

	private int offset;
	private String label = "";
	protected Segment segment;
	protected Position position = Position.ILLEGAL;

	protected Element(Segment seg) {
		this.segment = seg;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setSegment(Segment s) {
		this.segment = s;
	}

	public Segment getSegment() {
		return segment;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int eval() {
		return getOffset();
	}

	public boolean validate(ErrorReporter<Position> reporter) {
		return true;
	}

	public int addressOf() {
		segment.assertState(Segment.State.RELOCATED);
		return segment.getBase() + getOffset();
	}

	public final void append(Appendable app) throws IOException {
		if (! label.isEmpty()) {
			app.append(label);
			app.append(":\n");
		}
		appendInternal(app);
		app.append('\n');
	}

	protected abstract void appendInternal(Appendable app) throws IOException;
	public abstract int nextElementOffset(int pos);
	public abstract void writeToMem(Memory mem, int addr);

}

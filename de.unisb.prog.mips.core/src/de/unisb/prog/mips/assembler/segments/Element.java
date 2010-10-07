package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.simulator.Memory;

public abstract class Element implements Expr {
	
	private int offset;
	private String label = "";
	private List<LabelRef> referers = null;
	private final Segment segment;
	private int lineNumber;
	
	protected Element(Segment seg) {
		this.segment = seg;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
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
	
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public int eval() {
		return getOffset();
	}
	
	public void addReferrer(LabelRef r) {
		if (referers == null)
			referers = new LinkedList<LabelRef>();
		referers.add(r);
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

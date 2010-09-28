package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Memory;

public abstract class Element extends ListItem<Element, Element.Root> implements Expr<Integer> {
	
	public static class Root extends Element implements Iterable<Element> {
		Root() { super (Reg.zero); }
		@Override public int nextElementOffset(int pos) { return pos; }
		@Override public void writeToMem(Memory mem, int addr) { } 
		@Override protected void appendInternal(Appendable app) throws IOException { }
	}
	
	private final Reg relative;
	private int offset;
	private String label = "";
	private List<LabelRef> referers = null;
	
	protected Element(Reg relative) {
		this.relative = relative;
	}

	public Element me() {
		return this;
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
	
	public Integer eval() {
		return getOffset();
	}
	
	public void addReferrer(LabelRef r) {
		if (referers == null)
			referers = new LinkedList<LabelRef>();
		referers.add(r);
	}
	
	protected final void replaceBy(Element.Root list) {
		replaceByList(list);
		Element head = list.next();
		head.label = this.label;
		head.referers = this.referers;
	}
	
	public static Element.Root createRoot() {
		return new Root();
	}
	
	public final void append(Appendable app) throws IOException {
		if (! label.isEmpty()) {
			app.append(label);
			app.append(":\n");
		}
		appendInternal(app);
		app.append('\n');
	}
	
	public Reg relativeTo() {
		return relative;
	}
	
	protected abstract void appendInternal(Appendable app) throws IOException;
	public abstract int nextElementOffset(int pos);
	public abstract void writeToMem(Memory mem, int addr);

}
package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.util.Option;

public class LabelRef implements Address {
	private Element elm;
	private String name;
	
	public static final LabelRef NULL = new LabelRef(new Element(true) {
		
		{
			setLabel("@");
		}
		
		@Override
		protected void appendInternal(Appendable app) throws IOException {
		}

		@Override
		public int nextElementOffset(int pos) {
			return pos;
		}

		@Override
		public void writeToMem(Memory mem, int addr) {
		}
		
	});
	
	LabelRef(Element elm) {
		this.elm = elm;
		this.name = elm.getLabel();
	}
	
	LabelRef(String label) {
		this.elm = null;
		this.name = label;
	}

	public Element getElement() {
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
	public boolean isText() {
		return getElement().isText();
	}

}

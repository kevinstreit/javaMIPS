package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.Map;

import de.unisb.prog.mips.assembler.segments.Element;

public class LabelRef implements Expr<Integer> {
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

	public void patch(Map<String, Element> labels) throws LabelNotDefinedException {
		if (elm == null) {
			elm = labels.get(name);
			if (elm == null)
				throw new LabelNotDefinedException();
			elm.addReferrer(this);
		}
	}

	public Element getElement() {
		return elm;
	}

	@Override
	public Integer eval() {
		return elm.getOffset();
	}

	@Override
	public void append(Appendable app) throws IOException {
		app.append(name);
	}

}

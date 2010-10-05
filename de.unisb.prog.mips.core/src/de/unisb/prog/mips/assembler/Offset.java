package de.unisb.prog.mips.assembler;

import java.io.IOException;

public class Offset implements Address {
	
	private final Expr cnst;
	private final LabelRef label;
	
	public Offset(LabelRef label, Expr cnst) {
		this.label = label;
		this.cnst = cnst;
	}
	
	public Offset(LabelRef label) {
		this.label = label;
		this.cnst = Expressions.ZERO;
	}
	
	public void append(Appendable app) throws IOException {
		label.append(app);
		cnst.append(app);
	}
	
	public LabelRef getLabel() {
		return label;
	}
	
	@Override
	public int eval() {
		return cnst.eval() + (label != null ? label.eval() : 0);
	}

	@Override
	public boolean isText() {
		return label.getElement().isText();
	}

}

package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.util.Option;

public class Offset implements Address {
	
	private final Option<Expr> cnst;
	private final Option<LabelRef> label;
	
	public Offset(Option<LabelRef> label, Option<Expr> cnst) {
		this.label = label;
		this.cnst = cnst;
	}
	
	
	public void append(Appendable app) throws IOException {
		getLabel().append(app);
		cnst.otherwise(Expressions.ZERO).append(app);
	}
	
	public LabelRef getLabel() {
		return label.otherwise(LabelRef.NULL);
	}
	
	@Override
	public int eval() {
		return cnst.otherwise(Expressions.ZERO).eval() + getLabel().eval();
	}

	@Override
	public boolean isText() {
		return getLabel().getElement().isText();
	}

}

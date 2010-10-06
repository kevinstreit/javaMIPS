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
		label.otherwise(LabelRef.NULL).append(app);
		cnst.otherwise(Expressions.ZERO).append(app);
	}
	
	public Option<LabelRef> getLabel() {
		return label;
	}
	
	public Option<Expr> getExpr() {
		return cnst;
	}
	
	@Override
	public int eval() {
		return cnst.otherwise(Expressions.ZERO).eval() + label.otherwise(LabelRef.NULL).eval();
	}

	@Override
	public boolean isText() {
		return getLabel().otherwise(LabelRef.NULL).getElement().isText();
	}

}

package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Segment;
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
		getExpr().append(app);
	}
	
	public Option<LabelRef> getLabelOption() {
		return label;
	}
	
	public Option<Expr> getExprOption() {
		return cnst;
	}
	
	public LabelRef getLabel() {
		return label.otherwise(LabelRef.NULL);
	}
	
	public Expr getExpr() {
		return cnst.otherwise(Expressions.constantInt(0xdeadbeaf));
	}
	
	@Override
	public int eval() {
		return getExpr().eval() + getLabel().eval();
	}

	@Override
	public Segment getSegment() {
		return getLabel().getSegment();
	}

}

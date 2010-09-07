package de.unisb.prog.mips.assembler.segments;

import java.util.List;

import de.unisb.prog.mips.assembler.Expr;

public class Values extends Element {
	
	private final int elementSize;
	private final List<Expr<Integer>> values;
	
	Values(List<Expr<Integer>> values, int elementSize) {
		this.elementSize = elementSize;
		this.values = values;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + elementSize * values.size();
	}

}

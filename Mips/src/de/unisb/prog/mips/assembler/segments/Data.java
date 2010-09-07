package de.unisb.prog.mips.assembler.segments;

import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;

public class Data extends Segment {

	@Override
	public Type getType() {
		return Type.DATA;
	}

	public void values(List<Expr<Integer>> vals, int elementSize) {
		add(new Values(vals, elementSize));
	}
	
	public void string(String str, boolean zeroTerminate) {
		List<Expr<Integer>> list = new ArrayList<Expr<Integer>>(str.length() + (zeroTerminate ? 1 : 0));
		for (int i = 0, n = str.length(); i < n; i++) 
			list.add(Expressions.intConstant(str.charAt(i)));
		add(new Values(list, 1));
	}

}

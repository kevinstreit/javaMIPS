package de.unisb.prog.mips.assembler.segments;

import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.simulator.Type;

public class Data extends Segment {

	public void values(List<Expr<Integer>> vals, Type tp) {
		add(new Values(vals, tp));
	}
	
	public void string(String str, boolean zeroTerminate) {
		List<Expr<Integer>> list = new ArrayList<Expr<Integer>>(str.length() + (zeroTerminate ? 1 : 0));
		for (int i = 0, n = str.length(); i < n; i++) 
			list.add(Expressions.constantInt(str.charAt(i)));
		add(new Values(list, Type.BYTE));
	}

}

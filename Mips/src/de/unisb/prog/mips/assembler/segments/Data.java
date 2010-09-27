package de.unisb.prog.mips.assembler.segments;

import java.util.Collections;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.text.Constant;
import de.unisb.prog.mips.simulator.Type;

public class Data extends Segment {

	public Element values(List<Expr<Integer>> vals, Type tp) {
		return add(new Values(vals, tp));
	}
	
	public Element string(String str, boolean zeroTerminate) {
		return add(new Str(str, zeroTerminate));
	}
	
	public Element space(int bytes) {
		return add(new Space(bytes, Reg.gp));
	}
	
	@Override
	protected void relocate(int startAddress) {
	}

	@Override
	public Element word(int w) {
		return values(Collections.singletonList(Expressions.constantInt(w)), Type.WORD);
	}

}

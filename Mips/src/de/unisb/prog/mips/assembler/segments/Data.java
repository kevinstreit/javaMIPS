package de.unisb.prog.mips.assembler.segments;

import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
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
	
	public Element align(int powerOfTwo) {
		return add(new Align(powerOfTwo, Reg.gp));
	}

}

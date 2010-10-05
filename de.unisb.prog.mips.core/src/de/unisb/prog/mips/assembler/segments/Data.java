package de.unisb.prog.mips.assembler.segments;

import java.util.List;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.simulator.Type;

public class Data extends Segment {

	public Data(Assembly asm) {
		super(asm);
	}

	public Element values(List<Expr> vals, Type tp) {
		return add(new Values(vals, tp));
	}
	
	public Element string(String str, boolean zeroTerminate) {
		return add(new Str(str, zeroTerminate));
	}
	
	public Element space(int bytes) {
		return add(new Space(bytes, Reg.gp, false));
	}
	
	@Override
	protected void relocate(int startAddress) {
	}

	public Element align(int powerOfTwo) {
		return add(new Align(powerOfTwo, false));
	}

}

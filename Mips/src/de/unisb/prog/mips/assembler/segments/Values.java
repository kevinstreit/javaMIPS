package de.unisb.prog.mips.assembler.segments;

import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.simulator.CoarseMemory;
import de.unisb.prog.mips.simulator.Type;

public class Values extends Element {
	
	private final Type elementType;
	private final List<Expr<Integer>> values;
	
	Values(List<Expr<Integer>> values, Type elementType) {
		this.elementType = elementType;
		this.values = values;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + elementType.sizeof() * values.size();
	}

	@Override
	public void writeToMem(CoarseMemory mem, int addr) {
		for (Expr<Integer> e : values) {
			mem.store(addr, e.eval(), elementType);
			addr += elementType.sizeof();
		}
	}

}

package de.unisb.prog.mips.assembler.segments;

import java.io.IOException;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.simulator.Memory;
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
	public void writeToMem(Memory mem, int addr) {
		for (Expr<Integer> e : values) {
			mem.store(addr, e.eval(), elementType);
			addr += elementType.sizeof();
		}
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		String prefix = "." + elementType.name().toLowerCase() + ":";
		for (Expr<Integer> e : values) {
			app.append(prefix);
			e.append(app);
			prefix = ", ";
		}
	}
}

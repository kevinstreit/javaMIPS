package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.RegNameDisassembler;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.Type;

class Insn extends Element {

	protected int word;

	Insn(Segment seg, int word) {
		super(seg);
		this.word = word;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + 4;
	}

	@Override
	public void writeToMem(Memory mem, int addr) {
		mem.store(addr, word, Type.WORD);
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		app.append(toString());
	}

	@Override
	public String toString() {
		return RegNameDisassembler.INSTANCE.disasm(word);
	}

	protected static final boolean validateAddress(Position p, Address addr, ErrorReporter<Position> reporter) {
		if (! addr.isValid()) {
			reporter.error("invalid address: " + Expressions.toString(addr), p);
			return false;
		}
		return true;
	}

	public static Insn nop(Segment s) {
		return new Insn(s, 0);
	}

}

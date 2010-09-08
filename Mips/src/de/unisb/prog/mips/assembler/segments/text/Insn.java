package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.simulator.CoarseMemory;
import de.unisb.prog.mips.simulator.Type;

class Insn extends Element {
	
	protected int word;
	
	Insn(int word) {
		this.word = word;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + 4;
	}

	@Override
	public void writeToMem(CoarseMemory mem, int addr) {
		mem.store(addr, word, Type.WORD);
	}

	
}

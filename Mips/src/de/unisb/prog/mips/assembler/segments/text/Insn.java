package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.segments.Element;

class Insn extends Element {
	
	protected int word;
	
	Insn(int word) {
		this.word = word;
	}

	@Override
	public int nextElementOffset(int pos) {
		return pos + 4;
	}

	
}

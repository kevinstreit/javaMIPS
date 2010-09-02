package de.unisb.prog.mips.assembler.text;

import java.util.Map;

import de.unisb.prog.mips.assembler.Element;
import de.unisb.prog.mips.assembler.Label;

class Insn implements Element {
	
	protected int word;
	
	Insn(int word) {
		this.word = word;
	}

	@Override
	public int size() {
		return 4;
	}

	@Override
	public void patchLabels(Map<String, Label> labels) {
	}

}

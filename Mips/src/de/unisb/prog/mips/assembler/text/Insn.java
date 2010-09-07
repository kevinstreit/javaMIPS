package de.unisb.prog.mips.assembler.text;

import java.util.Collections;
import java.util.List;

import de.unisb.prog.mips.assembler.Element;

abstract class Insn implements Element {
	
	protected int word;
	protected int offset;
	
	Insn(int word) {
		this.word = word;
	}

	@Override
	public int nextElementPos(int pos) {
		return pos + 4;
	}
	
	protected List<Insn> generateAddr(int baseReg, int tgtReg, int addr) {
		return Collections.emptyList();
	}

}

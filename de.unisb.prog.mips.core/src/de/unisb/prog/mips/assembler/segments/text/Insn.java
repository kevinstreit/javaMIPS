package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.RegNameDisassembler;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.Type;

class Insn extends Element {
	
	protected int word;
	
	Insn(int word) {
		super(Reg.zero, true);
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
		app.append(RegNameDisassembler.INSTANCE.disasm(word));
	}

}

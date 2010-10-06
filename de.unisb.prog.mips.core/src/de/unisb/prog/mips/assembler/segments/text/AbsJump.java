package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Instruction;

class AbsJump extends LabelRefInsn {
	
	AbsJump(Segment seg, Instruction insn, Address ref) {
		super(seg, insn.encodeOpcodeInto(0), ref);
	}
	
	public void rewrite(int startAddress) {
		word = Instruction.FIELD_TARGET.insert(word, startAddress + ref.eval());
	}

}

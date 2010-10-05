package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.insn.Instruction;

class AbsJump extends LabelRefInsn {
	
	AbsJump(Instruction insn, Address ref) {
		super(insn.encodeOpcodeInto(0), ref);
	}
	
	public void rewrite(int startAddress) {
		word = Instruction.FIELD_TARGET.insert(word, startAddress + ref.eval());
	}

}

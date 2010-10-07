package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Instruction;

class AbsJump extends LabelRefInsn implements Relocateable {
	
	AbsJump(Segment seg, Instruction insn, Address addr) {
		super(seg, insn.encodeOpcodeInto(0), addr);
	}
	
	@Override
	public void relocate(int startAddress) throws JumpTargetOutOfRange {
		int addr = startAddress + ref.eval();
		if ((addr & 0x0fffffff) != addr)
			throw new JumpTargetOutOfRange();
		word = Instruction.FIELD_TARGET.insert(word, addr >>> 2);
	}

}

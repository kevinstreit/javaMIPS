package de.unisb.prog.mips.assembler.segments.text;


import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Immediate;
import de.unisb.prog.mips.insn.Instruction;

class RelJump extends LabelRefInsn {
	
	RelJump(Instruction insn, Reg rs, Reg rt, LabelRef ref) {
		this(rt.encodeInto(rs.encodeInto(insn.encodeOpcodeInto(0), Instruction.FIELD_RS), Instruction.FIELD_RT), ref);
	}
	
	RelJump(Instruction insn, Reg rs, LabelRef ref) {
		this(rs.encodeInto(insn.encodeOpcodeInto(0), Instruction.FIELD_RS), ref);
	}
	
	RelJump(int word, LabelRef ref) {
		super(word, ref);
	}
	
	public void rewrite() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		int labelOffset = ref.eval();
		
		if ((labelOffset & 3) != 0)
			throw new JumpTargetNotAligned();
		
		int rel = (getOffset() - labelOffset) >>> 2;
		if (! Immediate.SEXT_16.fits(rel))
			throw new JumpTargetOutOfRange();
		
		Instruction.FIELD_IMM.insert(word, rel);
	}

}

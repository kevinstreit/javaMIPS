package de.unisb.prog.mips.assembler.segments.text;


import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

class RelJump extends LabelRefInsn {
	
	RelJump(Opcode opc, Reg rs, Reg rt, LabelRef ref) {
		super(Encode.i(opc, rs.ordinal(), rt.ordinal(), 0), ref);
	}
	
	RelJump(RegImm ri, Reg rs, LabelRef ref) {
		super(Encode.i(ri, rs.ordinal(), 0), ref);
	}

	public void rewrite() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		int labelOffset = ref.eval();
		
		if ((labelOffset & 3) != 0)
			throw new JumpTargetNotAligned();
		
		int rel = (getOffset() - labelOffset) >>> 2;
		if (! Encode.immFitsISign(rel))
			throw new JumpTargetOutOfRange();
		
		word = Encode.setImmI(word, rel);
	}

}

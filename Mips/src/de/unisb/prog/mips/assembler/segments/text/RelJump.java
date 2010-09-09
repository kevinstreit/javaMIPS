package de.unisb.prog.mips.assembler.segments.text;


import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

class RelJump extends LabelRefInsn {
	
	RelJump(Opcode opc, int rs, int rt, Expr<Integer> e) {
		super(Encode.i(opc, rs, rt, 0), e);
	}
	
	RelJump(RegImm ri, int rs, Expr<Integer> e) {
		super(Encode.i(ri, rs, 0), e);
	}

	public void rewrite() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		int labelOffset = exp.eval();
		
		if ((labelOffset & 3) != 0)
			throw new JumpTargetNotAligned();
		
		int rel = (labelOffset - getOffset()) >> 2;
		if (! Encode.immFitsI(rel))
			throw new JumpTargetOutOfRange();
		
		word = Encode.setImmI(word, rel);
	}

}

package de.unisb.prog.mips.assembler.text;


import de.unisb.prog.mips.assembler.Label;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

class RelJump extends LabelRefInsn {
	
	private static int FARTHEST_NEG = Short.MIN_VALUE;
	private static int FARTHEST_POS = Short.MAX_VALUE;
	
	RelJump(Opcode opc, int rs, int rt, LabelRef ref) {
		super(Encode.i(opc, rs, rt, 0), ref);
	}
	
	RelJump(RegImm ri, int rs, LabelRef ref) {
		super(Encode.i(ri, rs, 0), ref);
	}

	public void patch() {
		Label l = ref.getLabel();
		int labelOffset = l.getOffset();
		
		if ((labelOffset & 3) != 0)
			throw new JumpTargetNotAligned();
		
		int rel = (offset() - l.getOffset()) / 4;
		if (rel < FARTHEST_NEG || rel > FARTHEST_POS)
			throw new JumpTargetOutOfRange();
		
		word = (word & 0xffff0000) | (rel & 0xffff);
	}

}

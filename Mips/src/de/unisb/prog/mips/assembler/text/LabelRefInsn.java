package de.unisb.prog.mips.assembler.text;

import de.unisb.prog.mips.assembler.LabelRef;

abstract class LabelRefInsn extends Insn {
	
	protected final LabelRef ref;
	
	LabelRefInsn(int enc, LabelRef ref) {
		super(enc);
		this.ref = ref;
	}

}

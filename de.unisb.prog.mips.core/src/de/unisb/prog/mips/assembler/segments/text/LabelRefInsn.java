package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.insn.Opcode;

abstract class LabelRefInsn extends Insn {
	
	protected final LabelRef ref;
	
	LabelRefInsn(int word, LabelRef ref) {
		super(word);
		this.ref = ref;
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		super.appendInternal(app);
		app.append(" ; ");
		ref.append(app);
	}
	
	
	
}

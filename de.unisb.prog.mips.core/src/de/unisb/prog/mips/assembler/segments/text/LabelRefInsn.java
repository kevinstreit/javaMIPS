package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Address;

abstract class LabelRefInsn extends Insn {
	
	protected final Address ref;
	
	LabelRefInsn(int word, Address ref) {
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

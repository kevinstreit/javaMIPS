package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.segments.Segment;

abstract class LabelRefInsn extends Insn {
	
	protected final Address ref;
	
	LabelRefInsn(Segment seg, int word, Address ref) {
		super(seg, word);
		this.ref = ref;
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		super.appendInternal(app);
		app.append(" ; ");
		ref.append(app);
	}
	
	@Override
	public boolean validate(ErrorReporter<Position> reporter) {
		return validateAddress(this, ref, reporter);
	}
	
}

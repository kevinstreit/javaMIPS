package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.assembler.segments.Label;

@SuppressWarnings("serial")
public class LabelAlreadyDefinedException extends Exception {

	public final Label label;

	public LabelAlreadyDefinedException(Label label) {
		super(label.getLabel());
		this.label = label;
	}

}

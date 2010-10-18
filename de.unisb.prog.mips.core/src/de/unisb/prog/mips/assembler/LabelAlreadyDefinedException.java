package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.assembler.segments.Element;

@SuppressWarnings("serial")
public class LabelAlreadyDefinedException extends Exception {

	public final Element definer;

	public LabelAlreadyDefinedException(Element definer) {
		super(definer.getLabel());
		this.definer = definer;
	}

}

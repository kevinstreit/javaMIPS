package de.unisb.prog.mips.assembler;

@SuppressWarnings("serial")
public class LabelNotDefinedException extends AssemblerException {
	
	public final String name;

	public LabelNotDefinedException(String name) {
		this.name = name;
	}

}

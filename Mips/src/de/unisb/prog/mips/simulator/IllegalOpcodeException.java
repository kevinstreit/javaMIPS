package de.unisb.prog.mips.simulator;

public class IllegalOpcodeException extends ProcessorException {

	private static final long serialVersionUID = 177862779100299638L;

	public IllegalOpcodeException(ProcessorState s) {
		super(s);
	}

}

package de.unisb.prog.mips.simulator;

public class UnalignedMemoryException extends ProcessorException {

	private static final long serialVersionUID = 3271763431641227333L;
	
	public final int faultyAddress;

	public UnalignedMemoryException(ProcessorState s, int faultyAddress) {
		super(s);
		this.faultyAddress = faultyAddress;
	}

}

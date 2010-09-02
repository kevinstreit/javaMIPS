package de.unisb.prog.mips.simulator;


public class ProcessorException extends RuntimeException {
	
	private static final long serialVersionUID = 6278155750591090629L;
	
	private final ProcessorState state;
	
	public ProcessorException(ProcessorState s) {
		this.state = s.clone();
	}
	
	public ProcessorState getState() {
		return state;
	}
}

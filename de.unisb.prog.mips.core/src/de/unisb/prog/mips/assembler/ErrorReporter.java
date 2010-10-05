package de.unisb.prog.mips.assembler;

public interface ErrorReporter<T> {
	
	public void error(String msg, T arg);
	public void warning(String msg, T arg);

}

package de.unisb.prog.mips.parser.ui.launching;

import java.io.PrintStream;

import de.unisb.prog.mips.os.SysCallImplementation;

public class UISyscallImpl implements SysCallImplementation {

	private final PrintStream out;
	
	public UISyscallImpl(PrintStream output) {
		this.out = output;
	}

	@Override
	public void print(CharSequence s) {
		this.out.print(s);
	}
	
	@Override
	public void print(char ch) {
		this.out.print(ch);
	}
	
	@Override
	public void print(int v) {
		this.out.print(v);
	}

}

package de.unisb.prog.mips.parser.ui.launching;

import java.io.PrintStream;

import de.unisb.prog.mips.os.SysCallImplementation;
import de.unisb.prog.mips.parser.ui.MIPSCore;

public class UISyscallImpl implements SysCallImplementation {

	private final PrintStream out;
	
	public UISyscallImpl(PrintStream output) {
		this.out = output;
	}

	@Override
	public void print(CharSequence s) {
		this.out.print(s);
		this.out.flush();
	}
	
	@Override
	public void print(char ch) {
		this.out.print(ch);
		this.out.flush();
	}
	
	@Override
	public void print(int v) {
		this.out.print(v);
		this.out.flush();
	}

	@Override
	public void exit(int ret) {
		MIPSCore.getInstance().setExitCode(ret);
	}

}

package de.unisb.prog.mips.parser.ui.launching;

import java.io.PrintStream;

import de.unisb.prog.mips.os.SysCallImplementation;
import de.unisb.prog.mips.parser.ui.MIPSCore;

public class UISyscallImpl implements SysCallImplementation {

	private final PrintStream out;

	public UISyscallImpl(PrintStream output) {
		out = output;
	}

	public void print(CharSequence s) {
		out.print(s);
		out.flush();
	}

	public void print(char ch) {
		out.print(ch);
		out.flush();
	}

	public void print(int v) {
		out.print(v);
		out.flush();
	}

	public void exit(int ret) {
		MIPSCore.getInstance().setExitCode(ret);
	}

}

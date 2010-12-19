package de.unisb.prog.mips.os;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class DefaultSysCallImpl implements SysCallImplementation {

	private final PrintStream out;
	private final InputStream in;

	public DefaultSysCallImpl(PrintStream out, InputStream in) {
		this.out = out;
		this.in  = in;
	}

	public void print(CharSequence s) {
		out.print(s);
	}

	public void print(char ch) {
		out.print(ch);
	}

	public void print(int v) {
		out.print(v);
	}

	public void exit(int ret) {
		// Nothing to do
	}

	public int readString(byte[] buffer) {
		try {
			return in.read(buffer);
		} catch (IOException e) {
			return 0;
		}
	}

	public int readInt() {
		try {
			return in.read();
		} catch (IOException e) {
			return 0;
		}
	}

	public char readChar() {
		return (char) readInt();
	}

	public static final SysCallImplementation INSTANCE = new DefaultSysCallImpl(System.out, System.in);
}

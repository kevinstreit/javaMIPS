package de.unisb.prog.mips.os;

import java.io.IOException;

public interface SysCallImplementation {

	void print(int v);
	void print(char ch);
	void print(CharSequence s);

	int readString(byte[] buffer);
	int readInt();
	char readChar();

	void exit(int ret);

	public static SysCallImplementation DEFAULT = new SysCallImplementation() {

		public void print(CharSequence s) {
			System.out.print(s);
		}

		public void print(char ch) {
			System.out.print(ch);
		}

		public void print(int v) {
			System.out.print(v);
		}

		public void exit(int ret) {
			// Nothing to do
		}

		public int readString(byte[] buffer) {
			try {
				return System.in.read(buffer);
			} catch (IOException e) {
				return 0;
			}
		}

		public int readInt() {
			try {
				return System.in.read();
			} catch (IOException e) {
				return 0;
			}
		}

		public char readChar() {
			return (char) readInt();
		}

	};

}

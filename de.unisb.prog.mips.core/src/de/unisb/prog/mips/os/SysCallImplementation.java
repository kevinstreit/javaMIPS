package de.unisb.prog.mips.os;


public interface SysCallImplementation {

	void print(int v);
	void print(char ch);
	void print(CharSequence s);

	int readString(byte[] buffer);
	int readInt();
	char readChar();

	void exit(int ret);
}

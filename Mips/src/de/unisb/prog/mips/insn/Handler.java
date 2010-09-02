package de.unisb.prog.mips.insn;

public interface Handler {
	
	void r(int rs, int rt, int rd, int shamt, int funct);
	void i(int op, int rs, int rt, int imm);
	void j(int op, int imm);
	void f(int fmt, int ft, int fs, int fd, int funct);
	

}

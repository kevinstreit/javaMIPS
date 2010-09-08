package de.unisb.prog.mips.insn;

public interface Handler<T> {
	
	T r(int rs, int rt, int rd, int shamt, int funct);
	T i(int op, int rs, int rt, int imm);
	T j(int op, int imm);
	T f(int fmt, int ft, int fs, int fd, int funct);

}

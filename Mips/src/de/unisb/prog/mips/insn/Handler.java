package de.unisb.prog.mips.insn;

public interface Handler<T> {
	
	T r(IntFunct funct, int rs, int rt, int rd, int shamt);
	T i(Opcode opc, int rs, int rt, int imm);
	T i(RegImm ri, int rs, int imm);
	T j(Opcode opc, int imm);

}

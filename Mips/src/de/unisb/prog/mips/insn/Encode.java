package de.unisb.prog.mips.insn;

public class Encode {
	
	public static int r(IntFunct funct, int rs, int rt, int rd, int shamt) {
		int res = rs & 0x1f;
		res = (res << 5) | (rt & 0x1f);
		res = (res << 5) | (rd & 0x1f);
		res = (res << 5) | (shamt & 0x1f);
		return (res << 6) | (funct.ordinal() & 0x1f);
	}
	
	public static int r(IntFunct funct, int rs, int rt, int rd) {
		return r(funct, rs, rt, rd, 0);
	}
	
	public static int i(Opcode opc, int rs, int rt, int imm) {
		int res = opc.ordinal();
		res <<= 5; res |= (rs & 0x1f);
		res <<= 5; res |= (rt & 0x1f);
		res <<= 16; res |= (imm & 0xffff);
		return res;
	}
	
	public static int i(Opcode opc, int rs, int imm) {
		return i(opc, rs, 0, imm);
	}
	
	public static int i(RegImm ri, int rs, int imm) {
		return i(Opcode.regimm, rs, ri.ordinal(), imm);
	}
	
	public static int j(Opcode opc, int imm) {
		return (opc.ordinal() << 26) | (imm & ((1 << 26) - 1));
	}
	
	public static int setImmI(int insn, int value) {
		return setImm(insn, 16, value);
	}
	
	public static int setImmJ(int insn, int value) {
		return setImm(insn, 26, value);
	}
	
	private static int setImm(int insn, int fieldWidth, int value) {
		int mask = (1 << fieldWidth) - 1;
		return (insn & ~mask) | (value & mask);
	}
	
	private static boolean immFits(int value, int fieldWidth) {
		int pos = (1 << (fieldWidth - 1)) - 1;
		int neg = -pos - 1;
		return value >= neg && value <= pos;
	}
	
	public static boolean immFitsI(int value) {
		return immFits(value, 16);
	}
	
	public static boolean immFitsJ(int value) {
		return immFits(value, 26);
	}
}

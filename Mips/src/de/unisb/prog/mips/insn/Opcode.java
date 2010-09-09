package de.unisb.prog.mips.insn;

import de.unisb.prog.mips.simulator.Type;

public enum Opcode {
	
	/* 00 */ special,
	/* 01 */ regimm,
	/* 02 */ j,
	/* 03 */ jal,
	/* 04 */ beq,
	/* 05 */ bne,
	/* 06 */ blez,
	/* 07 */ bgtz,
	
	/* 08 */ addi,
	/* 09 */ addiu,
	/* 0a */ slti,
	/* 0b */ sltiu,
	/* 0c */ andi(false),
	/* 0d */ ori(false),
	/* 0e */ xori(false),
	/* 0f */ lui(false),
	
	/* 10 */ _10,
	/* 11 */ _11,
	/* 12 */ _12,
	/* 13 */ _13,
	/* 14 */ _14,
	/* 15 */ _15,
	/* 16 */ _16,
	/* 17 */ _17,
	/* 18 */ _18,
	/* 19 */ _19,
	/* 1a */ _1a,
	/* 1b */ _1b,
	/* 1c */ _1c,
	/* 1d */ _1d,
	/* 1e */ _1e,
	/* 1f */ _1f,
	
	/* 20 */ lb,
	/* 21 */ lh,
	/* 22 */ lwl,
	/* 23 */ lw,
	/* 24 */ lbu,
	/* 25 */ lhu,
	/* 26 */ lwr,
	/* 27 */ _27,
	/* 28 */ sb,
	/* 29 */ sh,
	/* 2a */ swl,
	/* 2b */ sw,
	/* 2c */ _2c,
	/* 2d */ _2d,
	/* 2e */ swr,
	/* 2f */ _2f,
	
	/* 30 */ _30,
	/* 31 */ _31,
	/* 32 */ _32,
	/* 33 */ _33,
	/* 34 */ _34,
	/* 35 */ _35,
	/* 16 */ _36,
	/* 37 */ _37,
	/* 38 */ _38,
	/* 39 */ _39,
	/* 3a */ _3a,
	/* 3b */ _3b,
	/* 3c */ _3c,
	/* 3d */ _3d,
	/* 3e */ _3e,
	/* 3f */ _3f;
	
	private final boolean signExtend;
	
	private Opcode(boolean signExtend) {
		this.signExtend = signExtend;
	}
	
	private Opcode() {
		this(true);
	}
	
	public int extendImm(int imm) {
		return signExtend ? Type.HALF.signExtend(imm) : imm;
	}
	
}

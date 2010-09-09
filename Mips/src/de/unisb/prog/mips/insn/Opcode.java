package de.unisb.prog.mips.insn;

import java.util.EnumSet;
import java.util.Set;

import de.unisb.prog.mips.simulator.Type;

public enum Opcode implements Instruction {
	
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
	/* 0c */ andi(ZEXT),
	/* 0d */ ori(ZEXT),
	/* 0e */ xori(ZEXT),
	/* 0f */ lui(ZEXT),
	
	/* 10 */ _10,
	/* 11 */ _11,
	/* 12 */ _12,
	/* 13 */ _13,
	/* 14 */ beql(BRANCH),
	/* 15 */ bnel(BRANCH),
	/* 16 */ blezl(BRANCH),
	/* 17 */ bgtzl(BRANCH),
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
	/* 2f */ cache,
	
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
	/* 3f */ halt(EnumSet.of(Attribute.HALT));
	
	private final Set<Attribute> attributes;
	
	private Opcode(Set<Attribute> attr) {
		attributes = attr;
	}
	
	private Opcode() {
		this(SEXT);
	}
	
	public int extendImm(int imm) {
		return attributes.contains(Attribute.SEXT_IMM_16) ? Type.HALF.signExtend(imm) : imm;
	}

	@Override
	public boolean has(Attribute attr) {
		return attributes.contains(attr);
	}
	
}

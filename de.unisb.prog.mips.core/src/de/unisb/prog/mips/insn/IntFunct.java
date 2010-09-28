package de.unisb.prog.mips.insn;

import java.util.EnumSet;
import java.util.Set;

public enum IntFunct implements Instruction {
	
	/* 00 */ sll(EnumSet.of(Attribute.SHAMT)),
	/* 01 */ _01,
	/* 02 */ srl(EnumSet.of(Attribute.SHAMT)),
	/* 03 */ sra(EnumSet.of(Attribute.SHAMT)),
	/* 04 */ sllv,
	/* 05 */ _05,
	/* 06 */ srlv,
	/* 07 */ srav,
	/* 08 */ jr(JUMP),
	/* 09 */ jalr(JUMP),
	/* 0a */ movz,
	/* 0b */ movn,
	/* 0c */ syscall,
	/* 0d */ brk,
	/* 0e */ _0e,
	/* 0f */ sync,
	
	/* 10 */ mfhi,
	/* 11 */ mthi,
	/* 12 */ mflo,
	/* 13 */ mtlo,
	/* 14 */ _14,
	/* 15 */ _15,
	/* 16 */ _16,
	/* 17 */ _17,
	/* 18 */ mult,
	/* 19 */ multu,
	/* 1a */ div,
	/* 1b */ divu,
	/* 1c */ _1c,
	/* 1d */ _1d,
	/* 1e */ _1e,
	/* 1f */ _1f,
	
	/* 20 */ add,
	/* 21 */ addu,
	/* 22 */ sub,
	/* 23 */ subu,
	/* 24 */ and,
	/* 25 */ or,
	/* 26 */ xor,
	/* 27 */ nor,
	/* 28 */ _28,
	/* 29 */ _29,
	/* 2a */ slt,
	/* 2b */ sltu,
	/* 2c */ _2c,
	/* 2d */ _2d,
	/* 2e */ _2e,
	/* 2f */ _2f,
	
	/* 30 */ _30,
	/* 31 */ _31,
	/* 32 */ _32,
	/* 33 */ _33,
	/* 34 */ _34,
	/* 35 */ _35,
	/* 36 */ _36,
	/* 37 */ _37,
	/* 38 */ _38,
	/* 39 */ _39,
	/* 3a */ _3a,
	/* 3b */ _3b,
	/* 3c */ _3c,
	/* 3d */ _3d,
	/* 3e */ _3e,
	/* 3f */ _3f;
	
	private final Set<Attribute> attributes;
	
	private IntFunct() {
		this(REG);
	}

	private IntFunct(Set<Attribute> attr) {
		this.attributes = attr;
	}

	@Override
	public boolean has(Attribute attr) {
		return attributes.contains(attr);
	}

	@Override
	public Set<Attribute> attributes() {
		return attributes;
	}

	@Override
	public boolean valid() {
		return Instructions.valid(this);
	}

}

package de.unisb.prog.mips.insn;

import de.unisb.prog.mips.simulator.Type;

public enum Opcode implements Instruction {
	
	/* 00 */ special,
	/* 01 */ regimm,
	/* 02 */ j(Kind.ABS_JUMP, Immediate.ZEXT_26),
	/* 03 */ jal(Kind.ABS_JUMP, Immediate.ZEXT_26),
	/* 04 */ beq(Kind.REL_JUMP_CMP_REG),
	/* 05 */ bne(Kind.REL_JUMP_CMP_REG),
	/* 06 */ blez(Kind.REL_JUMP_CMP_ZERO), 
	/* 07 */ bgtz(Kind.REL_JUMP_CMP_ZERO),
	/* 08 */ addi,
	/* 09 */ addiu,
	/* 0a */ slti,
	/* 0b */ sltiu,
	/* 0c */ andi(Immediate.ZEXT_16),
	/* 0d */ ori(Immediate.ZEXT_16),
	/* 0e */ xori(Immediate.ZEXT_16),
	/* 0f */ lui(Immediate.ZEXT_16),
	
	/* 10 */ _10,
	/* 11 */ _11,
	/* 12 */ _12,
	/* 13 */ _13,
	/* 14 */ beql(Kind.REL_JUMP_CMP_REG),
	/* 15 */ bnel(Kind.REL_JUMP_CMP_REG),
	/* 16 */ blezl(Kind.REL_JUMP_CMP_ZERO),
	/* 17 */ bgtzl(Kind.REL_JUMP_CMP_ZERO),
	/* 18 */ _18,
	/* 19 */ _19,
	/* 1a */ _1a,
	/* 1b */ _1b,
	/* 1c */ _1c,
	/* 1d */ _1d,
	/* 1e */ _1e,
	/* 1f */ _1f,
	
	/* 20 */ lb(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 21 */ lh(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 22 */ lwl(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 23 */ lw(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 24 */ lbu(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 25 */ lhu(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 26 */ lwr(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 27 */ _27,
	/* 28 */ sb(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 29 */ sh(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 2a */ swl(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 2b */ sw(Kind.LOAD_STORE, Immediate.SEXT_16),
	/* 2c */ _2c,
	/* 2d */ _2d,
	/* 2e */ swr(Kind.LOAD_STORE, Immediate.SEXT_16),
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
	/* 3f */ _3f;
	
	private final Kind kind;
	private final Immediate imm;
	
	private Opcode(Kind kind, Immediate imm) {
		this.kind = kind;
		this.imm  = imm;
	}
	
	private Opcode(Kind kind) {
		this(kind, Immediate.SEXT_16);
	}
	
	private Opcode(Immediate imm) {
		this(Kind.IMM, imm);
	}
	
	private Opcode() {
		this(Kind.IMM, Immediate.SEXT_16);
	}
	
	public int extendImm(int imm) {
		return getImmediate() == Immediate.SEXT_16 ? Type.HALF.signExtend(imm) : imm & 0xffff;
	}

	@Override
	public boolean valid() {
		return Instructions.valid(this);
	}
	
	@Override
	public int encodeOpcodeInto(int word) {
		return FIELD_OPCODE.insert(word, this.ordinal());
	}
	
	public int encode(int rs, int rt, int imm) {
		int word = encodeOpcodeInto(0);
		word = FIELD_IMM.insert(word, imm);
		word = FIELD_RT.insert(word, rt);
		word = FIELD_RS.insert(word, rs);
		return word;
	}
	
	public boolean immFits(int imm) {
		Immediate i = getImmediate();
		if (i == Immediate.SEXT_16) 
			return -32768 <= imm && imm <= 32767;
		if (i == Immediate.ZEXT_16) 
			return 0 <= imm && imm <= 65535;
		else 
			return false;
	}

	@Override
	public Opcode getOpcode() {
		return this;
	}

	@Override
	public Kind getKind() {
		return kind;
	}

	@Override
	public Immediate getImmediate() {
		return imm;
	}

	@Override
	public String toString() {
		return name();
	}
	
}

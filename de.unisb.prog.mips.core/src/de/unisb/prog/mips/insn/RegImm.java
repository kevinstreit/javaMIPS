package de.unisb.prog.mips.insn;

import de.unisb.prog.mips.simulator.Type;

public enum RegImm implements Instruction {

	bltz,
	bgez,
	bltzl,
	bgezl,
	_04,
	_05,
	_06,
	_07,

	_08,
	_09,
	_0a,
	_0b,
	_0c,
	_0d,
	_0e,
	_0f,

	bltzal,
	bgezal,
	bltzall,
	bgezall,
	_14,
	_15,
	_16,
	_17,

	_18,
	_19,
	_1a,
	_1b,
	_1c,
	_1d,
	_1e,
	_1f;

	public int extendImm(int imm) {
		return Type.HALF.signExtend(imm);
	}

	public boolean valid() {
		return Instructions.valid(this);
	}

	public int encodeOpcodeInto(int word) {
		word = FIELD_OPCODE.insert(word, Opcode.regimm.ordinal());
		word = FIELD_RT.insert(word, ordinal());
		return word;
	}

	public Opcode getOpcode() {
		return Opcode.regimm;
	}

	public Kind getKind() {
		return Kind.REL_JUMP_CMP_ZERO;
	}

	public Immediate getImmediate() {
		return Immediate.SEXT_16;
	}

	@Override
	public String toString() {
		return name();
	}

}

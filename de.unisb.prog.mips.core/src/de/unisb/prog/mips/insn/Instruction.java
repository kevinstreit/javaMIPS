package de.unisb.prog.mips.insn;

import de.unisb.prog.mips.util.Bitfield;

public interface Instruction {
	
	public static final Bitfield FIELD_INTFUNCT = new Bitfield(0, 6);
	public static final Bitfield FIELD_SHAMT    = Bitfield.leftOf(FIELD_INTFUNCT, 5);
	public static final Bitfield FIELD_RD       = Bitfield.leftOf(FIELD_SHAMT, 5);
	public static final Bitfield FIELD_RT       = Bitfield.leftOf(FIELD_RD, 5);
	public static final Bitfield FIELD_RS       = Bitfield.leftOf(FIELD_RT, 5);
	public static final Bitfield FIELD_OPCODE   = Bitfield.leftOf(FIELD_RS, 6);
	
	public static final Bitfield FIELD_IMM      = new Bitfield(0, 16);
	public static final Bitfield FIELD_TARGET   = new Bitfield(0, 26);
	
	public boolean valid();
	public int encodeOpcodeInto(int word);
	public Opcode getOpcode();
	public Kind getKind();
	public Immediate getImmediate();

}

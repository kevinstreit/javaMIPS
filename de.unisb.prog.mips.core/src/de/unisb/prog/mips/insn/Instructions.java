package de.unisb.prog.mips.insn;

import java.util.HashMap;
import java.util.Map;


public class Instructions {

	private static final Instruction INVALID = new Instruction() {
		public boolean valid() { return false; }
		public int encodeOpcodeInto(int word) { return word; }
		public Opcode getOpcode() { return Opcode._3e; }
		public Kind getKind() { return Kind.THREE_REG; }
		public Immediate getImmediate() { return Immediate.NO_IMM; }
		public String toString() { return "<invalid>"; }
	};

	private static final Map<String, Instruction> INSN = new HashMap<String, Instruction>();

	private static <T extends Enum<T> & Instruction> void add(Class<T> cls) {
		for (T x : cls.getEnumConstants())
			if (valid(x))
				INSN.put(x.name(), x);
	}

	static {
		add(Opcode.class);
		add(IntFunct.class);
		add(RegImm.class);
	}

	static <T extends Enum<T>> boolean valid(T op) {
		return op.name().charAt(0) != '_';
	}

	public static Instruction get(String name) {
		Instruction insn = INSN.get(name);
		return insn != null ? insn : INVALID;
	}

	public static <T> T decode(int insn, Handler<T> h) throws IllegalOpcodeException {
		Opcode opcode = Opcode.values()[Instruction.FIELD_OPCODE.extract(insn)];
		int funct, shamt, rs, rt, rd, imm;

		if (! valid(opcode))
			throw new IllegalOpcodeException(insn);
		switch (opcode) {
		case special:
			funct = Instruction.FIELD_INTFUNCT.extract(insn);
			shamt = Instruction.FIELD_SHAMT.extract(insn);
			rd    = Instruction.FIELD_RD.extract(insn);
			rt    = Instruction.FIELD_RT.extract(insn);
			rs    = Instruction.FIELD_RS.extract(insn);
			IntFunct f = IntFunct.values()[funct];
			if (! valid(f))
				throw new IllegalOpcodeException(insn);
			return h.r(IntFunct.values()[funct], rs, rt, rd, shamt);
		case j:
		case jal:
			imm = Instruction.FIELD_TARGET.extract(insn);
			return h.j(opcode, imm);
		default: // format I
			imm = Instruction.FIELD_IMM.extract(insn);
			rt    = Instruction.FIELD_RT.extract(insn);
			rs    = Instruction.FIELD_RS.extract(insn);
			if (opcode == Opcode.regimm) {
				RegImm ri = RegImm.values()[rt];
				if (! valid(ri))
					throw new IllegalOpcodeException(insn);
				return h.i(ri, rs, imm);
			}
			else
				return h.i(opcode, rs, rt, imm);
		}
	}


}

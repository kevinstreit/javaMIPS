package de.unisb.prog.mips.insn;


public class Decode {
	
	public static <T extends Enum<T>> boolean valid(Enum<T> e) {
		return e.name().charAt(0) != '_';
	}
	
	public static <T> T decode(int insn, Handler<T> h) throws IllegalOpcodeException {
		Opcode opcode = Opcode.values()[insn >>> 26];
		int funct, shamt, rs, rt, rd, imm;
		
		if (! valid(opcode))
			throw new IllegalOpcodeException(insn);
		switch (opcode) {
		case special: 
			funct = insn & 0x3f; insn >>>= 6;
			shamt = insn & 0x1f; insn >>>= 5;
			rd    = insn & 0x1f; insn >>>= 5;
			rt    = insn & 0x1f; insn >>>= 5;
			rs    = insn & 0x1f; insn >>>= 5;
			IntFunct f = IntFunct.values()[funct];
			if (! valid(f))
				throw new IllegalOpcodeException(insn);
			return h.r(IntFunct.values()[funct], rs, rt, rd, shamt);
		case j:
		case jal: 
			imm = insn & 0x3ffffff;
			return h.j(opcode, imm);
		default: // format I
			imm = insn & 0xffff; insn >>>= 16;
			rt  = insn & 0x1f; insn >>>= 5;
			rs  = insn & 0x1f; insn >>>= 5;
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

package de.unisb.prog.mips.insn;


public class Decode {
	
	public static <T> T decode(int insn, Handler<T> h) {
		int opcode = insn >>> 26;
		int funct, shamt, rs, rt, rd, imm;
		
		switch (opcode) {
		case 0: // format r
			funct = insn & 0x3f; insn >>>= 6;
			shamt = insn & 0x1f; insn >>>= 5;
			rd    = insn & 0x1f; insn >>>= 5;
			rt    = insn & 0x1f; insn >>>= 5;
			rs    = insn & 0x1f; insn >>>= 5;
			return h.r(rs, rt, rd, shamt, funct);
		case 2: // j
		case 3: // jal
			imm = insn & 0x3ffffff;
			return h.j(opcode, imm);
		case 0x11: // floating point
			throw new UnsupportedOperationException("no floating point instructions supportet yet");
		default: // format I
			imm = insn & 0xffff; insn >>>= 16;
			rt  = insn & 0x1f; insn >>>= 5;
			rs  = insn & 0x1f; insn >>>= 5;
			return h.i(opcode, rs, rt, imm);
		}
	}

}

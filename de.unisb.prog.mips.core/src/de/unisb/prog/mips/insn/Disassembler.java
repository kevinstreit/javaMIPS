package de.unisb.prog.mips.insn;

public class Disassembler implements Handler<String> {

	public static final Disassembler INSTANCE = new Disassembler();

	public String i(Opcode opc, int rs, int rt, int imm) {
		if (opc.getImmediate() == Immediate.SEXT_16)
			imm = opc.extendImm(imm);
		return String.format("%7s %3s %3s %-6d", opc, regName(rs), regName(rt), imm);
	}

	public String i(RegImm ri, int rs, int imm) {
		return String.format("%7s %3s %-6d", ri, regName(rs), imm);
	}

	public String j(Opcode opc, int imm) {
		return String.format("%7s %08x", opc, imm << 2);
	}

	public String r(IntFunct funct, int rs, int rt, int rd, int shamt) {
		if (funct.getKind() == Kind.SHAMT)
			return String.format("%7s %3s %3s %3d", funct, regName(rt), regName(rd), shamt);
		else
			return String.format("%7s %3s %3s %3s", funct, regName(rs), regName(rt), regName(rd));
	}

	protected String regName(int reg) {
		return String.format("$%d", reg);
	}

	public String disasm(int word) {
		try {
			return Instructions.decode(word, this);
		} catch (IllegalOpcodeException e) {
			return String.format("%7s %08x", ".word", word);
		}
	}

}

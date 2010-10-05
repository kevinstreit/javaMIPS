package de.unisb.prog.mips.insn;

public class Test {
	
	public static void main(String[] args) {
		int insn = IntFunct.add.encodeOpcodeInto(0);
		insn = Instruction.FIELD_RD.insert(insn, 3);
		insn = Instruction.FIELD_RS.insert(insn, 4);
		insn = Instruction.FIELD_RT.insert(insn, 5);
		System.out.println(new Disassembler().disasm(insn));
	}

}

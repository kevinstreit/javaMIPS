package de.unisb.prog.mips.insn;

public class Test {
	
	public static void main(String[] args) {
		int insn = Encode.r(IntFunct.add, 2, 23, 12);
		System.out.println(new Disassembler().disasm(insn));
	}

}

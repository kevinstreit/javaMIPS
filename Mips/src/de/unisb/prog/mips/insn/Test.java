package de.unisb.prog.mips.insn;

public class Test {
	
	public static void main(String[] args) {
		int insn = Encode.r(IntFunct.add, 2, 23, 12);
		Disassembler dis = new Disassembler();
		System.out.println(Decode.decode(insn, dis));
	}

}

package de.unisb.prog.mips.insn;

public class Test {
	
	public static void main(String[] args) {
		int insn = Encode.r(IntFunct.add, 2, 23, 12);
		StringBuffer sb = new StringBuffer();
		Disassembler dis = new Disassembler(sb);
		Decode.decode(insn, dis);
		System.out.println(sb);
	}

}

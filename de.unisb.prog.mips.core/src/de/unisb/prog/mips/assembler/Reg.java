package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.util.Bitfield;

public enum Reg {
	
	/* 00 */ zero ("Constant 0"),
	/* 01 */ at ("Reserved for assembler"),
	/* 02 */ v0 ("Expression evaluation and results of a function"),
	/* 03 */ v1 ("Expression evaluation and results of a function"),
	/* 04 */ a0 ("Argument 1"),
	/* 05 */ a1 ("Argument 2"),
	/* 06 */ a2 ("Argument 3"),
	/* 07 */ a3 ("Argument 4"),
	/* 08 */ t0 ("Temporary (not preserved across call)"),
	/* 09 */ t1 ("Temporary (not preserved across call)"),
	/* 10 */ t2 ("Temporary (not preserved across call)"),
	/* 11 */ t3 ("Temporary (not preserved across call)"),
	/* 12 */ t4 ("Temporary (not preserved across call)"),
	/* 13 */ t5 ("Temporary (not preserved across call)"),
	/* 14 */ t6 ("Temporary (not preserved across call)"),
	/* 15 */ t7 ("Temporary (not preserved across call)"),
	/* 16 */ s0 ("Saved temporary (preserved across call)"),
	/* 17 */ s1 ("Saved temporary (preserved across call)"),
	/* 18 */ s2 ("Saved temporary (preserved across call)"),
	/* 19 */ s3 ("Saved temporary (preserved across call)"),
	/* 20 */ s4 ("Saved temporary (preserved across call)"),
	/* 21 */ s5 ("Saved temporary (preserved across call)"),
	/* 22 */ s6 ("Saved temporary (preserved across call)"),
	/* 23 */ s7 ("Saved temporary (preserved across call)"),
	/* 24 */ t8 ("Temporary (not preserved across call)"),
	/* 25 */ t9 ("Temporary (not preserved across call)"),
	/* 26 */ k0 ("Reserved for OS kernel"),
	/* 27 */ k1 ("Reserved for OS kernel"),
	/* 28 */ gp ("Pointer to global area"),
	/* 29 */ sp ("Stack pointer"),
	/* 30 */ fp ("Frame pointer"),
	/* 31 */ ra ("Return address (used by function call)");
	
	public int get(int[] gp) { return gp[ordinal()]; }
	public static Reg get(int idx) { return Reg.values()[idx & 0x1f]; }
	public final String description;
	
	private Reg(String desc) {
		this.description = desc;
	}
	
	public int encodeInto(int word, Bitfield f) {
		return f.insert(word, this.ordinal());
	}
	
	public static Reg parse(String s) {
		if (s.charAt(0) == '$')
			s = s.substring(1);
		try {
			return Reg.valueOf(s);
		}
		catch (IllegalArgumentException e) {
			try {
				int index = Integer.parseInt(s);
				return Reg.values()[index];
			}
			catch (NumberFormatException f) {
			}
		}
		throw new IllegalArgumentException("Illegal register " + s);
	}

}

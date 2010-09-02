package de.unisb.prog.mips.insn;

public enum Format {
	
	R {
		@Override public Bitfield format() { return fr; }
	},
	
	I {
		@Override public Bitfield format() { return fi; }
	},
	
	J {
		@Override public Bitfield format() { return fj; }
	},
	
	F {
		@Override public Bitfield format() { return ff; }
	};
	
	
	private static final Bitfield fr = new Bitfield();
	private static final Bitfield fi = new Bitfield();
	private static final Bitfield fj = new Bitfield();
	private static final Bitfield ff = new Bitfield();
	
	static {
		// fr.add("");
	}
	
	public abstract Bitfield format();
	
	
}

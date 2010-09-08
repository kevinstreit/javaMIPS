package de.unisb.prog.mips.simulator;

public class Memory {
	
	private final ByteMemory mem;
	boolean bigEndian;
	
	public Memory(ByteMemory mem) {
		this.mem = mem;
	}

	public void store(int addr, int val, Type tp) {
		int sz    = tp.sizeof();
		int start = bigEndian ? 0 : (sz - 1);
		int inc   = bigEndian ? 1 : -1;
		for (int i = addr + start; i < sz; i += inc) {
			byte b = (byte) (val & 0xff);
			val >>>= 8;
			mem.store(addr, b);
		}
	}

	public int load(int addr, Type tp) {
		int res   = 0;
		int sz    = tp.sizeof();
		int start = bigEndian ? 0 : (sz - 1);
		int inc   = bigEndian ? 1 : -1;
		for (int i = addr + start; i < sz; i += inc) {
			res |= mem.load(i) & 0xff;
			res <<= 8;
		}
		return res;
	}

}

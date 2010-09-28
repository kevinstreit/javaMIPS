package de.unisb.prog.mips.simulator;

import java.io.IOException;

public class Memory {
	
	private final ByteMemory mem;
	boolean bigEndian;
	
	public Memory(ByteMemory mem, boolean bigEndian) {
		this.mem = mem;
		this.bigEndian = bigEndian;
	}

	public void store(int addr, int val, Type tp) {
		int sz    = tp.sizeof();
		int start = bigEndian ? 0 : (sz - 1);
		int inc   = bigEndian ? 1 : -1;
		int end   = start + sz * inc;
		for (int i = start; i != end; i += inc) {
			byte b = (byte) (val & 0xff);
			mem.store(addr + i, b);
			val >>>= 8;
		}
	}

	public int load(int addr, Type tp) {
		int sz    = tp.sizeof();
		int start = !bigEndian ? 0 : (sz - 1);
		int inc   = !bigEndian ? 1 : -1;
		int end   = start + sz * inc;
		int res   = 0;
		for (int i = start; i != end; i += inc) {
			res <<= 8;
			res |= mem.load(addr + i) & 0xff;
		}
		return res;
	}
	
	public <T> void dump(T output, int from, int count, MemDumpFormatter<T> fmt) throws IOException {
		Type tp = fmt.granularity();
		int step = tp.sizeof();
		int[] chunk = new int[fmt.chunkSize()];
		int to = from + count;
		int i = 0;
		int start = from;
		for (int addr = from; addr < to; addr += step) {
			chunk[i] = load(addr, tp);
			if (i == chunk.length - 1) {
				fmt.emit(output, start, chunk);
				i = 0;
				start += chunk.length * step;
			}
			else {
				i = i + 1;
			}
		}
	}

}

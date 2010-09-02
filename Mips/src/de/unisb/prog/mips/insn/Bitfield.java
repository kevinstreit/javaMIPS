package de.unisb.prog.mips.insn;

import java.util.TreeSet;

@SuppressWarnings("serial")
public class Bitfield extends TreeSet<Bitfield.Field> {
	
	static final class Field implements Comparable<Field> {
		private final int offset, mask, size;
		private final boolean signed;
		
		Field(boolean signed, int offset, int size) {
			super();
			this.signed = signed;
			this.offset = offset;
			this.size = size;
			this.mask = (1 << size) - 1;
		}
		
		@Override
		public int compareTo(Field o) {
			return offset - o.offset;
		}
		
		public int encode(int val) {
			return (val & mask) << offset;
		}
		
		public int decode(int val) {
			int res = (val >> offset) & mask;
			if (signed) {
				int a = 32 - size;
				res <<= a;
				res >>= a;
			}
			return res;
		}
	}
	
	private int occupancy = 0;
	
	public void add(String name, int offset, int size) {
		add(name, offset, size, false);
	}

	public void add(String name, int offset, int size, boolean signed) {
		if (offset + size > 32)
			throw new IllegalArgumentException("field cannot excess 32 bit");
		// TODO: test for occupancy and generate error
		Field f = new Field(signed, offset, size);
		add(f);
		occupancy |= f.encode(-1);
	}
	
	public int encode(int[] args) {
		if (args.length < size())
			throw new IllegalArgumentException("unsufficient number of arguments supplied");
		int res = 0;
		
		int i = 0;
		for (Field f : this) 
			res |= f.encode(args[i]);
		return res;
	}
	
	public void decode(int val, int[] res) {
		if (res.length < size())
			throw new IllegalArgumentException("result array not long enough");
		int i = 0;
		for (Field f : this) 
			res[i++] = f.decode(val);
	}
	
	public int[] decode(int val) {
		int[] res = new int[size()];
		decode(val, res);
		return res;
	}
}

package de.unisb.prog.mips.util;

public class Bitfield {

	private final int width, offset;
	private final boolean signed;

	public Bitfield(int offset, int width, boolean signed) {
		if (width < 1)
			throw new IllegalStateException("bitfield must be at least of size 1");
		this.offset = offset;
		this.width = width;
		this.signed = signed;
	}

	public Bitfield(int offset, int width) {
		this(offset, width, false);
	}

	public final int insert(int word, int val) {
		int mask = ((1 << width) - 1) << offset;
		val <<= offset;
		return (val & mask) | (word & ~mask);
	}

	public final int extract(int word) {
		int mask = ((1 << width) - 1) << offset;
		int val  = (word & mask) >>> offset;
		if (signed) {
			int amount = 32 - width;
			val = (val << amount) >> amount;
		}
		return val;
	}

	public static Bitfield leftOf(Bitfield f, int width, boolean signed) {
		if (f.offset + f.width + width > 32)
			throw new IllegalStateException("bitfield width to high");
		return new Bitfield(f.offset + f.width, width, signed);
	}

	public static Bitfield leftOf(Bitfield f, int width) {
		return leftOf(f, width, false);
	}

	@Override
	public int hashCode() {
		return offset + 32 * width;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Bitfield))
			return false;
		Bitfield b = (Bitfield) obj;
		return b.width == width && b.offset == offset;
	}
}

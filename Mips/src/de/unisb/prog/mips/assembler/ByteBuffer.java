package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.insn.Util;


public class ByteBuffer {
	
	private byte[] buf;
	private int count = 0;
	
	public ByteBuffer(int size) {
		buf = new byte[size];
	}
	
	public ByteBuffer() {
		this(4096);
	}
	
	protected final int currPos() {
		return count;
	}
	
	private void expand(int newcount) {
		assert newcount > buf.length;
		newcount = Math.max(newcount, 2 * buf.length + 1);
		byte[] oldbuf = buf;
		buf = new byte[newcount];
		System.arraycopy(oldbuf, 0, buf, 0, oldbuf.length);
		assert newcount < buf.length;
	}
	
	public void append(byte[] data) {
		int newcount = data.length + count;
		if (newcount > buf.length)
			expand(newcount);
		System.arraycopy(data, 0, buf, count, data.length);
		count = newcount;
	}
		
	public void append(int word) {
		append(Util.intToBytes(word));
	}
	
	public void set(int ofs, byte[] data) {
		assert ofs >= 0 && (ofs + data.length) < count;
		System.arraycopy(data, 0, buf, ofs, data.length);
	}
	
	public void set(int ofs, int word) {
		byte[] data = Util.intToBytes(word);
		set(ofs, data);
	}
	
	public void get(int ofs, byte[] data) {
		assert ofs >= 0 && (ofs + data.length) < count;
		System.arraycopy(buf, ofs, data, 0, data.length);
	}
	
	public int get(int ofs) {
		byte[] data = new byte[4];
		get(ofs, data);
		return Util.bytesToInt(data);
	}
	
	
	
}

package de.unisb.prog.mips.simulator;

import java.util.HashMap;
import java.util.Map;

public class VirtualMemory implements ByteMemory {

	private final int pageBits;
	private final int inPageMask;
	private final Map<Integer, byte[]> pageMap = new HashMap<Integer, byte[]>();

	/**
	 * Create a new virtual memory.
	 * @param pageBits Number of bits to address a byte in a page (page size: 2^pageBits bytes).
	 */
	public VirtualMemory(int pageBits) {
		this.pageBits = pageBits;
		this.inPageMask = (1 << pageBits) - 1;
	}

	private byte[] lastAccessedPage = null;
	private int lastAccessedIdx = -1;

	byte[] lookupPage(int addr) {
		int idx     = addr >>> this.pageBits;
		byte[] page = this.lastAccessedIdx == idx ? this.lastAccessedPage : this.pageMap.get(idx);
		if (page == null) {
			page = new byte[1 << this.pageBits];
			this.pageMap.put(idx, page);
		}
		this.lastAccessedIdx = idx;
		this.lastAccessedPage = page;
		return page;
	}

	public byte load(int addr) {
		byte[] page = lookupPage(addr);
		return page[addr & this.inPageMask];
	}

	public void store(int addr, byte val) {
		byte[] page = lookupPage(addr);
		page[addr & this.inPageMask] = val;
	}

	public void reset() {
		this.pageMap.clear();
	}

}
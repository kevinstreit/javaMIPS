package de.unisb.prog.mips.simulator;

public class VirtualMemory implements Memory {
	
	// Page size in ints
	
	private static final int[] OFFSETS = { 32, 22, 12 };
	private static final int LAST      = OFFSETS[OFFSETS.length - 1];
	public static final int PAGE_SIZE  = 1 << (LAST - 2);
	
	private int[][] pages;
	private PageFaultHandler handler;
	
	public VirtualMemory(int nPages) {
		this.pages = new int[nPages][];
		this.pages[0] = new int[PAGE_SIZE];
	}
	
	private int[] findPage(int addr, int[] page, int level) {
		if (level >= OFFSETS.length)
			return page;
		int ofs  = OFFSETS[level];
		int mask = (1 << OFFSETS[level - 1]) - (1 << ofs);
		int idx  = (mask & addr) >> ofs;
		int paddr = page[idx];
		if (paddr == 0)
			handler.handle();
		return findPage(addr, pages[paddr], level + 1);
	}
	
	private int[] lookupPage(int addr) {
		return findPage(addr, pages[0], 1);
	}
	
	private static int getOffset(int addr) {
		return addr & ((1 << LAST) - 1);
	}
	
	@Override
	public int load(int addr) {
		int[] page = lookupPage(addr);
		int offset = getOffset(addr);
		return page[offset];
	}

	@Override
	public void store(int addr, int word) {
		int[] page = lookupPage(addr);
		int offset = getOffset(addr);
		page[offset] = word;
	}
}

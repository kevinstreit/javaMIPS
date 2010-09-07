package de.unisb.prog.mips.simulator;

public class VirtualMemory implements Memory {
	
	// Page size in ints
	public static int PAGE_SIZE = 1024;
	
	private int[][] pages;
	
	public VirtualMemory(int nPages) {
		this.pages = new int[nPages][];
	}
	
	@Override
	public int load(int addr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void store(int addr, int word) {
		// TODO Auto-generated method stub

	}

}

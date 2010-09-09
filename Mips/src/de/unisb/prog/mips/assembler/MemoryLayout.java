package de.unisb.prog.mips.assembler;

public interface MemoryLayout {
	
	int dataStart();
	int textStart();
	int stackStart();
	int dataStartOffset();
	int textStartOffset();

}

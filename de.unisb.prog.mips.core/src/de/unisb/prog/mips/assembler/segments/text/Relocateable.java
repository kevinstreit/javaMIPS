package de.unisb.prog.mips.assembler.segments.text;


public interface Relocateable {

	void relocate(int startAddress) throws JumpTargetOutOfRange;

}

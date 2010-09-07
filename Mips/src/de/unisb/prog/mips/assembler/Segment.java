package de.unisb.prog.mips.assembler;


public interface Segment {
	
	public static enum Type { DATA, TEXT }
	
	public Label createLabelOnLast();
	public Type getType();
	public void computeOffsets();
	public void patchLabels();
	
}

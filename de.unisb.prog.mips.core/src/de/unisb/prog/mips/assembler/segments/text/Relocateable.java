package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;


public interface Relocateable {

	public void relocate(int startAddress, ErrorReporter<Position> reporter);

}

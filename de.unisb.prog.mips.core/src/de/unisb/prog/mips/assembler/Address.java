package de.unisb.prog.mips.assembler;

import de.unisb.prog.mips.assembler.segments.Segment;

public interface Address extends Expr {
	
	Segment getSegment();

}

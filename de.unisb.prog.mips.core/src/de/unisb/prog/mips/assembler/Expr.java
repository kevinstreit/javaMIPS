package de.unisb.prog.mips.assembler;

import java.io.IOException;


public interface Expr {
	
	int eval();
	void append(Appendable app) throws IOException;

}

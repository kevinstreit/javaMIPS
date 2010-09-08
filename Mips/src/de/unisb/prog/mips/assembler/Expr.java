package de.unisb.prog.mips.assembler;

import java.io.IOException;


public interface Expr<T> {
	
	T eval();
	void append(Appendable app) throws IOException;

}

package de.unisb.prog.mips.assembler;

import java.util.Map;

public interface Element {

	int size();
	void patchLabels(Map<String, Label> labels);
	
}

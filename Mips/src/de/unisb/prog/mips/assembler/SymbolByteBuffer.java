package de.unisb.prog.mips.assembler;

import java.util.HashMap;
import java.util.Map;

public class SymbolByteBuffer extends ByteBuffer {
	
	private final Map<String, Integer> symbolTable = new HashMap<String, Integer>();
	
	@SuppressWarnings("serial")
	public class NotFound extends Exception { }
	
	@SuppressWarnings("serial")
	public class AlreadyExists extends Exception { }
	
	public final int lookup(String symbol) {
		if (! symbolTable.containsKey(symbol)) 
			return -1;
		return symbolTable.get(symbol);
	}
	
	public final int label(String symbol) {
		if (symbolTable.containsKey(symbol))
			return -1;
		int pos = currPos();
		symbolTable.put(symbol, pos);
		return pos;
	}
	
}

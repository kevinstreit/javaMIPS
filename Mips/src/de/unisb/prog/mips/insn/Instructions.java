package de.unisb.prog.mips.insn;

import java.util.HashMap;
import java.util.Map;

public class Instructions {
	
	private static final Map<String, Instruction> INSN = new HashMap<String, Instruction>();
	
	private static final <T extends Enum<?>> boolean valid(T x) {
		return x.name().charAt(0) != '_';
	}
	
	private static <T extends Enum<T> & Instruction> void add(Class<T> cls) {
		for (T x : cls.getEnumConstants())
			if (valid(x))
				INSN.put(x.name(), x);
	}
	
	static {
		add(Opcode.class);
		add(IntFunct.class);
		add(RegImm.class);
	}
	
	public static Instruction get(String name) {
		return INSN.get(name);
	}
	

}

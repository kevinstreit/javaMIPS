package de.unisb.prog.mips.insn;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Instructions {
	
	private static final Instruction INVALID = new Instruction() {
		@Override public boolean has(Attribute attr) { return false; }
		@Override public Set<Attribute> attributes() { return EnumSet.noneOf(Attribute.class); }
		@Override public boolean valid() { return false; }
	};
	
	private static final Map<String, Instruction> INSN = new HashMap<String, Instruction>();
	
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
	
	static <T extends Enum<T>> boolean valid(T op) {
		return op.name().charAt(0) != '_';
	}
	
	public static Instruction get(String name) {
		Instruction insn = INSN.get(name);
		return insn != null ? insn : INVALID;
	}
	

}

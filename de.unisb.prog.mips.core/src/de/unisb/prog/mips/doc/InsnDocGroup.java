package de.unisb.prog.mips.doc;

import java.util.Vector;

public class InsnDocGroup {
	public final String name;
	private final Vector<InsnDoc> insns;
	
	public InsnDocGroup(String name) {
		this.name = name;
		this.insns = new Vector<InsnDoc>();
	}
	
	public void addInsn(InsnDoc insn) {
		this.insns.add(insn);
	}
	
	public InsnDoc[] getInsns() {
		return insns.toArray(new InsnDoc[insns.size()]);
	}
	
	@Override
	public String toString() {
		return name;
	}
}

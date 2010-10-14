package de.unisb.prog.mips.doc;

public class InsnDoc {
	public final InsnDocGroup parentGroup;

	public final String mnemonic; 		// e.g. addi
	public final String longName;		// e.g. Add Immediate
	public final String description;	// e.g. Adds an immediate to the value read from $src and stores the result in $dst.

	public InsnDoc(
		InsnDocGroup parent,
		String mnemonic,
		String longName,
		String description
	) {
		this.parentGroup = parent;

		this.mnemonic = mnemonic;
		this.longName = longName;
		this.description = description;
	}

	@Override
	public String toString() {
		return mnemonic + " (" + longName + ")";
	}
}

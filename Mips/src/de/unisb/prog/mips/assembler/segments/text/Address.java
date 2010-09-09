package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.insn.Opcode;

public class Address extends AddrGen<LabelRef> {
	
	public Address(Reg rt, LabelRef ref) {
		super(Opcode.addi, rt, ref);
	}

	@Override
	protected void rewrite() {
		insertAddrGen(exp.getElement().relativeTo(), rt);
	}

}

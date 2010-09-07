package de.unisb.prog.mips.assembler.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

abstract class LabelRefInsn extends Insn {
	
	protected final LabelRef ref;
	
	@Override
	protected final List<Insn> generateAddr(int baseReg, int tgtReg, int addr) {
		if (Encode.immFitsI(addr)) {
			return super.generateAddr(baseReg, tgtReg, addr);
		}
		
		List<Insn> res = new ArrayList<Insn>(3);
		res.add(new Normal(Encode.i(Opcode.lui, tgtReg, addr >>> 16)));
		addr >>>= 16;
		if (addr != 0)
			res.add(new Normal(Encode.i(Opcode.ori, tgtReg, tgtReg, addr)));
		res.add(new Normal(Encode.r(IntFunct.add, tgtReg, tgtReg, baseReg)));
		return res;
		
	}
	
	LabelRefInsn(int enc, LabelRef ref) {
		super(enc);
		this.ref = ref;
	}

}

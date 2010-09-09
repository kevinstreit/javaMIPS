package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

public abstract class AddrGen<T extends Expr<Integer>> extends Insn {
	
	protected final Opcode opcode;
	protected final Reg rt;
	protected final T exp;
	
	public AddrGen(Opcode opc, Reg rt, T exp) {
		super(Encode.i(opc, 0, rt.ordinal(), 0));
		this.opcode = opc;
		this.rt = rt;
		this.exp = exp;
	}
	
	protected void insertAddrGen(Reg base, Reg temp) {
		int value = exp.eval();
		
		// get insertion point into the list and remove current instruction
		Element.Root root = Element.createRoot();
		
		int imm;
		int rs;
		
		if (Encode.immFitsI(value)) {
			imm = value;
			rs  = base.ordinal();
		}
		
		else {
			int hi = value >>> 16;
			int lo = value & 0xffff;
			
			imm = 0;
			rs  = temp.ordinal();

			// load high part of reg into assembler temp register
			root.prepend(new Normal(Encode.i(Opcode.lui, 0, rs, hi)));
			if (lo != 0) {
				if ((lo & 0x8000) != 0) 
					root.prepend(new Normal(Encode.i(Opcode.ori, rs, rs, lo)));
				else
					imm = lo;
			}

			if (base != Reg.zero)
				root.prepend(new Normal(Encode.r(IntFunct.add, base.ordinal(), rs, rs)));
		}
		
		root.prepend(new Normal(Encode.i(opcode, rs, rt.ordinal(), imm)));
		this.replaceBy(root);
	}
	
	protected abstract void rewrite();

}

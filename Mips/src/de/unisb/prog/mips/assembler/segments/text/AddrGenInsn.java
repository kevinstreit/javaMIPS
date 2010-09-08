package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

public abstract class AddrGenInsn extends LabelRefInsn {
	
	protected final Opcode opcode;
	protected final int rt;
	
	public AddrGenInsn(Opcode opc, int rt, Expr<Integer> exp) {
		super(0, exp);
		this.opcode = opc;
		this.rt = rt;
	}
	
	protected void insertAddrGen(int baseReg, int tempReg) {
		int value = exp.eval();
		
		// get insertion point into the list and remove current instruction
		Element.Root root = Element.createRoot();
		
		int imm;
		int rs;
		
		if (Encode.immFitsI(value)) {
			imm = value;
			rs  = baseReg;
		}
		
		else {
			int hi = value >>> 16;
			int lo = value & 0xffff;
			
			imm = 0;
			rs  = tempReg;

			// load high part of reg into assembler temp register
			root.prepend(new Normal(Encode.i(Opcode.lui, 0, rs, hi)));
			if (lo != 0) {
				if ((lo & 0x8000) != 0) 
					root.prepend(new Normal(Encode.i(Opcode.ori, rs, rs, lo)));
				else
					imm = lo;
			}

			if (baseReg != 0)
				root.prepend(new Normal(Encode.r(IntFunct.add, baseReg, rs, rs)));
		}
		
		root.prepend(new Normal(Encode.i(opcode, rs, rt, imm)));
		this.replaceBy(root);
	}
	
	protected abstract void rewrite();

}

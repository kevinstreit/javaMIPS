package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

public class DataRef extends LabelRefInsn {
	
	private final int rt;
	private final Opcode opc;
	
	DataRef(Opcode opc, int rt, Expr<Integer> e) {
		super(Encode.i(opc, 0, rt, 0), e);
		this.rt = rt;
		this.opc = opc;
	}
	
	protected void rewrite() {
		int offset = exp.eval();
		
		// get insertion point into the list and remove current instruction
		Element curr = prev();
		this.remove();
		
		int imm;
		int rs;
		
		if (Encode.immFitsI(offset)) {
			imm = offset;
			rs  = 3;
		}
		
		else {
			int hi = offset >>> 16;
			int lo = offset & 0xffff;
			
			imm = 0;
			rs  = 1;

			// load high part of reg into assembler temp register
			curr = curr.addAfterMe(new Normal(Encode.i(Opcode.lui, 0, rs, hi)));
			if (lo != 0) {
				if ((lo & 0x8000) != 0) 
					curr = curr.addAfterMe(new Normal(Encode.i(Opcode.ori, rs, rs, lo)));
				else
					imm = lo;
			}

			curr = curr.addAfterMe(new Normal(Encode.r(IntFunct.add, 3, rs, rs)));
		}
		
		curr = curr.addAfterMe(new Normal(Encode.i(opc, rs, rt, imm)));
	}

}

package de.unisb.prog.mips.assembler.text;

import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.Label;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

public class DataRef extends LabelRefInsn {
	
	private final int rt;
	private final Opcode opc;
	
	DataRef(Opcode opc, int rt, LabelRef ref) {
		super(Encode.i(opc, 0, rt, 0), ref);
		this.rt = rt;
		this.opc = opc;
	}
	
	public List<Insn> rewriteDataAddress() {
		assert ref.isLabelAvailable();
		Label l = ref.getLabel();
		int offset = l.getOffset();
		List<Insn> res = new ArrayList<Insn>(3);
		
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
			res.add(new Normal(Encode.i(Opcode.lui, 0, rs, hi)));
			if (lo != 0) {
				if ((lo & 0x8000) != 0) 
					res.add(new Normal(Encode.i(Opcode.ori, rs, rs, lo)));
				else
					imm = lo;
			}

			res.add(new Normal(Encode.r(IntFunct.add, 3, rs, rs)));
		}
		
		res.add(new Normal(Encode.i(opc, rs, rt, imm)));
		return res;
	}

}

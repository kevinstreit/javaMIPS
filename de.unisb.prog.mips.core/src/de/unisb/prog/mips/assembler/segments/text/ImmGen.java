package de.unisb.prog.mips.assembler.segments.text;

import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.ProxyElement;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

public abstract class ImmGen<T extends Expr> extends ProxyElement<Insn> {
	
	protected final T expr;
	protected final Opcode opcode;
	protected final Reg rt;

	ImmGen(Segment seg, Opcode opc, Reg rt, T expr) {
		super(seg);
		this.expr = expr;
		this.opcode = opc;
		this.rt = rt;
	}

	protected void setGenImm(Reg base, Reg temp) {
		List<Insn> res = new ArrayList<Insn>(4);
		Segment seg = getSegment();
		
		int value = expr.eval();
		int imm;
		int rs;
		
		if (opcode.immFits(value)) {
			imm = value;
			rs  = base.ordinal();
		}
		
		else {
			int hi = value >>> 16;
			int lo = value & 0xffff;
			
			imm = 0;
			rs  = temp.ordinal();
	
			// load high part of reg into assembler temp register
			res.add(new Insn(seg, Opcode.lui.encode(0, rs, hi)));
			if (lo != 0) {
				if ((lo & 0x8000) != 0) 
					res.add(new Insn(seg, Opcode.ori.encode(rs, rs, lo)));
				else
					imm = lo;
			}
	
			if (base != Reg.zero)
				res.add(new Insn(seg, IntFunct.add.encode(base.ordinal(), rs, rs)));
		}
		
		res.add(new Insn(seg, opcode.encode(rs, rt.ordinal(), imm)));
		set(res);
	}
	
	protected abstract void rewrite();

}

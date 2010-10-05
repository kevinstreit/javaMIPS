package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;

public abstract class ImmGen<T extends Expr> extends Insn {
	
	protected final T expr;
	protected final Opcode opcode;
	protected final Reg rt;

	ImmGen(Opcode opc, Reg rt, T expr) {
		super(rt.encodeInto(opc.encodeOpcodeInto(0), Instruction.FIELD_RT));
		this.expr = expr;
		this.opcode = opc;
		this.rt = rt;
	}

	protected void insertImmGen(Reg base, Reg temp) {
		int value = expr.eval();
		
		// get insertion point into the list and remove current instruction
		Element.Root root = Element.createRoot();
		
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
			root.prepend(new Normal(Opcode.lui.encode(0, rs, hi)));
			if (lo != 0) {
				if ((lo & 0x8000) != 0) 
					root.prepend(new Normal(Opcode.ori.encode(rs, rs, lo)));
				else
					imm = lo;
			}
	
			if (base != Reg.zero)
				root.prepend(new Normal(IntFunct.add.encode(base.ordinal(), rs, rs)));
		}
		
		root.prepend(new Normal(opcode.encode(rs, rt.ordinal(), imm)));
		this.replaceBy(root);
	}
	
	protected abstract void rewrite();

}

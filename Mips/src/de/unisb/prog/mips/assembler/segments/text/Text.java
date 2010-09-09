package de.unisb.prog.mips.assembler.segments.text;

import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Text extends Segment {
	
	private final List<AddrGen> addrGenInsns = new LinkedList<AddrGen>();
	private final List<RelJump> relJumps = new LinkedList<RelJump>();
	private final List<AbsJump> absJumps = new LinkedList<AbsJump>();
	
	public Element normal(IntFunct f, int rs, int rt, int rd, int shamt) {
		return add(new Normal(Encode.r(f, rs, rt, rd, shamt)));
	}
	
	public Element imm(Opcode opc, int rs, int rt, int imm) {
		return add(new Normal(Encode.i(opc, rs, rt, imm)));
	}
	
	public Element constant(int rt, int value) {
		AddrGen e = new Constant(rt, value);
		addrGenInsns.add(e);
		add(e);
		return e;
	}

	public Element constant(int rt, Expr<Integer> exp) {
		AddrGen e = new Constant(rt, exp);
		addrGenInsns.add(e);
		add(e);
		return e;
	}

	public Element loadstore(Opcode opc, int rt, Expr<Integer> e) {
		DataRef dr = new DataRef(opc, rt, e);
		add(dr);
		addrGenInsns.add(dr);
		return dr;
	}

	public Element condjump(Opcode opc, int rs, int rt, Expr<Integer> e) {
		RelJump rj = new RelJump(opc, rs, rt, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}

	public Element condjump(Opcode opc, int rs, Expr<Integer> e) {
		return condjump(opc, rs, 0, e);
	}

	public Element condjump(RegImm ri, int rs, Expr<Integer> e) {
		RelJump rj = new RelJump(ri, rs, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}
	
	public Element absjump(Opcode opc, Expr<Integer> exp) {
		AbsJump aj = new AbsJump(opc, exp);
		add(aj);
		absJumps.add(aj);
		return aj;
	}
	
	private void rewriteDataInsns() {
		for (AddrGen i : addrGenInsns) 
			i.rewrite();
	}
	
	private void rewriteRelJumps() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		for (RelJump rj : relJumps)
			rj.rewrite();
	}
	
	public void reloate(int startAddress) {
		super.relocate(startAddress);
		for (AbsJump aj : absJumps)
			aj.rewrite(startAddress);
	}
	
	public void prepare(MemoryLayout l) throws JumpTargetNotAligned, JumpTargetOutOfRange {
		relocate(l.textStart());
		rewriteDataInsns();
		assignOffsets(l.textStartOffset());
		rewriteRelJumps();
	}
}

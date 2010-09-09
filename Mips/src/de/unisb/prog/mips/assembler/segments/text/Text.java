package de.unisb.prog.mips.assembler.segments.text;

import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Text extends Segment {
	
	private final List<AddrGen<?>> addrGenInsns = new LinkedList<AddrGen<?>>();
	private final List<RelJump> relJumps = new LinkedList<RelJump>();
	private final List<AbsJump> absJumps = new LinkedList<AbsJump>();
	
	public Element normal(IntFunct f, Reg rs, Reg rt, Reg rd, int shamt) {
		return add(new Normal(Encode.r(f, rs.ordinal(), rt.ordinal(), rd.ordinal(), shamt)));
	}
	
	public Element imm(Opcode opc, Reg rs, Reg rt, int imm) {
		return add(new Normal(Encode.i(opc, rs.ordinal(), rt.ordinal(), imm)));
	}
	
	public Element constant(Reg rt, int value) {
		AddrGen<?> e = new Constant(rt, value);
		addrGenInsns.add(e);
		add(e);
		return e;
	}

	public Element address(Reg rt, LabelRef ref) {
		AddrGen<?> e = new Address(rt, ref);
		addrGenInsns.add(e);
		add(e);
		return e;
	}

	public Element constant(Reg rt, Expr<Integer> exp) {
		AddrGen<?> e = new Constant(rt, exp);
		addrGenInsns.add(e);
		add(e);
		return e;
	}

	public Element loadstore(Opcode opc, Reg rt, Expr<Integer> e) {
		DataRef dr = new DataRef(opc, rt, e);
		add(dr);
		addrGenInsns.add(dr);
		return dr;
	}

	public Element condjump(Opcode opc, Reg rs, Reg rt, LabelRef e) {
		RelJump rj = new RelJump(opc, rs, rt, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}

	public Element condjump(Opcode opc, Reg rs, LabelRef e) {
		return condjump(opc, rs, Reg.zero, e);
	}

	public Element condjump(RegImm ri, Reg rs, LabelRef e) {
		RelJump rj = new RelJump(ri, rs, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}
	
	public Element absjump(Opcode opc, LabelRef exp) {
		AbsJump aj = new AbsJump(opc, exp);
		add(aj);
		absJumps.add(aj);
		return aj;
	}
	
	private void rewriteDataInsns() {
		for (AddrGen<?> i : addrGenInsns) 
			i.rewrite();
	}
	
	private void rewriteRelJumps() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		for (RelJump rj : relJumps)
			rj.rewrite();
	}
	
	@Override
	public void relocate(int startAddress) {
		for (AbsJump aj : absJumps)
			aj.rewrite(startAddress);
	}
	
	public void prepare(MemoryLayout l) throws JumpTargetNotAligned, JumpTargetOutOfRange {
		rewriteDataInsns();
		assignOffsets(l.textStartOffset());
		rewriteRelJumps();
	}
}

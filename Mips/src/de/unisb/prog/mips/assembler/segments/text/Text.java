package de.unisb.prog.mips.assembler.segments.text;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Segments;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Text extends Segment {
	
	private final List<DataRef> dataRefInsns = new LinkedList<DataRef>();
	private final List<RelJump> relJumps = new LinkedList<RelJump>();
	
	public void normal(IntFunct f, int rs, int rt, int rd, int shamt) {
		add(new Normal(Encode.r(f, rs, rt, rd, shamt)));
	}
	
	public void imm(Opcode opc, int rs, int rt, int imm) {
		add(new Normal(Encode.i(opc, rs, rt, imm)));
	}

	public void loadstore(Opcode opc, int rt, Expr<Integer> e) {
		DataRef dr = new DataRef(opc, rt, e);
		add(dr);
		dataRefInsns.add(dr);
	}

	public void condjump(Opcode opc, int rs, int rt, Expr<Integer> e) {
		RelJump rj = new RelJump(opc, rs, rt, e);
		add(rj);
		relJumps.add(rj);
	}

	public void condjump(Opcode opc, int rs, Expr<Integer> e) {
		condjump(opc, rs, 0, e);
	}

	public void condjump(RegImm ri, int rs, Expr<Integer> e) {
		RelJump rj = new RelJump(ri, rs, e);
		add(rj);
		relJumps.add(rj);
	}
	
	public void absjump(Opcode opc, Expr<Integer> ref) {
		// TODO: NYI
	}
	
	@Override
	public Segment.Type getType() {
		return Segment.Type.TEXT;
	}
	
	private void rewriteDataInsns() {
		for (DataRef dr : dataRefInsns) 
			dr.rewrite();
	}
	
	private void rewriteRelJumps() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		for (RelJump rj : relJumps)
			rj.rewrite();
	}
	
	public void process(Data data) throws JumpTargetNotAligned, JumpTargetOutOfRange {
		rewriteDataInsns();
		assignOffsets();
		rewriteRelJumps();
	}
}

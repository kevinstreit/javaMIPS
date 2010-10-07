package de.unisb.prog.mips.assembler.segments.text;

import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.MemoryLayout;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class Text extends Segment {
	
	private static final long serialVersionUID = -1937181393628006820L;

	public Text(Assembly asm) {
		super(asm);
	}

	private final List<ImmGen<?>>    immGenInsns = new LinkedList<ImmGen<?>>();
	private final List<RelJump>         relJumps = new LinkedList<RelJump>();
	private final List<Relocateable>    relocate = new LinkedList<Relocateable>();
	
	public Element word(int w) {
		Element res = new Insn(this, w);
		add(res);
		return res;
	}
	
	public Element normal(Instruction f, Reg rs, Reg rt, Reg rd, int shamt) {
		int word = f.encodeOpcodeInto(0);
		word = rd.encodeInto(word, Instruction.FIELD_RD);
		word = rs.encodeInto(word, Instruction.FIELD_RS);
		word = rt.encodeInto(word, Instruction.FIELD_RT);
		Element res = new Insn(this, word);
		add(res);
		return res;
	}
	
	public Element imm(Instruction i, Reg rs, Reg rt, int imm) {
		int word = i.encodeOpcodeInto(0);
		word = rs.encodeInto(word, Instruction.FIELD_RS);
		word = rt.encodeInto(word, Instruction.FIELD_RT);
		word = Instruction.FIELD_IMM.insert(word, imm);
		Element res = new Insn(this, word);
		add(res);
		return res;
	}
	
	public Element address(Reg rt, Address addr) {
		return address(rt, new Option<Reg>(Reg.gp), addr);
	
	}
	public Element address(Reg rt, Option<Reg> reg, Address addr) {
		ImmGen<?> e = new LoadAddress(this, rt, reg, addr);
		immGenInsns.add(e);
		add(e);
		return e;
	}
	
	public Element constant(Reg rt, int val) {
		return constant(rt, Expressions.constantInt(val));
	}

	public Element constant(Reg rt, Expr expr) {
		ImmGen<?> e = new Constant(this, rt, expr);
		immGenInsns.add(e);
		add(e);
		return e;
	}

	public Element loadstore(Opcode opc, Reg rt, Option<Reg> base, Address addr) {
		DataRef dr = new DataRef(this, opc, rt, base, addr);
		add(dr);
		immGenInsns.add(dr);
		return dr;
	}

	public Element condjump(Instruction opc, Reg rs, Reg rt, LabelRef e) {
		RelJump rj = new RelJump(this, opc, rs, rt, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}

	public Element condjump(Instruction opc, Reg rs, LabelRef e) {
		RelJump rj = new RelJump(this, opc, rs, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}
	
	public Element condjump(int word, LabelRef e) {
		RelJump rj = new RelJump(this, word, e);
		add(rj);
		relJumps.add(rj);
		return rj;
	}
	
	public Element absjump(Instruction opc, LabelRef exp) {
		AbsJump aj = new AbsJump(this, opc, exp);
		add(aj);
		relocate.add(aj);
		return aj;
	}
	
	
	private void rewriteDataInsns() {
		for (ImmGen<?> i : immGenInsns) 
			i.rewrite();
	}
	
	private void rewriteRelJumps() throws JumpTargetNotAligned, JumpTargetOutOfRange {
		for (RelJump rj : relJumps)
			rj.rewrite();
	}
	
	@Override
	public void relocate(int startAddress) throws JumpTargetOutOfRange {
		for (Relocateable r : relocate)
			r.relocate(startAddress);
	}
	
	public void prepare(MemoryLayout l) throws JumpTargetNotAligned, JumpTargetOutOfRange {
		// append an exit syscall for those who forgot it
		imm(Opcode.ori, Reg.zero, Reg.v0, 10);
		normal(IntFunct.syscall, Reg.zero, Reg.zero, Reg.zero, 0);
		
		rewriteDataInsns();
		assignOffsets(l.textStartOffset());
		rewriteRelJumps();
	}

	@Override
	public Kind getKind() {
		return Kind.TEXT;
	}

}

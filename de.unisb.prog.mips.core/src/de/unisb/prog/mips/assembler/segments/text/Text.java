package de.unisb.prog.mips.assembler.segments.text;

import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Instruction;
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

	@Override
	public void addAll(Segment s) {
		super.addAll(s);
		if (s instanceof Text) {
			Text other = (Text) s;
			immGenInsns.addAll(other.immGenInsns);
			relJumps.addAll(other.relJumps);
			relocate.addAll(other.relocate);
		}
	}

	public Element word(int first, int... rest) {
		Element res = new Insn(this, first);
		add(res);
		for (int w : rest) {
			Element e = new Insn(this, w);
			add(e);
		}
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

	public Element normal(Instruction f, Reg rs, Reg rt, Reg rd) {
		return normal(f, rs, rt, rd, 0);
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
		return address(rt, Option.empty(Reg.class), addr);

	}
	public Element address(Reg rt, Option<Reg> reg, Address addr) {
		AddrInsn e = new AddrInsn(this, "la", Opcode.addiu, rt, reg, addr);
		immGenInsns.add(e);
		relocate.add(e);
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
		AddrInsn dr = new AddrInsn(this, opc, rt, base, addr);
		add(dr);
		immGenInsns.add(dr);
		relocate.add(dr);
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
		assertState(State.BUILDING);
		for (ImmGen<?> i : immGenInsns)
			i.rewrite();
		state = State.PSEUDOS_EXPANDED;
	}

	private void rewriteRelJumps(ErrorReporter<Position> reporter) {
		assertState(State.OFFSETS_ASSIGNED);
		for (RelJump rj : relJumps)
			rj.rewrite(reporter);
		state = State.RELATIVE_ADDRESSES_PATCHED;
	}

	@Override
	public void relocateInternal(int startAddress, ErrorReporter<Position> reporter) {
		for (Relocateable r : relocate)
			r.relocate(startAddress, reporter);
	}

	@Override
	public void prepare(ErrorReporter<Position> reporter) {
		validateElements(reporter);
		rewriteDataInsns();
		assignOffsets();
		rewriteRelJumps(reporter);
	}

	@Override
	public Kind getKind() {
		return Kind.TEXT;
	}

}

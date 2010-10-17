package de.unisb.prog.mips.assembler.segments.text;

import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Segment.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public class LoadAddress extends ImmGen<Address> implements Relocateable {

	public LoadAddress(Segment seg, Reg rt, Option<Reg> reg, Address addr) {
		super(seg, "la", Opcode.addiu, rt, reg, addr);
	}

	@Override
	protected void rewrite() {
		if (this.expr.getSegment().getKind() == Kind.DATA) {
			Reg b = this.base.otherwise(Reg.gp);
			Address a = this.base.ifthenelse(this.expr, Expressions.offset(this.expr, -32768));
			set(genImm(b, this.rt, a));
		}

		else {
			// produce dummy address generation code
			// to represent the code size correctly for relocation
			// which comes *after* rewriting
			List<Insn> dummy = new ArrayList<Insn>(2);
			dummy.add(new Insn(this.segment, 0));
			dummy.add(new Insn(this.segment, 0));
			set(dummy);
		}
	}

	public void relocate(int startAddress, ErrorReporter<Position> reporter) {
		if (this.expr.getSegment().getKind() != Segment.Kind.TEXT)
			return;

		List<Insn> res = new ArrayList<Insn>(2);
		produceImmediate(res, this.rt, startAddress + this.expr.eval());
		// if address generation takes less than two instructions
		// insert nops to pad it to the right size
		for (int i = res.size(); i < 2; i++)
			res.add(new Insn(this.segment, 0));
		set(res);
	}

	@Override
	public boolean validate(ErrorReporter<Position> reporter) {
		return Insn.validateAddress(getPosition(), this.expr, reporter);
	}
}

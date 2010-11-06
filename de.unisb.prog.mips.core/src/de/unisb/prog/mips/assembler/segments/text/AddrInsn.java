package de.unisb.prog.mips.assembler.segments.text;

import java.util.List;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;


class AddrInsn extends ImmGen<Address> implements Relocateable {

	private final boolean isData;
	private final boolean addressRelativeToGP;

	protected AddrInsn(Segment seg, Opcode opc, Reg rt, Option<Reg> base, Address expr) {
		this(seg, opc.name(), opc, rt, base, expr);
	}

	protected AddrInsn(Segment seg, String pseudoOpName, Opcode opc, Reg rt, Option<Reg> base, Address expr) {
		super(seg, opc, rt, base, expr);
		isData = expr.getSegment().getKind() == Segment.Kind.DATA;
		addressRelativeToGP = isData && base.otherwise(Reg.gp).equals(Reg.gp);
	}

	@Override
	protected void rewrite() {
		if (addressRelativeToGP) {
			Address relative = Expressions.offset(this.expr, -32768);
			set(genImm(Reg.gp, Reg.at, relative));
		}

		else {
			// this constant should trigger a sufficiently large sequence of instructions to be generated
			set(genImm(base.otherwise(Reg.zero), Reg.at, 0x55555555));
		}
	}

	public void relocate(int startAddress, ErrorReporter<Position> reporter) {
		if (!addressRelativeToGP) {
			Address absolute = Expressions.absolute(this.expr);
			List<Insn> addrGen = genImm(base.otherwise(Reg.zero), Reg.at, absolute);

			// we need to allocate enough instructions to generate the address!
			if (addrGen.size() > proxyElements())
				throw new IllegalStateException("address generation took to many instructions!");

			// fill up with nops, if address generation took surprisingly less instructions
			for (int i = addrGen.size(); i < proxyElements(); i++)
				addrGen.add(Insn.nop(segment));

			set(addrGen);
		}
	}

}

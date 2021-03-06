package de.unisb.prog.mips.assembler.segments.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.ProxyElement;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public abstract class ImmGen<T extends Expr> extends ProxyElement<Insn> {

	protected final T expr;
	protected final Opcode opcode;
	protected final Reg rt;
	protected final Option<Reg> base;

	private final String pseudoOpName;

	private static final Set<Opcode> compOpcodes = EnumSet.of(Opcode.addiu, Opcode.ori, Opcode.addi);
	private static final Map<Opcode, IntFunct> opcodeMap = new EnumMap<Opcode, IntFunct>(Opcode.class);

	static {
		opcodeMap.put(Opcode.ori, IntFunct.or);
		opcodeMap.put(Opcode.andi, IntFunct.and);
		opcodeMap.put(Opcode.xori, IntFunct.xor);
		opcodeMap.put(Opcode.addi, IntFunct.add);
		opcodeMap.put(Opcode.addiu, IntFunct.addu);
		opcodeMap.put(Opcode.slti, IntFunct.slt);
		opcodeMap.put(Opcode.sltiu, IntFunct.sltu);
	}

	ImmGen(Segment seg, String pseudoOpName, Opcode opc, Reg rt, Option<Reg> base, T expr) {
		super(seg);
		this.expr = expr;
		this.opcode = opc;
		this.rt = rt;
		this.base = base;
		this.pseudoOpName = pseudoOpName;
	}

	ImmGen(Segment seg, Opcode opc, Reg rt, Option<Reg> base, T expr) {
		this(seg, opc.name(), opc, rt, base, expr);
	}

	protected final List<Insn> genImm(Reg base, Reg temp) {
		return genImm(base, temp, expr);
	}

	protected final List<Insn> genImm(Reg base, Reg temp, T addr) {
		return genImm(base, temp, addr.eval());
	}

	protected final List<Insn> genImm(Reg base, Reg temp, int addr) {
		List<Insn> res;

		if (opcode.getKind() == Kind.LOAD_STORE)
			res = immGenLoadStore(base, temp, addr);
		else if (opcode.getKind() == Kind.IMM)
			res = immGenComp(base, temp, addr);
		else
			throw new IllegalStateException("Cannot generate immediate with opcode " + opcode.name());

		return res;
	}

	private List<Insn> immGenLoadStore(Reg base, Reg temp, int addr) {
		List<Insn> res = new ArrayList<Insn>(3);
		final int at = (temp == base ? Reg.at : temp).ordinal();
		final int br = base.ordinal();

		if (opcode.immFits(addr)) {
			res.add(new Insn(segment, opcode.encode(base.ordinal(), rt.ordinal(), addr)));
		}
		else {
			int lo = addr & 0xffff;
			int hi = addr >>> 16;

			// hi cannot be produced by sign extension
			// and we can use the immediate field of the actual instruction
			// that means that a sign extension of the low 16 bit does not overflow the high 16 bit
			if (hi != 0 && !(hi == 0xffff && lo == 0x8000)) {
				// Adjust high 16, since load sign-extends low 16
				// if high bit of lo is set
				if ((lo & 0x8000) != 0)
					hi += 1;

				res.add(new Insn(segment, Opcode.lui.encode(0, at, hi)));
				if (base != Reg.zero) {
					int word = IntFunct.addu.encode(br, at, at);
					res.add(new Insn(segment, word));
				}
				res.add(new Insn(segment, opcode.encode(at, rt.ordinal(), lo)));
			}

			// sign extension of lower 16 bit produces hi
			else {
				res.add(new Insn(segment, opcode.encode(br, rt.ordinal(), lo)));
			}
		}

		return res;
	}

	private List<Insn> immGenComp(Reg base, Reg temp, int addr) {
		List<Insn> res = new ArrayList<Insn>(3);

		if (opcode.immFits(addr)) {
			res.add(new Insn(segment, opcode.encode(base.ordinal(), rt.ordinal(), addr)));
		}

		else {
			if (base == Reg.zero && compOpcodes.contains(opcode))
				produceImmediate(res, rt, addr);
			else {
				produceImmediate(res, Reg.at, addr);
				IntFunct f = opcodeMap.get(opcode);
				res.add(new Insn(segment, f.encode(base.ordinal(), Reg.at.ordinal(), rt.ordinal())));
			}
		}

		return res;
	}

	protected final List<Insn> produceImmediate(List<Insn> res, Reg rt, int addr) {
		int tgt = rt.ordinal();
		int src = 0;
		if ((addr & 0xffff0000) != 0) {
			res.add(new Insn(segment, Opcode.lui.encode(0, tgt, addr >>> 16)));
			src = tgt;
		}
		if ((addr & 0xffff) != 0)
			res.add(new Insn(segment, Opcode.ori.encode(src, tgt, addr & 0xffff)));
		return res;
	}

	@Override
	protected void appendInternal(Appendable app) throws IOException {
		if (! elements.isEmpty())
			super.appendInternal(app);
		else {
			StringBuffer sb = new StringBuffer();
			expr.append(sb);
			Reg r = base.otherwise(Reg.zero);
			String b = r != Reg.zero ? String.format("($%s)", base.otherwise(Reg.zero)) : "";
			String t = "$" + rt.name();
			String s = String.format("%7s %3s %s%s", pseudoOpName, t, sb.toString(), b);
			app.append(s);
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			appendInternal(sb);
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}

	protected abstract void rewrite();

}

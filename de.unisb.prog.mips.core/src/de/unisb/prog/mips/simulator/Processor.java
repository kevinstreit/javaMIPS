package de.unisb.prog.mips.simulator;

import java.util.HashSet;
import java.util.Set;

import de.unisb.prog.mips.insn.Handler;
import de.unisb.prog.mips.insn.IllegalOpcodeException;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Instructions;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public final class Processor extends ProcessorState implements Handler<Instruction> {

	private final Memory mem;
	private final SysCallHandler os;
	private final ExceptionHandler exc;
	private boolean ignoreBreaks = true;
	private int subsequentNops = 0;
	private final int haltAfterSeenNops = 20;

	private long brkpointBloomFilter = 0;
	private Set<Integer> brkpoints = new HashSet<Integer>();

	private static long brkpointFilterBit(int addr) {
		return 1L << (addr >>> 2 & 0x3f);
	}

	public void addBreakpoint(int addr) {
		brkpointBloomFilter |= brkpointFilterBit(addr);
		brkpoints.add(addr);
	}

	public void clearBreakpoints() {
		brkpoints.clear();
		brkpointBloomFilter = 0;
	}

	public void removeBreakpoint(int addr) {
		if (brkpoints.remove(addr)) {
			brkpointBloomFilter = 0;
			for (int a : brkpoints)
				brkpointBloomFilter |= brkpointFilterBit(a);
		}
	}

	public boolean isBreakpoint(int addr) {
		long bit = brkpointFilterBit(addr);
		if ((brkpointBloomFilter & bit) == 0)
			return false;
		return brkpoints.contains(addr);
	}

	public boolean ignoresBreak() {
		return ignoreBreaks;
	}

	public void setIgnoreBreaks(boolean ignoreBreaks) {
		this.ignoreBreaks = ignoreBreaks;
	}

	public Processor(Memory mem, ExceptionHandler exc, SysCallHandler os) {
		this.mem = mem;
		this.os  = os;
		this.exc = exc;
		gp  = new int[32];
	}

	private static int cmpltu(int a, int b) {
		long la = a & 0xffffffffL;
		long lb = b & 0xffffffffL;
		return la < lb ? 1 : 0;
	}

	public boolean step(boolean dontBreakOnFirst) {
		if (state != ExecutionState.RUNNING)
			return false;

		if (!dontBreakOnFirst && !ignoreBreaks && isBreakpoint(pc)) {
			state = ExecutionState.BREAKPOINT;
			exc.breakpoint(this, mem);
			return false;
		}
		int insn = load(pc, Type.WORD, false);

		// count nops executed in a row
		// to stop after we have seen HALT_AFTER_NOPS
		subsequentNops = insn == 0 ? subsequentNops + 1 : 0;
		if (subsequentNops == haltAfterSeenNops) {
			state = ExecutionState.HALTED;
			return false;
		}

		try {
			Instruction i = Instructions.decode(insn, this);
			if (! i.getKind().changesPc())
				pc += 4;
		} catch (IllegalOpcodeException e) {
			if (exc != null)
				exc.illegalInstruction(this, mem, pc);
		}

		return true;
	}

	public void run(boolean dontBreakOnFirst) {
		while (step(dontBreakOnFirst))
			dontBreakOnFirst = false;
	}

	private int load(int addr, Type tp, boolean signExtend) {
		if (! tp.isAligned(addr))
			exc.unalignedMemory(this, mem, addr);
		return mem.load(addr, tp);
	}

	private void store(int addr, Type tp, int val) {
		if (! tp.isAligned(addr))
			exc.unalignedMemory(this, mem, addr);
		mem.store(addr, val, tp);
	}

	public Instruction j(Opcode opc, int imm) {
		switch (opc) {
		case jal: gp[31] = pc + 4;
		case j:   pc = pc & 0xf0000000 | imm << 2;
		}
		return opc;
	}

	public Instruction i(RegImm ri, int rs, int imm) {
		int s = gp[rs];
		imm = ri.extendImm(imm);
		switch (ri) {
		case bgezl:
		case bgez:    pc += s >= 0 ? imm << 2 : 4; break;
		case bgezall:
		case bgezal:  if (s >= 0) { gp[31] = pc + 4; pc += imm << 2; } else pc += 4; break;
		case bltzl:
		case bltz:    pc += s < 0 ? imm << 2 : 4; break;
		case bltzall:
		case bltzal:  if (s < 0)  { gp[31] = pc + 4; pc += imm << 2; } else pc += 4; break;
		}
		return ri;
	}

	public Instruction i(Opcode opc, int rs, int rt, int imm) {
		int s = gp[rs];
		int t = gp[rt];
		imm = opc.extendImm(imm);
		switch (opc) {
		case beql:
		case beq:   pc += s == t ? imm << 2 : 4; break;
		case bnel:
		case bne:   pc += s != t ? imm << 2 : 4; break;
		case blezl:
		case blez:  pc += s <= 0 ? imm << 2 : 4; break;
		case bgtzl:
		case bgtz:  pc += s > 0 ? imm << 2 : 4; break;

		case addi:  gp[rt] = s + imm; break;
		case addiu: gp[rt] = s + imm; break; // TODO: Correct!
		case slti:  gp[rt] = s < imm ? 1 : 0; break;
		case sltiu: gp[rt] = cmpltu(s, imm); break;
		case andi:  gp[rt] = s & imm; break;
		case ori:   gp[rt] = s | imm; break;
		case xori:  gp[rt] = s ^ imm; break;
		case lui:   gp[rt] = imm << 16; break;

		case lb:    gp[rt] = load(s + imm, Type.BYTE, true); break;
		case lh:    gp[rt] = load(s + imm, Type.HALF, true); break;
		case lwl:   break; // TODO Implement
		case lw:    gp[rt] = load(s + imm, Type.WORD, true); break;
		case lbu:   gp[rt] = load(s + imm, Type.BYTE, false); break;
		case lhu:   gp[rt] = load(s + imm, Type.HALF, false); break;
		case lwr:   break; // TODO Implement
		case sb:    store(s + imm, Type.BYTE, t & 0xff); break;
		case sh:    store(s + imm, Type.HALF, t & 0xffff); break;
		case swl:   break; // TODO Implement
		case sw:    store(s + imm, Type.WORD, t); break;
		case swr:   break; // TODO Implement
		}
		return opc;
	}

	public Instruction r(IntFunct funct, int rs, int rt, int rd, int shamt) {
		int s = gp[rs];
		int t = gp[rt];
		switch (funct) {
		case sll:     gp[rd] = t << shamt; break;
		case srl:     gp[rd] = t >>> shamt; break;
		case sra:     gp[rd] = t >> shamt; break;
		case sllv:    gp[rd] = t << s; break;
		case srlv:    gp[rd] = t >>> s; break;
		case srav:    gp[rd] = t >> s; break;
		case jr:      pc = s; break;
		case jalr:    gp[rd] = pc + 4; pc = s; break;
		case movz:    if (t == 0) gp[rd] = s; break;
		case movn:    if (t != 0) gp[rd] = s; break;
		case syscall: os.syscall(this, mem); break;
		case brk:     {
			if (!ignoreBreaks) {
				state = ExecutionState.BREAKPOINT;
				exc.breakpoint(this, mem);
			}
		}
		break;
		case mfhi:    gp[rd] = hi; break;
		case mthi:    hi = s; break;
		case mflo:    gp[rd] = lo; break;
		case mtlo:    lo = s; break;
		case mult:    {
			long ld = (long) s * (long) t;
			lo = (int) (ld & 0xffffffff);
			hi = (int) (ld >> 32 & 0xffffffff);
		}
		break;
		// TODO: Check, if this is ok.
		case multu:   {
			long a = s & 0xffffffffL;
			long b = t & 0xffffffffL;
			long r = a * b;
			lo = (int) (r & 0xffffffff);
			hi = (int) (r >> 32 & 0xffffffff);
		}
		break;
		case div:     {
			try {
				lo = s / t;
				hi = s % t;
			} catch (ArithmeticException ex) {
				// According to MIPS spec, "No arithmetic exception occurs under any circumstances".
				// => result undefined
			}
			break;
		}
		// TODO: Check, if this is ok.
		case divu:    {
			try {
				long a = s & 0xffffffffL;
				long b = t & 0xffffffffL;
				lo = (int) (a / b);
				hi = (int) (a % b);
			} catch (ArithmeticException ex) {
				// According to MIPS spec, "No arithmetic exception occurs under any circumstances".
				// => result undefined
			}
		}
		break;
		case add:     gp[rd] = s + t; break;
		case addu:    gp[rd] = s + t; break;
		case sub:     gp[rd] = s - t; break;
		case subu:    gp[rd] = s - t; break;
		case and:     gp[rd] = s & t; break;
		case or:      gp[rd] = s | t; break;
		case xor:     gp[rd] = s ^ t; break;
		case nor:     gp[rd] = ~(s | t); break;
		case slt:     gp[rd] = s < t ? 1 : 0; break;
		case sltu:    gp[rd] = cmpltu(s, t); break;
		}
		return funct;
	}
}

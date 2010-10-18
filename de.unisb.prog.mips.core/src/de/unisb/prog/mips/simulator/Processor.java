package de.unisb.prog.mips.simulator;

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

	public boolean ignoresBreak() {
		return this.ignoreBreaks;
	}

	public void setIgnoreBreaks(boolean ignoreBreaks) {
		this.ignoreBreaks = ignoreBreaks;
	}

	public Processor(Memory mem, ExceptionHandler exc, SysCallHandler os) {
		this.mem = mem;
		this.os  = os;
		this.exc = exc;
		this.gp  = new int[32];
	}

	private static int cmpltu(int a, int b) {
		int la = a & 0xffff;
		int lb = b & 0xffff;
		a >>>= 16;
		b >>>= 16;
		return a < b || la < lb ? 1 : 0;
	}

	public boolean step() {
		if (this.state != ExecutionState.RUNNING)
			return false;

		int insn = load(this.pc, Type.WORD, false);

		// count nops executed in a row
		// to stop after we have seen HALT_AFTER_NOPS
		this.subsequentNops = insn == 0 ? this.subsequentNops + 1 : 0;
		if (this.subsequentNops == this.haltAfterSeenNops) {
			this.state = ExecutionState.HALTED;
			return false;
		}

		try {
			Instruction i = Instructions.decode(insn, this);
			if (! i.getKind().changesPc())
				this.pc += 4;
		} catch (IllegalOpcodeException e) {
			if (this.exc != null)
				this.exc.illegalInstruction(this, this.mem, this.pc);
		}

		return true;
	}

	public void run() {
		while (step());
	}

	private int load(int addr, Type tp, boolean signExtend) {
		if (! tp.isAligned(addr))
			exc.unalignedMemory(this, mem, addr);
		return this.mem.load(addr, tp);
	}

	private void store(int addr, Type tp, int val) {
		if (! tp.isAligned(addr))
			exc.unalignedMemory(this, mem, addr);
		this.mem.store(addr, val, tp);
	}

	public Instruction j(Opcode opc, int imm) {
		switch (opc) {
		case jal: this.gp[31] = this.pc + 4;
		case j:   this.pc = this.pc & 0xf0000000 | imm << 2;
		}
		return opc;
	}

	public Instruction i(RegImm ri, int rs, int imm) {
		int s = this.gp[rs];
		imm = ri.extendImm(imm);
		switch (ri) {
		case bgezl:
		case bgez:    this.pc += s >= 0 ? imm << 2 : 4; break;
		case bgezall:
		case bgezal:  if (s >= 0) { this.gp[31] = this.pc + 4; this.pc += imm << 2; } else this.pc += 4; break;
		case bltzl:
		case bltz:    this.pc += s < 0 ? imm << 2 : 4; break;
		case bltzall:
		case bltzal:  if (s < 0)  { this.gp[31] = this.pc + 4; this.pc += imm << 2; } else this.pc += 4; break;
		}
		return ri;
	}

	public Instruction i(Opcode opc, int rs, int rt, int imm) {
		int s = this.gp[rs];
		int t = this.gp[rt];
		imm = opc.extendImm(imm);
		switch (opc) {
		case beql:
		case beq:   this.pc += s == t ? imm << 2 : 4; break;
		case bnel:
		case bne:   this.pc += s != t ? imm << 2 : 4; break;
		case blezl:
		case blez:  this.pc += s <= 0 ? imm << 2 : 4; break;
		case bgtzl:
		case bgtz:  this.pc += s > 0 ? imm << 2 : 4; break;

		case addi:  this.gp[rt] = s + imm; break;
		case addiu: this.gp[rt] = s + imm; break; // TODO: Correct!
		case slti:  this.gp[rt] = s < imm ? 1 : 0; break;
		case sltiu: this.gp[rt] = cmpltu(s, imm); break;
		case andi:  this.gp[rt] = s & imm; break;
		case ori:   this.gp[rt] = s | imm; break;
		case xori:  this.gp[rt] = s ^ imm; break;
		case lui:   this.gp[rt] = imm << 16; break;

		case lb:    this.gp[rt] = load(s + imm, Type.BYTE, true); break;
		case lh:    this.gp[rt] = load(s + imm, Type.HALF, true); break;
		case lwl:   break; // TODO Implement
		case lw:    this.gp[rt] = load(s + imm, Type.WORD, true); break;
		case lbu:   this.gp[rt] = load(s + imm, Type.BYTE, false); break;
		case lhu:   this.gp[rt] = load(s + imm, Type.HALF, false); break;
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
		int s = this.gp[rs];
		int t = this.gp[rt];
		switch (funct) {
		case sll:     this.gp[rd] = t << shamt; break;
		case srl:     this.gp[rd] = t >>> shamt; break;
		case sra:     this.gp[rd] = t >> shamt; break;
		case sllv:    this.gp[rd] = t << s; break;
		case srlv:    this.gp[rd] = t >>> s; break;
		case srav:    this.gp[rd] = t >> s; break;
		case jr:      this.pc = s; break;
		case jalr:    this.gp[rd] = this.pc + 4; this.pc = s; break;
		case movz:    if (t == 0) this.gp[rd] = s; break;
		case movn:    if (t != 0) this.gp[rd] = s; break;
		case syscall: this.os.syscall(this, this.mem); break;
		case brk:     {
			if (!this.ignoreBreaks) {
				this.state = ExecutionState.BREAKPOINT;
				if (this.exc != null)
					this.exc.breakpoint(this, this.mem);
			}
		}
		break;
		case mfhi:    this.gp[rd] = this.hi; break;
		case mthi:    this.hi = s; break;
		case mflo:    this.gp[rd] = this.lo; break;
		case mtlo:    this.lo = s; break;
		case mult:    {
			long ld = (long) s * (long) t;
			this.lo = (int) (ld & 0xffffffff);
			this.hi = (int) (ld >> 32 & 0xffffffff);
		}
		break;
		// TODO: Check, if this is ok.
		case multu:   {
			long a = s & 0xffffffffL;
			long b = t & 0xffffffffL;
			long r = a * b;
			this.lo = (int) (r & 0xffffffff);
			this.hi = (int) (r >> 32 & 0xffffffff);
		}
		break;
		case div:     this.lo = s / t; this.hi = s % t; break;
		// TODO: Check, if this is ok.
		case divu:    {
			long a = s & 0xffffffffL;
			long b = t & 0xffffffffL;
			this.lo = (int) (a / b);
			this.hi = (int) (a % b);
		}
		break;
		case add:     this.gp[rd] = s + t; break;
		case addu:    this.gp[rd] = s + t; break;
		case sub:     this.gp[rd] = s - t; break;
		case subu:    this.gp[rd] = s - t; break;
		case and:     this.gp[rd] = s & t; break;
		case or:      this.gp[rd] = s | t; break;
		case xor:     this.gp[rd] = s ^ t; break;
		case nor:     this.gp[rd] = ~(s | t); break;
		case slt:     this.gp[rd] = s < t ? 1 : 0; break;
		case sltu:    this.gp[rd] = cmpltu(s, t); break;
		}
		return funct;
	}
}

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
		int insn = load(pc, Type.WORD, false);
		try {
			Instruction i = Instructions.decode(insn, this);
			if (! i.getKind().changesPc())
				pc += 4;
		} catch (IllegalOpcodeException e) {
			exc.illegalInstruction(this, mem, pc);
		}
		
		return true;
	}
	
	public void run() {
		while (step());
	}
	
	private int load(int addr, Type tp, boolean signExtend) {
		if (! tp.isAligned(addr)) 
			throw new UnalignedMemoryException(this, addr);
		return mem.load(addr, tp);
	}
	
	private void store(int addr, Type tp, int val) {
		if (! tp.isAligned(addr)) 
			throw new UnalignedMemoryException(this, addr);
		mem.store(addr, val, tp);
	}
	
	@Override
	public Instruction j(Opcode opc, int imm) {
		switch (opc) {
		case jal: gp[31] = pc + 4;
		case j:   pc = (pc & 0xf0000000) | (imm << 2);
		}
		return opc;
	}
	
	@Override
	public Instruction i(RegImm ri, int rs, int imm) {
		imm = ri.extendImm(imm);
		switch (ri) {
		case bgezl:
		case bgez:    if (rs >= 0)                    pc += imm << 2;   break;
		case bgezall:
		case bgezal:  if (rs >= 0) { gp[31] = pc + 4; pc += imm << 2; } break;
		case bltzl:
		case bltz:    if (rs < 0)                     pc += imm << 2;   break;
		case bltzall:
		case bltzal:  if (rs < 0)  { gp[31] = pc + 4; pc += imm << 2; } break;
		}
		return ri;
	}
	
	@Override
	public Instruction i(Opcode opc, int rs, int rt, int imm) {
		int s = gp[rs];
		int t = gp[rt];
		imm = opc.extendImm(imm);
		switch (opc) {
		case beql:
		case beq:   if (s == t) pc += imm << 2; break;
		case bnel:
		case bne:   if (s != t) pc += imm << 2; break;
		case blezl:
		case blez:  if (s <= 0) pc += imm << 2; break;
		case bgtzl:
		case bgtz:  if (s >  0) pc += imm << 2; break;
		
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
		case sb:    store(s + imm, Type.BYTE, t); break;
		case sh:    store(s + imm, Type.HALF, t >>> 16); break;
		case swl:   break; // TODO Implement
		case sw:    store(s + imm, Type.WORD, t); break;
		case swr:   break; // TODO Implement
		}
		return opc;
	}

	@Override
	public Instruction r(IntFunct funct, int rs, int rt, int rd, int shamt) {
		int s = gp[rs];
		int t = gp[rt];
		switch (funct) {
		case sll:     gp[rd] = s << shamt; break;
		case srl:     gp[rd] = s >>> shamt; break;
		case sra:     gp[rd] = s >> shamt; break;
		case sllv:    gp[rd] = s << t; break;
		case srlv:    gp[rd] = s >>> t; break;
		case srav:    gp[rd] = s >> t; break;
		case jr:      pc = s; break;
		case jalr:    gp[rd] = pc + 4; pc = s; break;
		case movz:    if (t == 0) gp[rd] = s; break;
		case movn:    if (t != 0) gp[rd] = s; break;
		case syscall: os.syscall(this, mem); break;
		case brk:     break; // TODO break to debugger
		case mfhi:    gp[rd] = hi; break;
		case mthi:    hi = s; break;
		case mflo:    gp[rd] = lo; break;
		case mtlo:    lo = s; break;
		case mult:    {
			long ld = (long) s * (long) t;
			lo = (int) (ld & 0xffffffff);
			hi = (int) ((ld >> 32) & 0xffffffff);
		}
		// TODO: Check, if this is ok.
		case multu:   {
			long a = (long) s & 0xffffffffL;
			long b = (long) t & 0xffffffffL;
			long r = a * b;
			lo = (int) (r & 0xffffffff);
			hi = (int) ((r >> 32) & 0xffffffff);
		}
		case div:     lo = s / t; hi = s % t; break;
		// TODO: Check, if this is ok.
		case divu:    lo = s / t; hi = s % t; break;
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

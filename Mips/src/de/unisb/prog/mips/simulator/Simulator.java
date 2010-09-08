package de.unisb.prog.mips.simulator;

import de.unisb.prog.mips.insn.Decode;
import de.unisb.prog.mips.insn.FloatFunct;
import de.unisb.prog.mips.insn.Handler;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Simulator extends ProcessorState implements Handler {
	
	private CoarseMemory mem;
	
	private int gp(int r) {
		return gp(r);
	}
	
	private float fp(int r) {
		return fp(r);
	}
	
	private static int signExtend(int v, int bit) {
		int s = 32 - bit;
		return (v << s) >> s;
	}
	
	private static int cmpltu(int a, int b) {
		int la = a & 0xffff;
		int lb = b & 0xffff;
		a >>>= 16;
		b >>>= 16;
		return a < b || la < lb ? 1 : 0;
	}
	
	public void step() {
		int insn = load(pc, 0, Type.WORD, false);
		Decode.decode(insn, this);
		pc += 4;
	}
	
	private void floatDouble(int ft, int fs, int fd, int funct) {
		throw new IllegalOpcodeException(this);
	}
	
	private void floatSingle(int ft, int fs, int fd, int funct) {
		switch (funct) {
		case 0x32: fpc = fp(fs) == fp(ft); break;
		case 0x3c: fpc = fp(fs) <  fp(ft); break;
		case 0x3d: fpc = fp(fs) <= fp(ft); break;
		}
		switch (FloatFunct.values()[funct]) {
		case add: fp[fd] = fp(fs) + fp(ft); break;
		case sub: fp[fd] = fp(fs) - fp(ft); break;
		case mul: fp[fd] = fp(fs) * fp(ft); break;
		case div: fp[fd] = fp(fs) / fp(ft); break;
		}
	}

	@Override
	public void f(int fmt, int ft, int fs, int fd, int funct) {
		switch (fmt) {
		case 0x10: floatSingle(ft, fs, fd, funct); break;
		case 0x11: floatDouble(ft, fs, fd, funct); break;
		default:
			throw new IllegalOpcodeException(this);
		}
		
	}

	@Override
	public void j(int op, int imm) {
		switch (Opcode.values()[op]) {
		case jal: 
			gp[31] = pc + 4;
		case j:
			pc = (pc & 0xf0000000) | (imm << 2);
			break;
		default:
			throw new IllegalStateException(String.format("format j and opcode %x", op));
		}
	}
	
	private int computeAddr(int addr, int disp, Type tp) {
		addr += signExtend(disp, 16);
		if (! tp.isAligned(addr)) 
			throw new UnalignedMemoryException(this, addr);
		return addr;
	}
	
	private int load(int addr, int disp, Type tp, boolean signExtend) {
		int memAddr = computeAddr(addr, disp, tp);
		return mem.load(addr, tp);
	}
	
	private void store(int addr, int disp, Type tp, int val) {
		int memAddr = computeAddr(addr, disp, tp);
		mem.store(memAddr, val, tp);
	}
	
	private void i(RegImm ri, int rs, int imm) {
		switch (ri) {
		case bgez:    if (rs >= 0) pc += 4 + (signExtend(imm, 16) << 2); break;
		case bgezal:  if (rs >= 0) { gp[31] = pc + 4; pc += 4 + (signExtend(imm, 16) << 2); } break;
		case bltz:    if (rs < 0) pc += 4 + (signExtend(imm, 16) << 2); break;
		case bltzal:  if (rs < 0) { gp[31] = pc + 4; pc += 4 + (signExtend(imm, 16) << 2); } break;
		}
	}
	
	@Override
	public void i(int op, int rs, int rt, int imm) {
		int s = gp(rs);
		int t = gp(rt);
		switch (Opcode.values()[op]) {
		case regimm: i(RegImm.values()[rt], rs, imm); break;
		case beq:   if (s == t) pc += 4 + (signExtend(imm, 16) << 2); break;
		case bne:   if (s != t) pc += 4 + (signExtend(imm, 16) << 2); break;
		case blez:  if (s <= 0) pc += 4 + (signExtend(imm, 16) << 2); break;
		case bgtz:  if (s >  0) pc += 4 + (signExtend(imm, 16) << 2); break;
		
		case addi:  gp[rt] = s + signExtend(imm, 16); break;
		case addiu: gp[rt] = s + signExtend(imm, 16); break; // TODO: Correct!
		case slti:  gp[rt] = s < signExtend(imm, 16) ? 1 : 0; break;
		case sltiu: gp[rt] = cmpltu(s, signExtend(imm, 16)); break;
		case andi:  gp[rt] = s & imm; break;
		case ori:   gp[rt] = s | imm; break;
		case xori:  gp[rt] = s ^ imm; break;
		case lui:   gp[rt] = imm << 16; break;
		
		case lb:    gp[rt] = load(s, imm, Type.BYTE, true); break;
		case lh:    gp[rt] = load(s, imm, Type.HALF, true); break;
		case lwl:   throw new IllegalOpcodeException(this);
		case lw:    gp[rt] = load(s, imm, Type.WORD, true); break;
		case lbu:   gp[rt] = load(s, imm, Type.BYTE, false); break;
		case lhu:   gp[rt] = load(s, imm, Type.HALF, false); break;
		case lwr:   throw new IllegalOpcodeException(this);
		case sb:    store(s, imm, Type.BYTE, t); break;
		case sh:    store(s, imm, Type.HALF, t >>> 16); break;
		case swl:   throw new IllegalOpcodeException(this);
		case sw:    store(s, imm, Type.WORD, t); break;
		case swr:   throw new IllegalOpcodeException(this);
		}
		
	}

	@Override
	public void r(int rs, int rt, int rd, int shamt, int funct) {
		int s = gp(rs);
		int t = gp(rt);
		switch (IntFunct.values()[funct]) {
		case sll:   gp[rd] = s << shamt; break;
		case srl:   gp[rd] = s >>> shamt; break;
		case sra:   gp[rd] = s >> shamt; break;
		case sllv:  gp[rd] = s << t; break;
		case srlv:  gp[rd] = s >>> t; break;
		case srav:  gp[rd] = s >> t; break;
		case jr:    pc = s; break;
		case jalr:  gp[rd] = pc + 4; pc = s; break;
		case mfhi:  gp[rd] = hi; break;
		case mflo:  gp[rd] = lo; break;
		case mult:  {
			long ld = (long) s * (long) t;
			lo = (int) (ld & 0xffffffff);
			hi = (int) ((ld >> 32) & 0xffffffff);
		}
		case multu: throw new IllegalOpcodeException(this);
		case div:   lo = s / t; hi = s % t; break;
		case divu:  throw new IllegalOpcodeException(this);
		case add:   gp[rd] = s + t; break;
		case addu:  gp[rd] = s + t; break;
		case sub:   gp[rd] = s - t; break;
		case subu:  gp[rd] = s - t; break;
		case and:   gp[rd] = s & t; break;
		case or:    gp[rd] = s | t; break;
		case xor:   gp[rd] = s ^ t; break;
		case slt:   gp[rd] = s < t ? 1 : 0; break;
		case sltu:  gp[rd] = cmpltu(s, t); break;
		}
	}
}

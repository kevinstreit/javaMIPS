package de.unisb.prog.mips.assembler.segments.text;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.util.Option;

public final class Factory {
	
	// generate an instruction of shape
	// opcode rt rs imm
	public static interface RegConst {
		Element create(Text text, Instruction insn, Reg rs, Reg rt, int imm);
	}
	
	// opcode rt address
	// address ::= label? (+/- offset)? (rs)?
	public static interface RegAddr {
		Element create(Text text, Instruction insn, Reg rt, Option<Reg> rs, Address addr);
	}

	// opcode rd rs rt
	public static interface ThreeReg {
		Element create(Text text, Instruction insn, Reg rs, Reg rt, Reg rd);
	}
	
	// opcode rs rt label
	public static interface Branch {
		Element create(Text text, Instruction insn, Reg rs, Reg rt, LabelRef ref);
	}
	
	private static final ThreeReg threeRegFactory = new ThreeReg() {
		@Override
		public Element create(Text text, Instruction insn, Reg rs, Reg rt, Reg rd) {
			return text.normal(insn, rs, rt, rd, 0);
		}
	};
	
	public static ThreeReg threeReg(Instruction insn) {
		return threeRegFactory;
	}
	
	private static final RegAddr loadStore = new RegAddr() {
		@Override
		public Element create(Text text, Instruction insn, Reg rt, Option<Reg> base, Address addr) {
			if (!(insn instanceof Opcode))
				throw new IllegalArgumentException("insn has to be instance of Opcode: " + insn);
			return text.loadstore((Opcode) insn, rt, base, addr);
		}
		
	};
	
	private static final RegAddr branchCmp0 = new RegAddr() {
		@Override
		public Element create(Text text, Instruction insn, Reg rt, Option<Reg> base, Address addr) {
			return text.condjump(insn, rt, addr);
		}
	};
	
	public static RegAddr regAddr(Instruction insn) {
		return (insn.getOpcode() == Opcode.regimm) ? branchCmp0 : loadStore;
	}
	
	private static final RegConst shamt = new RegConst() {
		@Override
		public Element create(Text text, Instruction insn, Reg rs, Reg rt, int imm) {
			// note the register renaming here. 
			// the RegConst form also includes the R-Form instructions
			// with a shift amount (shamt). They have the same syntactical
			// representation. 
			return text.normal(insn, Reg.zero, rs, rt, imm);
		}
	};
	
	private static final RegConst imm = new RegConst() {
		@Override
		public Element create(Text text, Instruction insn, Reg rs, Reg rt, int imm) {
			return text.imm(insn, rs, rt, imm);
		}
	};
	
	public static RegConst regConst(Instruction insn) {
		return insn.getKind() == Kind.SHAMT ? shamt : imm;
	}
	
	private static final Branch branch = new Branch() {
		@Override
		public Element create(Text text, Instruction insn, Reg rs, Reg rt, LabelRef ref) {
			return text.condjump(insn, rs, rt, ref);
		}
	};
	
	public static Branch branch(Instruction insn) {
		return branch;
	}
	
	
}

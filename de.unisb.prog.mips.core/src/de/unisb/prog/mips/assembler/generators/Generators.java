package de.unisb.prog.mips.assembler.generators;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Instructions;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Kind;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;
import de.unisb.prog.mips.util.Bitfield;

public class Generators {

	public static final Bitfield[] DTS = new Bitfield[] {
		Instruction.FIELD_RD, Instruction.FIELD_RT, Instruction.FIELD_RS
	};

	public static final Bitfield[] DST = new Bitfield[] {
		Instruction.FIELD_RD, Instruction.FIELD_RS, Instruction.FIELD_RT
	};

	public static final Bitfield[] DT = new Bitfield[] {
		Instruction.FIELD_RD, Instruction.FIELD_RT
	};

	public static final Bitfield[] ST = new Bitfield[] {
		Instruction.FIELD_RS, Instruction.FIELD_RT,
	};

	public static final Bitfield[] TS = new Bitfield[] {
		Instruction.FIELD_RT, Instruction.FIELD_RS,
	};

	public static final Bitfield[] DS = new Bitfield[] {
		Instruction.FIELD_RD, Instruction.FIELD_RS,
	};

	public static final Bitfield[] S = new Bitfield[] {
		Instruction.FIELD_RS
	};

	public static final Bitfield[] D = new Bitfield[] {
		Instruction.FIELD_RD
	};

	public static final Bitfield[] T = new Bitfield[] {
		Instruction.FIELD_RT
	};

	public static final Bitfield[] NONE = new Bitfield[0];

	public static class RegGenerator extends InstructionGenerator {

		public RegGenerator(AddressMode am, Bitfield... regFields) {
			super(am, regFields);
		}

		public RegGenerator(Bitfield... regFields) {
			super(AddressMode.NONE, regFields);
		}

		@Override
		public Element generate(Text text, String opcode, OperandInstance inst) {
			Instruction i = Instructions.get(opcode);
			int word = i.encodeOpcodeInto(0);
			word = encodeRegisters(word, inst.getRegisters());
			word = addEncoding(word, inst);
			return text.word(word);
		}

		protected int addEncoding(int word, OperandInstance inst) {
			return word;
		}

	}

	public static final InstructionGenerator THREE_REG  = new RegGenerator(AddressMode.NONE, DTS);

	public static final InstructionGenerator SHAMT      = new RegGenerator(AddressMode.SHAMT, DT) {
		@Override
		protected int addEncoding(int word, OperandInstance inst) {
			return Instruction.FIELD_SHAMT.insert(word, inst.getExpr().eval());
		}
	};

	public static final InstructionGenerator IMM      = new RegGenerator(AddressMode.EXPR, TS) {
		@Override
		protected int addEncoding(int word, OperandInstance inst) {
			return Instruction.FIELD_IMM.insert(word, inst.getExpr().eval());
		}
	};

	public static final InstructionGenerator LUI = new RegGenerator(AddressMode.EXPR, T) {
		@Override
		protected int addEncoding(int word, OperandInstance inst) {
			return Instruction.FIELD_IMM.insert(word, inst.getExpr().eval());
		}
	};

	public static final InstructionGenerator ABSJUMP = new InstructionGenerator(AddressMode.LABEL, NONE) {
		@Override
		public Element generate(Text text, String opcode, OperandInstance inst) {
			Opcode opc = Opcode.valueOf(opcode);
			return text.absjump(opc, inst.getLabel());
		}
	};

	public static final InstructionGenerator BRANCH_2OP = new InstructionGenerator(AddressMode.LABEL, ST) {
		@Override
		public Element generate(Text text, String opcode, OperandInstance inst) {
			Instruction i = Instructions.get(opcode);
			List<Reg> regs = inst.getRegisters();
			return text.condjump(i, regs.get(0), regs.get(1), inst.getLabel());
		}
	};

	public static final InstructionGenerator BRANCH_1OP = new InstructionGenerator(AddressMode.LABEL, S) {
		@Override
		public Element generate(Text text, String opcode, OperandInstance inst) {
			Instruction i = Instructions.get(opcode);
			List<Reg> regs = inst.getRegisters();
			return text.condjump(i, regs.get(0), inst.getLabel());
		}
	};

	public static final InstructionGenerator LOAD_STORE = new InstructionGenerator(AddressMode.LABEL_EXPR_BASE, T) {
		@Override
		public Element generate(Text text, String opcode, OperandInstance inst) {
			Opcode opc = Opcode.valueOf(opcode);
			return text.loadstore(opc, inst.getRegisters().get(0), inst.getBase(), inst.genAddress());
		}
	};

	public static final InstructionGenerator NOP = new InstructionGenerator(AddressMode.NONE, NONE) {
		@Override
		public Element generate(Text text, String opcode, OperandInstance inst) {
			return text.word(0);
		}

		@Override
		public boolean isLegal() {
			return false;
		}
	};

	private static final Map<Kind, InstructionGenerator> kindMap = new EnumMap<Kind, InstructionGenerator>(Kind.class);

	static {
		kindMap.put(Kind.THREE_REG, THREE_REG);
		kindMap.put(Kind.INDIR_JUMP, THREE_REG);
		kindMap.put(Kind.ABS_JUMP, ABSJUMP);
		kindMap.put(Kind.IMM, IMM);
		kindMap.put(Kind.SHAMT, SHAMT);
		kindMap.put(Kind.REL_JUMP_CMP_REG, BRANCH_2OP);
		kindMap.put(Kind.REL_JUMP_CMP_ZERO, BRANCH_1OP);
		kindMap.put(Kind.LOAD_STORE, LOAD_STORE);
	}

	private final Map<String, List<InstructionGenerator>> registry = new HashMap<String, List<InstructionGenerator>>();

	private final <T extends Enum<T>> void register(T v, InstructionGenerator op) {
		register(v.name(), op);
	}

	private final <T extends Enum<T> & Instruction> void register(Class<T> cls) {
		for (T e : cls.getEnumConstants())
			if (e.valid())
				register(e, kindMap.get(e.getKind()));
	}

	public void register(String name, InstructionGenerator op) {
		List<InstructionGenerator> list = registry.get(name);
		if (list == null) {
			list = new LinkedList<InstructionGenerator>();
			registry.put(name, list);
		}
		list.add(op);
	}

	public boolean contains(String str) {
		return registry.containsKey(str);
	}

	public List<InstructionGenerator> get(String str) {
		List<InstructionGenerator> res = registry.get(str);
		if (res == null)
			throw new IllegalStateException("No instruction generator for: " + str);
		return res;
	}

	public Set<String> getAvailableOpcodes() {
		return Collections.unmodifiableSet(registry.keySet());
	}

	private static Generators instance = null;

	public static Generators getInstance() {
		if (instance == null)
			instance = new Generators();
		return instance;
	}

	private Generators() {
		addMachine();
		addPseudos();
	}

	private void addMachine() {
		register(Opcode.class);
		register(RegImm.class);

		// some exceptions :)

		// grrrr! lui is the only opcode instruction where RS has to be 0!
		registry.get(Opcode.lui.name()).clear();
		register(Opcode.lui,       LUI);

		register(IntFunct.sll,     SHAMT);
		register(IntFunct.srl,     SHAMT);
		register(IntFunct.sra,     SHAMT);
		register(IntFunct.sllv,    new RegGenerator(DTS));
		register(IntFunct.srlv,    new RegGenerator(DTS));
		register(IntFunct.srav,    new RegGenerator(DTS));

		register(IntFunct.movz,    new RegGenerator(DST));
		register(IntFunct.movn,    new RegGenerator(DST));

		register(IntFunct.add,     new RegGenerator(DST));
		register(IntFunct.addu,    new RegGenerator(DST));
		register(IntFunct.sub,     new RegGenerator(DST));
		register(IntFunct.subu,    new RegGenerator(DST));
		register(IntFunct.and,     new RegGenerator(DST));
		register(IntFunct.or,      new RegGenerator(DST));
		register(IntFunct.xor,     new RegGenerator(DST));
		register(IntFunct.nor,     new RegGenerator(DST));
		register(IntFunct.slt,     new RegGenerator(DST));
		register(IntFunct.sltu,    new RegGenerator(DST));

		register(IntFunct.syscall, new RegGenerator(NONE));
		register(IntFunct.brk,     new RegGenerator(NONE));
		register(IntFunct.jalr,    new RegGenerator(DS));
		register(IntFunct.jalr,    new RegGenerator(S) {
			@Override
			protected int addEncoding(int word, OperandInstance inst) {
				return Instruction.FIELD_RD.insert(word, Reg.ra.ordinal());
			}
		});
		register(IntFunct.jr,      new RegGenerator(S));
		register(IntFunct.mult,    new RegGenerator(ST));
		register(IntFunct.multu,   new RegGenerator(ST));
		register(IntFunct.div,     new RegGenerator(ST));
		register(IntFunct.divu,    new RegGenerator(ST));
		register(IntFunct.mfhi,    new RegGenerator(D));
		register(IntFunct.mflo,    new RegGenerator(D));
		register(IntFunct.mthi,    new RegGenerator(S));
		register(IntFunct.mtlo,    new RegGenerator(S));
	}

	private void addPseudos() {
		register("li", new InstructionGenerator(AddressMode.EXPR, T) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.constant(inst.getRegisters().get(0), inst.getExpr().eval());
			}
		});

		register("la", new InstructionGenerator(AddressMode.LABEL_EXPR_BASE, T) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.address(inst.getRegisters().get(0), inst.getBase(), inst.genAddress());
			}
		});

		register("nop", new InstructionGenerator(AddressMode.NONE, NONE) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.word(0);
			}
		});

		register("not", new InstructionGenerator(AddressMode.NONE, DT) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				List<Reg> regs = inst.getRegisters();
				return text.normal(IntFunct.nor, Reg.zero, regs.get(1), regs.get(0));
			}
		});

		register("neg", new InstructionGenerator(AddressMode.NONE, DT) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				List<Reg> regs = inst.getRegisters();
				return text.normal(IntFunct.sub, Reg.zero, regs.get(1), regs.get(0));
			}
		});

		register("negu", new InstructionGenerator(AddressMode.NONE, DT) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				List<Reg> regs = inst.getRegisters();
				return text.normal(IntFunct.subu, Reg.zero, regs.get(1), regs.get(0));
			}
		});

		register("move", new InstructionGenerator(AddressMode.NONE, DT) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				List<Reg> regs = inst.getRegisters();
				return text.normal(IntFunct.or, Reg.zero, regs.get(1), regs.get(0), 0);
			}
		});

		register("b", new InstructionGenerator(AddressMode.LABEL, NONE) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.condjump(Opcode.beq, Reg.zero, Reg.zero, inst.getLabel());
			}
		});

		register("beqz", new InstructionGenerator(AddressMode.LABEL, S) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.condjump(Opcode.beq, inst.getRegisters().get(0), Reg.zero, inst.getLabel());
			}
		});

		register("bnez", new InstructionGenerator(AddressMode.LABEL, S) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.condjump(Opcode.bne, inst.getRegisters().get(0), Reg.zero, inst.getLabel());
			}
		});

		register("ret", new InstructionGenerator(AddressMode.NONE, NONE) {
			@Override
			public Element generate(Text text, String opcode, OperandInstance inst) {
				return text.normal(IntFunct.jr, Reg.ra, Reg.zero, Reg.zero, 0);
			}
		});

		// add convenience forms for div and mult that move the lower result back to a destination register
		// and make the opcodes look like three-address code instructions
		for (final IntFunct funct : new IntFunct[] { IntFunct.div, IntFunct.divu, IntFunct.mult, IntFunct.multu }) {
			register(funct.name(), new InstructionGenerator(AddressMode.NONE, DST) {
				@Override
				public Element generate(Text text, String opcode, OperandInstance inst) {
					List<Reg> regs = inst.getRegisters();
					return text.word(
							funct.encode(regs.get(1).ordinal(), regs.get(2).ordinal(), 0),
							IntFunct.mflo.encode(0, 0, regs.get(0).ordinal())
					);
				}
			});
		}
	}

}

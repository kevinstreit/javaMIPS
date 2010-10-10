package de.unisb.prog.mips.assembler.generators;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import de.unisb.prog.mips.assembler.Address;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Offset;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.util.Option;

public class OperandInstance {
	
	private final List<Reg> regs;
	private final Option<Reg> base;
	private final Offset offset;
	
	public OperandInstance(List<Reg> regs, Offset off, Option<Reg> base) {
		this.regs = regs;
		this.base = base;
		this.offset = off;
	}
	
	public List<Reg> getRegisters() {
		return regs;
	}
	
	public Option<Reg> getBase() {
		return base;
	}

	public Expr getExpr() {
		return offset.getExpr();
	}

	public LabelRef getLabel() {
		return offset.getLabel();
	}
	
	public boolean hasLabel() {
		return offset.getLabelOption().isSet();
	}
	
	public boolean hasExpr() {
		return offset.getExprOption().isSet();
	}
	
	public boolean hasBase() {
		return base.isSet();
	}
	
	public Address genAddress() {
		return offset;
	}

	enum State {
		MUST("requires"), MAY("allows"), NO("forbids");
		
		private final String phrase;
		
		private State(String phrase) {
			this.phrase = phrase;
		}
		
		public boolean isOk(Option<?> there) {
			return there.isSet() ? this != NO : this != MUST;
		}
		
		public String phrase() {
			return phrase;
		}
	}
	
	private static final Map<AddressMode, State[]> ALLOWED = new EnumMap<AddressMode, State[]>(AddressMode.class);
	
	static { 
		//                                                     base        label       expr
		ALLOWED.put(AddressMode.EXPR,            new State[] { State.NO,   State.NO,   State.MUST });
		ALLOWED.put(AddressMode.EXPR_BASE,       new State[] { State.MAY,  State.NO,   State.MUST });
		ALLOWED.put(AddressMode.LABEL,           new State[] { State.NO,   State.MUST, State.NO });
		ALLOWED.put(AddressMode.LABEL_EXPR,      new State[] { State.NO,   State.MAY,  State.MAY });
		ALLOWED.put(AddressMode.LABEL_EXPR_BASE, new State[] { State.MAY,  State.MAY,  State.MAY });
		ALLOWED.put(AddressMode.SHAMT,           new State[] { State.NO,   State.NO,   State.MUST });
		ALLOWED.put(AddressMode.NONE,            new State[] { State.NO,   State.NO,   State.NO });
	}
	
	
	public static enum Errors {
		REG_NO, BASE_REG, LABEL, EXPR
	}
	
	public InstructionGenerator select(List<InstructionGenerator> gens) {
		for (InstructionGenerator g : gens) 
			if (fits(g))
				return g;
		throw new IllegalStateException("No valid instruction set generator found");
	}
	
	public boolean fits(InstructionGenerator g) {
		return regs.size() == g.getNumberOfRegs() && g.getAddressMode().fits(this);
	}
	
	private String check(InstructionGenerator g) {
		if (! fits(g)) {
			String sep = "";
			String regs = "";
			for (int i = g.getNumberOfRegs(); i > 0; i--) {
				regs += sep + "reg";
				sep = " ";
			}
			return String.format("illegal instruction format. required: opcode %s %s", regs, g.getAddressMode().stringRepr());
		}
		return "";
	}
	
	public boolean check(List<InstructionGenerator> gens, ErrorReporter<?> report) {
		List<String> errors = new ArrayList<String>(gens.size());
		for (InstructionGenerator gen : gens)  {
			String err = check(gen);
			if (err.length() == 0)
				return true;
			else 
				errors.add(err);
		}
		for (String e : errors)
			report.error(e, null);
		
		return false;
	}
	
}

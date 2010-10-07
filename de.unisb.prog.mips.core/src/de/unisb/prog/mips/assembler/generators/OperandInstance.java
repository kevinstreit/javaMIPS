package de.unisb.prog.mips.assembler.generators;

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
			System.out.println("" + there.isSet() + " " + this.name() );
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
		ALLOWED.put(AddressMode.LABEL_EXPR,      new State[] { State.NO,   State.MUST, State.MAY });
		ALLOWED.put(AddressMode.LABEL_EXPR_BASE, new State[] { State.MAY,  State.MUST, State.MAY });
		ALLOWED.put(AddressMode.SHAMT,           new State[] { State.NO,   State.NO,   State.MUST });
		ALLOWED.put(AddressMode.NONE,            new State[] { State.NO,   State.NO,   State.NO });
	}
	
	
	public static enum Errors {
		REG_NO, BASE_REG, LABEL, EXPR
	}
	
	public void check(InstructionGenerator op, ErrorReporter<Errors> report) {
		if (regs.size() != op.getNumberOfRegs())
			report.error(String.format("instruction requires %d registers not %s", op.getNumberOfRegs(), regs.size()), Errors.REG_NO);
		System.out.println(op.getAddressMode().name());
		State[] allowed = ALLOWED.get(op.getAddressMode());
		if (!allowed[0].isOk(base))
			report.error(String.format("instruction %s base register", allowed[0].phrase), Errors.BASE_REG);
		if (!allowed[1].isOk(offset.getLabelOption()))
			report.error(String.format("instruction %s label", allowed[1].phrase), Errors.LABEL);
		if (!allowed[2].isOk(offset.getExprOption()))
			report.error(String.format("instruction %s constant expression", allowed[2].phrase), Errors.EXPR);
	}
	
}

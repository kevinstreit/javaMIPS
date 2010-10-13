package de.unisb.prog.mips.assembler.generators;

import java.util.ArrayList;
import java.util.List;

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

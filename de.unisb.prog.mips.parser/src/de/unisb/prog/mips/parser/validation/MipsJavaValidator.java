package de.unisb.prog.mips.parser.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.validation.Check;

import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Null;
import de.unisb.prog.mips.assembler.Offset;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.assembler.generators.InstructionGenerator;
import de.unisb.prog.mips.assembler.generators.OperandInstance;
import de.unisb.prog.mips.parser.mips.Addr;
import de.unisb.prog.mips.parser.mips.Insn;
import de.unisb.prog.mips.parser.mips.MipsPackage;
import de.unisb.prog.mips.util.Option;

public class MipsJavaValidator extends AbstractMipsJavaValidator {

	private final Option<LabelRef> DUMMY_LABEL_REF = new Option<LabelRef>(Null.getRef());
	private final Option<Expr>     DUMMY_EXPR      = new Option<Expr>(Expressions.ZERO);
	private final Option<Reg>      DUMMY_REG       = new Option<Reg>(Reg.zero);

	private final Generators generators = Generators.getInstance();

	private final ErrorReporter<Object> reporter = new ErrorReporter<Object>() {
		private int errs = 0;

		public void error(String fmt, Object... args) {
			MipsJavaValidator.this.error(String.format(fmt, args), MipsPackage.INSN);
			this.errs += 1;
		}

		public void error(Object arg, String fmt, Object... args) {
			error(fmt, args);
		}

		public void warning(String fmt, Object... args) {
			MipsJavaValidator.this.warning(String.format(fmt, args), MipsPackage.INSN);
		}

		public void warning(Object arg, String fmt, Object... args) {
			warning(fmt, args);
		}

		public int errorsReported() {
			return errs;
		}
	};

	@Check
	public void insn(Insn i) {
		// System.out.println(i.getOpcode());
		List<Reg> regs = new ArrayList<Reg>(i.getRegs().size());
		for (String s : i.getRegs()) {
			Reg r = checkReg(s, MipsPackage.INSN__REGS);
			regs.add(r != null ? r : Reg.zero);
		}

		Addr a = i.getAddr();

		Option<LabelRef> label = Option.empty(LabelRef.class);
		Option<Expr> expr = Option.empty(Expr.class);

		if (a != null) {
			if (a.getExpr() != null)
				expr = DUMMY_EXPR;
			if (a.getLabel() != null)
				label = DUMMY_LABEL_REF;
		}

		Option<Reg> base = Option.empty(Reg.class);
		if (i.getBase() != null && checkReg(i.getBase(), MipsPackage.INSN__BASE) != null)
			base = DUMMY_REG;

		OperandInstance op = new OperandInstance(regs, new Offset(label, expr), base);
		String opcode = i.getOpcode();
		if (this.generators.contains(opcode)) {
			List<InstructionGenerator> gens = this.generators.get(i.getOpcode());
			op.check(gens, this.reporter);
		}
		else
			error("illegal opcode: " + opcode, MipsPackage.INSN__OPCODE);
	}

	private Reg checkReg(String name, int feature) {
		try {
			return Reg.parse(name);
		}
		catch (IllegalArgumentException e) {
			error(e.getMessage(), feature);
		}
		return null;
	}


}

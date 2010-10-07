package de.unisb.prog.mips.parser.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.validation.Check;

import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.Expressions;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Offset;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.assembler.generators.InstructionGenerator;
import de.unisb.prog.mips.assembler.generators.OperandInstance;
import de.unisb.prog.mips.assembler.generators.OperandInstance.Errors;
import de.unisb.prog.mips.parser.mips.Addr;
import de.unisb.prog.mips.parser.mips.Insn;
import de.unisb.prog.mips.parser.mips.MipsPackage;
import de.unisb.prog.mips.util.Option;

public class MipsJavaValidator extends AbstractMipsJavaValidator {

	/*
		RForm:          opcode=ID rd=Reg rs=Reg rt=Reg;
		IArithForm:     opcode=ID rt=Reg rs=Reg imm=PNInt;
		IExpForm:       opcode=ID rt=Reg imm=PNInt '(' rs=Reg ')';
		ILabelForm:     opcode=ID reg=Reg label=[Label];
		IBr2Form:       opcode=ID rt=Reg rs=Reg label=[Label];
		JExpForm:       opcode=ID addr=INT;
		JLabelForm:     opcode=ID label=[Label];
	 */
	
	private static final Option<LabelRef> DUMMY_LABEL_REF = new Option<LabelRef>(LabelRef.NULL);
	private static final Option<Expr>     DUMMY_EXPR      = new Option<Expr>(Expressions.ZERO);
	private static final Option<Reg>      DUMMY_REG       = new Option<Reg>(Reg.zero);
	
	private final Generators generators = new Generators();
	
	private final ErrorReporter<OperandInstance.Errors> reporter = new ErrorReporter<OperandInstance.Errors>() {
		
		private int getFeature(Errors err) {
			switch (err) {
			case BASE_REG: return MipsPackage.INSN__BASE;
			case EXPR:     return MipsPackage.ADDR__EXPR;
			case LABEL:    return MipsPackage.ADDR__LABEL;
			case REG_NO:   return MipsPackage.INSN__REGS;
			}
			return MipsPackage.INSN;
		}

		@Override
		public void error(String msg, Errors arg) {
			MipsJavaValidator.this.error(msg, getFeature(arg));
		}

		@Override
		public void warning(String msg, Errors arg) {
			MipsJavaValidator.this.warning(msg, getFeature(arg));
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
		
		System.out.format("label: %s, expr: %s, base; %s\n", label.isSet(), expr.isSet(), base.isSet());
		
		OperandInstance op = new OperandInstance(regs, new Offset(label, expr), base);
		InstructionGenerator gen = generators.get(i.getOpcode());
		if (!gen.isLegal())
			error("illegal opcode: " + i.getOpcode(), MipsPackage.INSN__OPCODE);
		// System.out.println(gen.getAddressMode().name());
		op.check(gen, reporter);
		// System.out.println();
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

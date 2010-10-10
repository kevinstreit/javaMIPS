package de.unisb.prog.mips.assembler.generators;

public enum AddressMode {
	
	LABEL {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasLabel() && !inst.hasExpr() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "label";
		}
	},
	
	LABEL_EXPR {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasLabel() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "label | label+expression | expression";
		}
	},
	
	LABEL_EXPR_BASE {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasLabel() || inst.hasBase() || inst.hasExpr();
		}

		@Override
		public String stringRepr() {
			return "{" + LABEL_EXPR.stringRepr() + "}? (base_register)?";
		}
	},
	
	EXPR {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasExpr() && !inst.hasLabel() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "expression";
		}
	},
	
	EXPR_BASE {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasExpr() && !inst.hasLabel();
		}

		@Override
		public String stringRepr() {
			return "expression(base_register)?";
		}
	},
		
	SHAMT {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasExpr() && !inst.hasLabel() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "expression";
		}
	},
	
	NONE {
		@Override
		public boolean fits(OperandInstance inst) {
			return !inst.hasExpr() && !inst.hasLabel() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "";
		}
	};
	
	public abstract boolean fits(OperandInstance inst);
	public abstract String stringRepr();

}

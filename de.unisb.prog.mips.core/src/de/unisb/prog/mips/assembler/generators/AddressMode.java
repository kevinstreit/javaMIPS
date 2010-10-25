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
			return "address";
		}
	},

	LABEL_EXPR_BASE {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasLabel() || inst.hasBase() || inst.hasExpr();
		}

		@Override
		public String stringRepr() {
			return LABEL_EXPR.stringRepr() + "(base_reg)";
		}
	},

	EXPR {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasExpr() && !inst.hasLabel() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "immediate";
		}
	},

	EXPR_BASE {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasExpr() && !inst.hasLabel();
		}

		@Override
		public String stringRepr() {
			return "offset(base_reg)";
		}
	},

	SHAMT {
		@Override
		public boolean fits(OperandInstance inst) {
			return inst.hasExpr() && !inst.hasLabel() && !inst.hasBase();
		}

		@Override
		public String stringRepr() {
			return "shift_count";
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

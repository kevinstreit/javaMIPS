package de.unisb.prog.mips.assembler;

import java.io.IOException;

public class Expressions {
	
	public static enum IntOp {
		ADD('+') { public int op(int a, int b) { return a + b; } },
		SUB('-') { public int op(int a, int b) { return a - b; } },
		MUL('*') { public int op(int a, int b) { return a * b; } },
		DIV('/') { public int op(int a, int b) { return a / b; } };
		
		public final char sym;
		
		private IntOp(char sym) {
			this.sym = sym;
		}
		
		public abstract int op(int a, int b);
	}
	
	private static class IntBinOp implements Expr<Integer> {
		private final IntOp op;
		private final Expr<Integer> left, right;
		
		IntBinOp(IntOp op, Expr<Integer> left, Expr<Integer> right) {
			this.op = op;
			this.left = left;
			this.right = right;
		}

		@Override
		public Integer eval() {
			return op.op(left.eval(), right.eval());
		}

		@Override
		public void append(Appendable app) throws IOException {
			app.append('(');
			left.append(app);
			app.append(op.sym);
			right.append(app);
		}
	}
	
	public static Expr<Integer> binary(IntOp op, Expr<Integer> left, Expr<Integer> right) {
		return new IntBinOp(op, left, right);
	}
	
	public static Expr<Integer> constantInt(final int val) {
		return new Expr<Integer>() {
			@Override
			public Integer eval() {
				return val;
			}

			@Override
			public void append(Appendable app) throws IOException {
				app.append(String.format("%d", val));
			}
		};
	}
	

}

package de.unisb.prog.mips.assembler;

public interface ErrorReporter<T> {

	public void error(String fmt, Object... args);
	public void error(T arg, String fmt, Object... args);
	public void warning(String fmt, Object... args);
	public void warning(T arg, String fmt, Object... args);
	public int errorsReported();

	public static final ErrorReporter<Position> POSITION_STD_REPORTER = new ErrorReporter<Position>() {
		private int errs = 0;

		public void error(Position arg, String fmt, Object... args) {
			String msg = String.format(fmt, args);
			System.out.format("error %s(%d): %s\n", arg.getFilename(), arg.getLineNumber(), msg);
			errs++;
		}

		public void error(String msg, Object... args) {
			System.out.format("error: " + msg + "\n", args);
			errs++;
		}

		public void warning(Position arg, String fmt, Object... args) {
			String msg = String.format(fmt, args);
			System.out.format("warning %s(%d): %s\n", arg.getFilename(), arg.getLineNumber(), msg);
			errs++;
		}

		public void warning(String msg, Object... args) {
			System.out.format("warning: " + msg + "\n", args);
			errs++;
		}

		public int errorsReported() {
			return errs;
		}
	};

}

package de.unisb.prog.mips.assembler;

public interface Position {

	String getFilename();
	int getLineNumber();
	int getCharStart();
	int getCharEnd();

	public static final Position ILLEGAL = new Position() {

		@Override
		public int getLineNumber() {
			return -1;
		}

		@Override
		public String getFilename() {
			return "<no such file>";
		}

		@Override
		public int getCharStart() {
			return -1;
		}

		@Override
		public int getCharEnd() {
			return -1;
		}
	};

}

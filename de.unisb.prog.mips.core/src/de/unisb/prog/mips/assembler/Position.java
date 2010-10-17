package de.unisb.prog.mips.assembler;

public interface Position {

	String getFilename();
	int getLineNumber();
	int getCharStart();
	int getCharEnd();

	public static final Position ILLEGAL = new Position() {
		public int getLineNumber() {
			return -1;
		}

		public String getFilename() {
			return "<no such file>";
		}

		public int getCharStart() {
			return -1;
		}

		public int getCharEnd() {
			return -1;
		}
	};

}

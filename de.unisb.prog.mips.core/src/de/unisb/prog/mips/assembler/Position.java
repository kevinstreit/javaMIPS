package de.unisb.prog.mips.assembler;

import java.net.URI;

public interface Position {

	URI getURI();
	int getLineNumber();
	int getCharStart();
	int getCharEnd();

	public static final Position ILLEGAL = new Position() {
		public int getLineNumber() {
			return -1;
		}

		public URI getURI() {
			return null;
		}

		public int getCharStart() {
			return -1;
		}

		public int getCharEnd() {
			return -1;
		}
	};

}

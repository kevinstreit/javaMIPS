package de.unisb.prog.mips.assembler;

public interface Position {
	
	String getFilename();
	int getLineNumber();
	
	public static final Position ILLEGAL = new Position() {
		
		@Override
		public int getLineNumber() {
			return 0;
		}
		
		@Override
		public String getFilename() {
			return "<no such file>";
		}
	};

}

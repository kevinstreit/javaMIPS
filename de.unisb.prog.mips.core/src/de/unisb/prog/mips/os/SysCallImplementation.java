package de.unisb.prog.mips.os;

public interface SysCallImplementation {
	
	void print(int v);
	void print(char ch);
	void print(CharSequence s);
	
	void exit(int ret);
	
	public static SysCallImplementation DEFAULT = new SysCallImplementation() {
		
		@Override
		public void print(CharSequence s) {
			System.out.print(s);
		}
		
		@Override
		public void print(char ch) {
			System.out.print(ch);
		}
		
		@Override
		public void print(int v) {
			System.out.print(v);
		}

		@Override
		public void exit(int ret) {
			// Nothing to do
		}
	};

}

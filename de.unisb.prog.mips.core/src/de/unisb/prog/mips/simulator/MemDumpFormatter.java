package de.unisb.prog.mips.simulator;

import java.io.IOException;

import de.unisb.prog.mips.insn.Disassembler;

public interface MemDumpFormatter<T> {
	
	Type granularity();
	int chunkSize();
	void emit(T output, int addr, int[] data) throws IOException;
	
	public static final MemDumpFormatter<Appendable> DISASM = new MemDumpFormatter<Appendable>() {
		@Override public Type granularity() { return Type.WORD; }
		@Override public int chunkSize() { return 1; }
		@Override public void emit(Appendable output, int addr, int[] data) throws IOException {
			output.append(String.format("%08x: %08x ", addr, data[0]));
			output.append(Disassembler.INSTANCE.disasm(data[0]));
			output.append('\n');
		}
	};
	
	public static final MemDumpFormatter<Appendable> DATA = new MemDumpFormatter<Appendable>() {

		@Override public Type granularity() { return Type.BYTE; }
		@Override public int chunkSize() { return 16; }

		@Override
		public void emit(Appendable output, int addr, int[] data) throws IOException {
			output.append(String.format("%08x: ", addr));
			for (int i = 0; i < data.length; i++)
				output.append(String.format("%02x ", data[i]));
			for (int i = 0; i < data.length; i++) {
				char ch = (char) data[i];
				output.append(String.format("%c", Character.isLetterOrDigit(ch) ? ch : '.'));
			}
			output.append('\n');
		}
	};

}

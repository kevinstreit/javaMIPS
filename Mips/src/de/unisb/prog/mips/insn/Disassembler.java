package de.unisb.prog.mips.insn;

import java.io.IOException;


public class Disassembler implements Handler {
	
	private final Appendable app;
	
	public Disassembler(Appendable app) {
		this.app = app;
	}

	@Override
	public void f(int fmt, int ft, int fs, int fd, int funct) {
	}

	@Override
	public void i(int op, int rs, int rt, int imm) {
		try {
			app.append(String.format("%5s $%2d, $%2d, %5d", Opcode.values()[op].toString(), rs, rt, imm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void j(int op, int imm) {
		try {
			app.append(String.format("%5s %8x", Opcode.values()[op].toString(), imm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void r(int rs, int rt, int rd, int shamt, int funct) {
		try {
			app.append(String.format("%5s $%2d, $%2d, $%2d", IntFunct.values()[funct].toString(), rs, rt, rd));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

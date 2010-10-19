package de.unisb.prog.mips.os;

public enum SysCallID {
	/* 00 */ _00,
	/* 01 */ print_int,
	/* 02 */ print_float,
	/* 03 */ print_double,
	/* 04 */ print_string,
	/* 05 */ read_int,
	/* 06 */ read_float,
	/* 07 */ read_double,
	/* 08 */ read_string,
	/* 09 */ sbrk,
	/* 10 */ exit,
	/* 11 */ print_char,
	/* 12 */ read_char,
	/* 13 */ open,
	/* 14 */ read,
	/* 15 */ write,
	/* 16 */ close,
	/* 17 */ exit2;

	public boolean valid() {
		return name().charAt(0) != '_';
	}

}

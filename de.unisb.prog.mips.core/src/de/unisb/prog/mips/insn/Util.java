package de.unisb.prog.mips.insn;

public class Util {
	
	public static void intToBytes(int word, byte[] arr) {
		for (int i = 0; i < 4; i++) {
			arr[i] = (byte) word;
			word >>>= 8;
		}
	}
	
	public static byte[] intToBytes(int word) {
		byte[] res = new byte[4];
		intToBytes(word, res);
		return res;
	}
	
	public static int bytesToInt(byte[] arr) {
		int res = 0;
		for (int i = 3; i >= 0; i--) {
			res |= arr[i] & 0xff;
			res <<= 8;
		}
		return res;
	}

}

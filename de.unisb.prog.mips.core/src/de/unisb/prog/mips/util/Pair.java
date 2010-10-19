package de.unisb.prog.mips.util;

public class Pair<U, V> {

	private final U fst;
	private final V snd;

	public Pair(U fst, V snd) {
		super();
		this.fst = fst;
		this.snd = snd;
	}

	public U fst() {
		return fst;
	}

	public V snd() {
		return snd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fst == null) ? 0 : fst.hashCode());
		result = prime * result + ((snd == null) ? 0 : snd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;
		return fst.equals(other.fst) && snd.equals(other.snd);
	}




}

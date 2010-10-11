package de.unisb.prog.mips.util;

public class Option<T> {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static final Option EMPTY = new Option(null);
	
	@SuppressWarnings("unchecked")
	public static final <T> Option<T> empty(Class<T> cls) {
		return EMPTY;
	}
	
	private final T elm;
	
	public Option(T elm) {
		this.elm = elm;
	}
	
	public final T otherwise(T opt) {
		return elm != null ? elm : opt;
	}
	
	public final boolean isSet() {
		return elm != null;
	}
	
	public final <S> S ifthenelse(S t, S f) {
		return elm != null ? t : f;
	}

}

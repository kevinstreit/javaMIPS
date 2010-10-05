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
	
	public T otherwise(T opt) {
		return elm != null ? elm : opt;
	}
	
	public boolean isSet() {
		return elm != null;
	}

}

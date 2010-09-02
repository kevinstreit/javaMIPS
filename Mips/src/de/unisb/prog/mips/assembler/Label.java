package de.unisb.prog.mips.assembler;

public class Label {
	
	private final String name;
	
	public Label(String name) {
		this.name = name;
	}
	
	public final boolean equals(Object o) {
		if (!(o instanceof Label))
			return false;
		return name.equals(((Label) o).name);
	}
	
	public final int hashCode() {
		return name.hashCode();
	}
	
}

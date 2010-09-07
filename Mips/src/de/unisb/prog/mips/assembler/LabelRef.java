package de.unisb.prog.mips.assembler;

public class LabelRef implements Expr<Integer> {
	private Label label;
	
	LabelRef(Label l) {
	}
	
	LabelRef() {
		this(null);
	}

	public void patchTo(Label label) {
		this.label = label;
	}

	public boolean isLabelAvailable() {
		return label != null;
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public Integer eval() {
		return label.getOffset();
	}

}

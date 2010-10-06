package de.unisb.prog.mips.assembler.segments;

import java.util.List;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.simulator.Type;

public class Data extends Segment {

	private static final long serialVersionUID = 3256826055186816334L;

	public Data(Assembly asm) {
		super(asm);
	}

	public Element values(List<Expr> vals, Type tp) {
		Element res = new Values(this, vals, tp);
		add(res);
		return res;
	}
	
	public Element string(String str, boolean zeroTerminate) {
		Element res = new Str(this, str, zeroTerminate);
		add(res);
		return res;
	}
	
	@Override
	protected void relocate(int startAddress) {
	}

	@Override
	public Kind getKind() {
		return Kind.DATA;
	}

}

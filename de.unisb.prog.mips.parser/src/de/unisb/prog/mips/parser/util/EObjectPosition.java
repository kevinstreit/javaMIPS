package de.unisb.prog.mips.parser.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.NodeUtil;

import de.unisb.prog.mips.assembler.Position;

public class EObjectPosition implements Position {
	
	private final EObject obj;

	public EObjectPosition(EObject obj) {
		super();
		this.obj = obj;
	}

	@Override
	public String getFilename() {
		// TODO: Implement properly
		return "TODO";
	}

	@Override
	public int getLineNumber() {
		return NodeUtil.getNodeAdapter(obj).getParserNode().getLine();
	}

}

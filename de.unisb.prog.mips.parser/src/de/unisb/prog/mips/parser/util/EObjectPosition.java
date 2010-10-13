package de.unisb.prog.mips.parser.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.CompositeNode;
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
		String uri = this.obj.eResource().getURI().toString();
		String worspaceRootRelativePath = uri.startsWith("platform:/resource") ? uri.substring("platform:/resource".length()) : null;
		return worspaceRootRelativePath;
	}

	@Override
	public int getLineNumber() {
		return NodeUtil.getNodeAdapter(this.obj).getParserNode().getLine();
	}

	@Override
	public int getCharStart() {
		return NodeUtil.getNodeAdapter(this.obj).getParserNode().getOffset();
	}

	@Override
	public int getCharEnd() {
		CompositeNode node = NodeUtil.getNodeAdapter(this.obj).getParserNode();
		return node.getOffset() + node.getLength();
		// return node.getTotalOffset() + node.getTotalLength();
	}

}

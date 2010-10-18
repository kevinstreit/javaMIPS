package de.unisb.prog.mips.parser.util;

import org.eclipse.core.resources.ResourcesPlugin;
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

	public String getFilename() {
		if (obj.eResource() == null)
			return null;

		String uri = obj.eResource().getURI().toString();
		String workspaceRootRelativePath = null;

		if (uri.startsWith("platform:/resource")) {
			workspaceRootRelativePath = uri.substring("platform:/resource".length());
		} else if (uri.startsWith("file:")) {
			String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toString();
			workspaceRootRelativePath = uri.substring(workspacePath.length() + 5); // plus 5 for the "file:"
		}

		return workspaceRootRelativePath;
	}

	public int getLineNumber() {
		return NodeUtil.getNodeAdapter(obj).getParserNode().getLine();
	}

	public int getCharStart() {
		return NodeUtil.getNodeAdapter(obj).getParserNode().getOffset();
	}

	public int getCharEnd() {
		CompositeNode node = NodeUtil.getNodeAdapter(obj).getParserNode();
		return node.getOffset() + node.getLength();
	}

}

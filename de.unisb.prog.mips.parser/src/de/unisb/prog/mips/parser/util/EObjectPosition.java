package de.unisb.prog.mips.parser.util;

import java.io.File;
import java.net.URI;

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

		String workspaceRootRelativePath = null;

		if (obj.eResource().getURI().isFile()) {
			URI uri = new File(obj.eResource().getURI().toFileString()).toURI();
			URI workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocationURI();
			workspaceRootRelativePath = "/" + workspacePath.relativize(uri).toString();
		} else if (obj.eResource().getURI().isPlatformResource()) {
			workspaceRootRelativePath = obj.eResource().getURI().toPlatformString(true);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		String filename = getFilename();
		int result = filename == null ? 1 : filename.hashCode();
		result = prime * result + getCharStart();
		result = prime * result + getCharEnd();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position p = (Position) obj;
		String filename = getFilename();
		boolean sameFiles = filename == null ? false : filename.equals(p.getFilename());
		return sameFiles && getCharStart() == p.getCharStart() && getCharEnd() == p.getCharEnd();
	}



}

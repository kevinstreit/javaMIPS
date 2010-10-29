package de.unisb.prog.mips.parser.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

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

	public URI getURI() {
		if (obj.eResource() == null)
			return null;

		URI uri = null;

		if (obj.eResource().getURI().isFile()) {
			uri = new File(obj.eResource().getURI().toFileString()).toURI();
		} else if (obj.eResource().getURI().isPlatformResource()) {
			try {
				uri = new URI(ResourcesPlugin.getWorkspace().getRoot().getLocationURI().toString() + obj.eResource().getURI().toPlatformString(false));
			} catch (URISyntaxException e) {
				uri = null;
			}
		}

		return uri;
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
		URI uri = getURI();
		int result = uri == null ? 1 : uri.hashCode();
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
		URI uri = getURI();
		boolean sameFiles = uri == null ? false : uri.equals(p.getURI());
		return sameFiles && getCharStart() == p.getCharStart() && getCharEnd() == p.getCharEnd();
	}



}

package de.unisb.prog.mips.parser.ui.launching;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.editor.XtextEditor;

public class RunnableMIPSPropTester extends org.eclipse.core.expressions.PropertyTester {

	public RunnableMIPSPropTester() {

	}

	private static boolean hasMIPSFiles(IProject proj) {
		try {
			final AtomicBoolean hasMIPS = new AtomicBoolean(false);
			proj.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile && ((IFile) resource).getFileExtension().equals("mips")) {
						hasMIPS.set(true);
						return false;
					}

					return true;
				}
			}, IResource.DEPTH_INFINITE, 0);
			return hasMIPS.get();
		} catch (CoreException e) {
			return false;
		}
	}

	public static boolean isMIPSRunnable(IProject proj) {
		if (proj != null && proj.isOpen()) {
			try {
				return proj.hasNature(XtextProjectHelper.NATURE_ID) && hasMIPSFiles(proj);
			} catch (CoreException e) {
				return false;
			}
		}

		return false;
	}

	public static boolean isMIPSRunnable(IFile file) {
		IProject proj = file.getProject();

		if (proj != null)
			return isMIPSRunnable(proj);

		return false;
	}

	public static boolean isMIPSRunnable(IEditorInput inp) {
		if (inp instanceof FileEditorInput) {
			IFile f = ((FileEditorInput) inp).getFile();
			return f != null && isMIPSRunnable(f);
		}

		return false;
	}

	public static boolean isMIPSRunnable(IAdaptable obj) {
		if (obj instanceof IEditorPart) {
			IEditorPart editor = (IEditorPart) obj;
			return editor instanceof XtextEditor && isMIPSRunnable(editor.getEditorInput());
		}

		if (obj instanceof IEditorInput) {
			return isMIPSRunnable((IEditorInput) obj);
		}

		if (obj instanceof IFile) {
			return isMIPSRunnable((IFile) obj);
		}

		if (obj instanceof IProject) {
			return isMIPSRunnable((IProject) obj);
		}

		return false;
	}

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		return receiver instanceof IAdaptable && isMIPSRunnable((IAdaptable) receiver);
	}

}

package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;

public abstract class EditorOpenListener implements IWindowListener, IPageListener, IPartListener2 {
	
	public abstract void editorActivated(IEditorPart editor);
	public abstract void editorDeactivated(IEditorPart editor);
	public abstract void editorOpened(IEditorPart editor);
	public abstract void editorClosed(IEditorPart editor);

	@Override
	public void pageActivated(IWorkbenchPage page) {
		// Nothing
	}

	@Override
	public void pageClosed(IWorkbenchPage page) {
		page.removePartListener(this);
	}

	@Override
	public void pageOpened(IWorkbenchPage page) {
		page.addPartListener(this);
		if (page.getActivePartReference() != null) {
			partOpened(page.getActivePartReference());
			partActivated(page.getActivePartReference());
		}
	}

	@Override
	public void windowActivated(IWorkbenchWindow window) {
		// Nothing
	}

	@Override
	public void windowDeactivated(IWorkbenchWindow window) {
		// Nothing
	}

	@Override
	public void windowClosed(IWorkbenchWindow window) {
		window.removePageListener(this);	
	}

	@Override
	public void windowOpened(IWorkbenchWindow window) {
		window.addPageListener(this);
		if (window.getActivePage() != null) {
			pageOpened(window.getActivePage());
			pageActivated(window.getActivePage());
		}
	}
	
	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		if (partRef.getPart(false) instanceof IEditorPart)
			editorActivated((IEditorPart) partRef.getPart(false));
	}
	
	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// Nothing
	}
	
	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		if (partRef.getPart(false) instanceof IEditorPart)
			editorClosed((IEditorPart) partRef.getPart(false));
	}
	
	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		if (partRef.getPart(false) instanceof IEditorPart)
			editorDeactivated((IEditorPart) partRef.getPart(false));
	}
	
	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		if (partRef.getPart(false) instanceof IEditorPart)
			editorOpened((IEditorPart) partRef.getPart(false));
	}
	
	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// Nothing
	}
	
	@Override
	public void partVisible(IWorkbenchPartReference partRef) {
		// Nothing
	}
	
	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// Nothing
	}

}

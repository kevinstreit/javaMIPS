package de.unisb.prog.mips.parser.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.parser.ui.launching.CurrentIPMarker;

public class MarkerUtil {
	private static IMarker createMarkerOnResource(Position pos, String markerID) {
		// Create debugging marker
		String pathStr = pos.getFilename();

		if (pathStr != null) {
			Path resPath = new Path(pathStr);
			if (resPath != null) {
				IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(resPath);
				if (res != null) {
					try {
						IMarker m = res.createMarker(CurrentIPMarker.ID);
						return m;
					} catch (CoreException e) {
						e.printStackTrace();
						// We can't do anything
					}
				}
			}
		}

		return null;
	}

	public static IMarker markPosition(Position pos, String markerID, boolean openEditor, boolean activateEditor) {
		IMarker m = createMarkerOnResource(pos, markerID);

		if (m != null) {
			IResource res = m.getResource();

			try {
				m.setAttribute(IMarker.CHAR_START, pos.getCharStart());
				m.setAttribute(IMarker.CHAR_END, pos.getCharEnd());
				m.setAttribute(IMarker.LINE_NUMBER, pos.getLineNumber());

				if (openEditor) {
					IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
					IWorkbenchPage page = window != null ? window.getActivePage() : null;

					if (res instanceof IFile) {
						if (page != null)
							IDE.openEditor(page, (IFile) res, activateEditor);
					} else {
						if (page != null)
							IDE.openEditor(page, m, activateEditor);
					}
				}
			} catch (CoreException e) {
				try {
					m.delete();
				} catch (CoreException e1) {
					// No need to do something
				}

				return null;
			}
		}

		return m;
	}
}

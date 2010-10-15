package de.unisb.prog.mips.parser.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.unisb.prog.mips.assembler.Position;

public class MarkerUtil {
	public static final String ID_CurrentIP = "de.unisb.prog.mips.currentIPMarker";
	public static final String ID_Highlighting = "de.unisb.prog.mips.highlightMarker";

	public static final String ANN_ID_CurrentIP = "de.unisb.prog.mips.currentIPAnnotation";
	public static final String ANN_ID_Highlighting = "de.unisb.prog.mips.highlightAnnotation";

	public static void cleanAllMarkers(String markerID) {
		try {
			ResourcesPlugin.getWorkspace().getRoot().deleteMarkers(markerID, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			// Nothing we can do
		}
	}

	private static IMarker createMarkerOnResource(Position pos, String markerID) {
		// Create debugging marker
		String pathStr = pos.getFilename();

		if (pathStr != null) {
			Path resPath = new Path(pathStr);
			if (resPath != null) {
				IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(resPath);
				if (res != null) {
					try {
						IMarker m = res.createMarker(markerID);
						return m;
					} catch (CoreException e) {
						// We can't do anything
					}
				}
			}
		}

		return null;
	}

	public static IMarker markPosition(final Position pos, final String markerID, final boolean openEditor, final boolean activateEditor) {
		final IMarker[] m = new IMarker[1];

		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					m[0] = createMarkerOnResource(pos, markerID);

					if (m[0] != null) {
						IResource res = m[0].getResource();

						try {
							m[0].setAttribute(IMarker.CHAR_START, pos.getCharStart());
							m[0].setAttribute(IMarker.CHAR_END, pos.getCharEnd());
							m[0].setAttribute(IMarker.LINE_NUMBER, pos.getLineNumber());

							if (openEditor) {
								IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
								IWorkbenchPage page = window != null ? window.getActivePage() : null;

								if (res instanceof IFile) {
									if (page != null)
										IDE.openEditor(page, (IFile) res, activateEditor);
								} else {
									if (page != null)
										IDE.openEditor(page, m[0], activateEditor);
								}
							}
						} catch (CoreException e) {
							try {
								m[0].delete();
							} catch (CoreException e1) {
								// No need to do something
							}

							m[0] = null;
						}
					}
				}
			}, null);
		} catch (CoreException e2) {
			return null;
		}

		return m[0];
	}
}

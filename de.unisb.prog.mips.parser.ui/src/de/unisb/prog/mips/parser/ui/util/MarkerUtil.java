package de.unisb.prog.mips.parser.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.unisb.prog.mips.assembler.Position;

public class MarkerUtil {
	public static final String ID_CurrentIP = "de.unisb.prog.mips.currentIPMarker";
	public static final String ID_Highlighting = "de.unisb.prog.mips.highlightMarker";
	public static final String ID_Breakpoint = "de.unisb.prog.mips.breakMarker";

	public static final String ANN_ID_CurrentIP = "de.unisb.prog.mips.currentIPAnnotation";
	public static final String ANN_ID_Highlighting = "de.unisb.prog.mips.highlightAnnotation";

	public static void cleanAllMarkers(String markerID) {
		try {
			ResourcesPlugin.getWorkspace().getRoot().deleteMarkers(markerID, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			// Nothing we can do
		}
	}

	private static IResource findResource(Position pos) {
		if (pos.getURI() != null) {
			IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(pos.getURI());
			return files.length > 0 ? files[0] : null;
		}

		return null;
	}

	private static IMarker createMarkerOnResource(IResource res, String markerID) {
		if (res != null) {
			try {
				IMarker m = res.createMarker(markerID);
				return m;
			} catch (CoreException e) {
				// We can't do anything
			}
		}
		return null;
	}

	public static IMarker markProject(final IProject project, final String markerID) {
		final IMarker[] m = new IMarker[1];

		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					m[0] = createMarkerOnResource(project, markerID);
				}
			}, null);
		} catch (Exception e) {

		}

		return m[0];
	}

	public static IMarker markPosition(final Position pos, final String markerID, final boolean openEditor, final boolean activateEditor) {
		final IMarker[] m = new IMarker[1];

		try {
			ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					final IResource res = findResource(pos);
					m[0] = createMarkerOnResource(res, markerID);

					if (m[0] != null) {
						try {
							m[0].setAttribute(IMarker.CHAR_START, pos.getCharStart());
							m[0].setAttribute(IMarker.CHAR_END, pos.getCharEnd());
							m[0].setAttribute(IMarker.LINE_NUMBER, pos.getLineNumber());

							if (openEditor) {
								PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
									public void run() {
										IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
										IWorkbenchPage page = window != null ? window.getActivePage() : null;

										try {
											if (res instanceof IFile) {
												if (page != null)
													// TODO: Try to  scroll to the exact position of the marker without selecting the content
													IDE.openEditor(page, (IFile) res, activateEditor);
											} else {
												if (page != null)
													IDE.openEditor(page, m[0], activateEditor);
											}
										} catch (PartInitException e) {
											// Nothing to do
										}
									}
								});
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

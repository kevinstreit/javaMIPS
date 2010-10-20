package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.LineBreakpoint;

import de.unisb.prog.mips.parser.ui.util.MarkerUtil;

public class MIPSBreakpoint extends LineBreakpoint {
	public static final String DEBUG_MODEL_ID = "de.unisb.prog.debugModelID";

	public MIPSBreakpoint(final IResource res, final int line, final int charStart, final int charEnd) throws DebugException {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				IMarker marker = res.createMarker(MarkerUtil.ID_Breakpoint);
				setMarker(marker);
				marker.setAttribute(IBreakpoint.ENABLED, Boolean.TRUE);
				marker.setAttribute(IMarker.LINE_NUMBER, line);
				if (charStart >= 0 && charEnd >= charStart) {
					marker.setAttribute(IMarker.CHAR_START, charStart);
					marker.setAttribute(IMarker.CHAR_END, charEnd);
				}
				marker.setAttribute(IBreakpoint.ID, getModelIdentifier());
				marker.setAttribute(IMarker.MESSAGE, "Line Breakpoint: " + res.getName() + " [line: " + line + "]");
			}
		};
		run(getMarkerRule(res), runnable);
	}

	public String getModelIdentifier() {
		return DEBUG_MODEL_ID;
	}
}

package de.unisb.prog.mips.parser.ui.util;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.parser.ui.MIPSCore;

public class UIErrorReporter implements ErrorReporter<Position> {

	private final boolean markIfPossible;
	private int errorsCounted;

	public UIErrorReporter(boolean markIfPossible) {
		this.markIfPossible = markIfPossible;
	}

	public void error(Position pos, String msg, Object... args) {
		++errorsCounted;

		if (markIfPossible && pos != null) {
			IMarker m = MarkerUtil.markPosition(pos, IMarker.PROBLEM, true, false);
			if (m != null) {
				try {
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					m.setAttribute(IMarker.MESSAGE, String.format(msg, args));
				} catch (CoreException e) {
					// Nothing
				}
			}
		} else {
			error(msg);
		}
	}

	public void warning(Position pos, String msg, Object... args) {
		if (markIfPossible && pos != null) {
			IMarker m = MarkerUtil.markPosition(pos, IMarker.PROBLEM, true, false);
			if (m != null) {
				try {
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
					m.setAttribute(IMarker.MESSAGE, String.format(msg, args));
				} catch (CoreException e) {
					// Nothing
				}
			}
		} else {
			warning(msg);
		}
	}

	public int errorsReported() {
		return errorsCounted;
	}

	public void error(String fmt, Object... args) {
		errorsCounted++;
		IProject proj = MIPSCore.getInstance().getLodedProject();
		if (markIfPossible && proj != null) {
			IMarker m = MarkerUtil.markProject(proj, IMarker.PROBLEM);
			if (m != null) {
				try {
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					m.setAttribute(IMarker.MESSAGE, String.format(fmt, args));
				} catch (CoreException e) {
					// Nothing
				}
			}
		} else {
			Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", String.format("Error: " + fmt, args));
			StatusManager.getManager().handle(stat, StatusManager.LOG);
		}
	}

	public void warning(String fmt, Object... args) {
		IProject proj = MIPSCore.getInstance().getLodedProject();
		if (markIfPossible && proj != null) {
			IMarker m = MarkerUtil.markProject(proj, IMarker.PROBLEM);
			if (m != null) {
				try {
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
					m.setAttribute(IMarker.MESSAGE, String.format(fmt, args));
				} catch (CoreException e) {
					// Nothing
				}
			}
		} else {
			Status stat = new Status(Status.WARNING, "de.unisb.prog.mips.parser.ui", String.format("Warning: " + fmt, args));
			StatusManager.getManager().handle(stat, StatusManager.LOG);
		}
	}

}

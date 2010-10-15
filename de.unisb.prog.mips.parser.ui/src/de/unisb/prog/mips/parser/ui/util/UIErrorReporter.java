package de.unisb.prog.mips.parser.ui.util;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;

public class UIErrorReporter implements ErrorReporter<Position> {

	private final boolean markIfPossible;
	private int errorsCounted;

	public UIErrorReporter(boolean markIfPossible) {
		this.markIfPossible = markIfPossible;
	}

	public void error(String msg, Position arg) {
		++errorsCounted;

		if (markIfPossible && arg != null) {
			IMarker m = MarkerUtil.markPosition(arg, IMarker.PROBLEM, true, false);
			if (m != null) {
				try {
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					m.setAttribute(IMarker.MESSAGE, msg);
				} catch (CoreException e) {
					// Nothing
				}
			}
		} else {
			Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", String.format("[ MIPS:ERROR ] %s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg));
			StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
		}
	}

	public void warning(String msg, Position arg) {
		if (markIfPossible && arg != null) {
			IMarker m = MarkerUtil.markPosition(arg, IMarker.PROBLEM, true, false);
			if (m != null) {
				try {
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
					m.setAttribute(IMarker.MESSAGE, msg);
				} catch (CoreException e) {
					// Nothing
				}
			}
		} else {
			Status stat = new Status(Status.WARNING, "de.unisb.prog.mips.parser.ui", String.format("[ MIPS:WARNING ] %s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg));
			StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
		}
	}

	public int errorsReported() {
		return errorsCounted;
	}

}

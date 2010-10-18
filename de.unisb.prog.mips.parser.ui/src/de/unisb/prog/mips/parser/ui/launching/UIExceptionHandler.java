package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.util.MarkerUtil;
import de.unisb.prog.mips.simulator.ExceptionHandler;
import de.unisb.prog.mips.simulator.Memory;
import de.unisb.prog.mips.simulator.ProcessorState;

public class UIExceptionHandler implements ExceptionHandler {

	public void breakpoint(ProcessorState state, Memory mem) {
		// Nothing to do currently
	}

	public void illegalInstruction(ProcessorState state, Memory mem, int addr) {
		reportError(state, mem, addr, "Illegal Instruction");
	}

	public void unalignedMemory(ProcessorState state, Memory mem, int addr) {
		reportError(state, mem, addr, "Unaligned Memory");
	}

	private void reportError(ProcessorState state, Memory mem, int addr, String message, Object... args) {
		Assembly asm = MIPSCore.getInstance().getAsm();

		if (asm != null) {
			Element elm = asm.getElementAt(addr);
			if (elm != null && elm.getPosition() != null) {
				try {
					IMarker m = MarkerUtil.markPosition(elm.getPosition(), IMarker.PROBLEM, true, true);
					m.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					m.setAttribute(IMarker.MESSAGE, String.format(message, args));
					return;
				} catch (CoreException e) {
					// Should not happen
				}
			}
		}

		Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", String.format(message));
		StatusManager.getManager().handle(stat, StatusManager.LOG);
	}

}

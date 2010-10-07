package de.unisb.prog.mips.parser.ui.launching;

import de.unisb.prog.mips.simulator.Sys;

public interface ExecutionListener {
	public void execStarted(Sys sys);
	public void execPaused(Sys sys);
	public void execStepped(Sys sys);
	public void execFinished(Sys sys);
	
	public void dbgBrkptReached(Sys sys);
}

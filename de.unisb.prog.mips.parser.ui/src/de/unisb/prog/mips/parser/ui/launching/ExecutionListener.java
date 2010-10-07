package de.unisb.prog.mips.parser.ui.launching;

import de.unisb.prog.mips.simulator.Processor;

public interface ExecutionListener {
	public void execStarted(Processor proc);
	public void execPaused(Processor proc);
	public void execStepped(Processor proc);
	public void execFinished(Processor proc);
	
	public void dbgBrkptReached(Processor proc);
}

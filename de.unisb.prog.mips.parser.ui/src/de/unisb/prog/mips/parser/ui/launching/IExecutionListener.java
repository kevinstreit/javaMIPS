package de.unisb.prog.mips.parser.ui.launching;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.simulator.Sys;

public interface IExecutionListener {
	public void execStarted(Sys sys, Assembly asm);
	public void execPaused(Sys sys, Assembly asm);
	public void execContinued(Sys sys, Assembly asm);
	public void execStepped(Sys sys, Assembly asm);
	public void execFinished(Sys sys, Assembly asm, boolean interrupted);

	public void inputModeStarted();
	public void inputModeDone();

	public void dbgBrkptReached(Sys sys, Assembly asm);
}

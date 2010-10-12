package de.unisb.prog.mips.parser.ui.launching;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.simulator.Sys;

public interface IAssemblyLoadListener {
	public void assemblyLoaded(Assembly asm, Sys sys);
	public void assemblyReset();
}

package de.unisb.prog.mips.parser.ui.launching;

import java.util.HashSet;

import de.unisb.prog.mips.simulator.Processor;

public class MIPSCore implements ExecutionListener {
	
	// Singleton management ====================
	
	private static MIPSCore instance = null;
	
	private MIPSCore() {
		// Nothing to do
	}
	
	public static MIPSCore getInstance() {
		if (instance == null)
			instance = new MIPSCore();
		
		return instance;
	}
	
	// Listener Management =====================
	
	private HashSet<ExecutionListener> listener = new HashSet<ExecutionListener>();
	
	public void addExecutionListener(ExecutionListener l) {
		listener.add(l);
	}
	
	public void removeExecutionListener(ExecutionListener l) {
		listener.remove(l);
	}
	
	@Override
	public void execStarted(Processor proc) {
		for (ExecutionListener l : listener)
			l.execStarted(proc);
	}

	@Override
	public void execPaused(Processor proc) {
		for (ExecutionListener l : listener)
			l.execPaused(proc);
	}

	@Override
	public void execStepped(Processor proc) {
		for (ExecutionListener l : listener)
			l.execStepped(proc);
	}

	@Override
	public void execFinished(Processor proc) {
		for (ExecutionListener l : listener)
			l.execFinished(proc);
	}

	@Override
	public void dbgBrkptReached(Processor proc) {
		for (ExecutionListener l : listener) {
			l.dbgBrkptReached(proc);
			l.execPaused(proc);
		}
	}
	
	// Execution Registry ======================
	
	private Processor proc = null;
	
	
}

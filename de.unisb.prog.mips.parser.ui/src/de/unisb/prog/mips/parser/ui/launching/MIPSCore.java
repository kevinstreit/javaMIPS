package de.unisb.prog.mips.parser.ui.launching;

import java.util.HashSet;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;

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
	public void execStarted(Sys sys) {
		for (ExecutionListener l : listener)
			l.execStarted(sys);
	}

	@Override
	public void execPaused(Sys sys) {
		for (ExecutionListener l : listener)
			l.execPaused(sys);
	}

	@Override
	public void execStepped(Sys sys) {
		for (ExecutionListener l : listener)
			l.execStepped(sys);
	}

	@Override
	public void execFinished(Sys sys) {
		for (ExecutionListener l : listener)
			l.execFinished(sys);
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		for (ExecutionListener l : listener) {
			l.dbgBrkptReached(sys);
			l.execPaused(sys);
		}
	}
	
	// Execution Registry ======================
	
	private Sys sys;
	
	public void init(Sys sys) {
		this.sys = sys;
	}
	
	private void continueExecution(Processor proc) {
		proc.run();
		switch (proc.state) {
		case BREAKPOINT:
			dbgBrkptReached(sys);
			break;
		case HALTED:
			execFinished(sys);
			break;
		}
	}
	
	public void start() {
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.RUNNING;
		execStarted(sys);
		continueExecution(proc);
	}
	
	public void cont() {
		Processor proc = sys.getProcessor();
		proc.setContinue();
		continueExecution(proc);
	}
	
	public void step() {
		Processor proc = sys.getProcessor();
		proc.setContinue();
		boolean ran = proc.step();
		if (ran)
			execStepped(sys);
	}
	
	public void load(Assembly asm) {
		sys.load(asm);
	}
	
	
}

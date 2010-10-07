package de.unisb.prog.mips.parser.ui.launching;

import java.io.PrintStream;
import java.util.HashSet;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

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
	
	// UI Component Registry ===================
	
	PrintStream MIPSConsole = System.out;
	
	public void setConsoleOut(PrintStream consoleOut) {
		this.MIPSConsole = consoleOut != null ? consoleOut : System.out;
	}
	
	public PrintStream getConsoleOut() {
		return MIPSConsole;
	}
	
	// Execution Registry ======================

	private Sys sys = null;
	private Assembly asm = null;
	
	public void init(int memPages) {
		this.sys = new Sys(memPages, new SysCallDispatcher(new UISyscallImpl(MIPSConsole)));
		this.asm = null;
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
	
	public void start(boolean dbg) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.RUNNING;
		proc.setIgnoreBreaks(!dbg);
		execStarted(sys);
		continueExecution(proc);
	}
	
	public void cont() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		Processor proc = sys.getProcessor();
		proc.setContinue();
		continueExecution(proc);
	}
	
	public void pause() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.INTERRUPT;
		execPaused(sys);
	}
	
	public void step() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		Processor proc = sys.getProcessor();
		proc.setContinue();
		boolean ran = proc.step();
		
		switch (proc.state) {
		case BREAKPOINT:
			dbgBrkptReached(sys);
			break;
		case HALTED:
			execFinished(sys);
			break;
		default:
			proc.state = ExecutionState.INTERRUPT;
		}
			
		if (ran)
			execStepped(sys);
	}
	
	public void load(Assembly asm) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		sys.load(asm);
		this.asm = asm;
	}
	
}

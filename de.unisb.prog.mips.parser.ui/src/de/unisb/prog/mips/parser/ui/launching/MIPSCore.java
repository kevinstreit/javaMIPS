package de.unisb.prog.mips.parser.ui.launching;

import java.util.HashSet;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
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
	
	MIPSConsoleOutput MIPSConsole = null;
	
	public void setConsoleOut(MIPSConsoleOutput consoleOut) {
		this.MIPSConsole = consoleOut;
	}
	
	public MIPSConsoleOutput getConsoleOut() {
		return MIPSConsole;
	}
	
	// Execution Registry ======================

	private Sys sys = null;
	private Assembly asm = null;
	
	public void init(int memPages) {
		// TODO
		this.sys = new Sys(memPages, null , new SysCallDispatcher(new UISyscallImpl(MIPSConsole)));
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
		
		// TODO Make the error reporter communicate with the GUI
		sys.load(asm, new ErrorReporter<Position>() {
			@Override
			public void warning(String msg, Position arg) {
				System.out.format("%s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg);
			}
			@Override public void error(String msg, Position arg) { warning(msg, arg); }
			@Override public int errorsReported() { return 0; }
		});
		this.asm = asm;
	}
	
}

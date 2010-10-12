package de.unisb.prog.mips.parser.ui;

import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.ImageData;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.parser.ui.launching.ExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.UIExceptionHandler;
import de.unisb.prog.mips.parser.ui.launching.UISyscallImpl;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSCore implements ExecutionListener {
	
	// Some shared images ======================
	
	public static final String ICN_RUN_MIPS = "de.unisb.cs.prog.mips.runmips";
	public static final String ICN_RESUME_MIPS = "de.unisb.cs.prog.mips.resumemips";
	public static final String ICN_STEP_MIPS = "de.unisb.cs.prog.mips.stepmips";
	public static final String ICN_SUSPEND_MIPS = "de.unisb.cs.prog.mips.suspendmips";
	public static final String ICN_MIPS_CONSOLE = "de.unisb.cs.prog.mips.mipsconsole";
	public static final String ICN_DEBUG_MIPS = "de.unisb.cs.prog.mips.dbgmips";
	public static final String ICN_REGISTER_VIEW = "de.unisb.cs.prog.mips.registerview";
	
	private static ImageDescriptor createImageDesc(String path) {
		return ImageDescriptor.createFromImageData(new ImageData(MIPSCore.class.getResourceAsStream(path)));
	}
	
	static {
		ImageRegistry imgReg = JFaceResources.getImageRegistry();
		
		imgReg.put(ICN_RUN_MIPS, createImageDesc("/icons/icn/run.gif"));
		imgReg.put(ICN_RESUME_MIPS, createImageDesc("/icons/icn/resume_co.gif"));
		imgReg.put(ICN_STEP_MIPS, createImageDesc("/icons/icn/stepover_co.gif"));
		imgReg.put(ICN_SUSPEND_MIPS, createImageDesc("/icons/icn/suspend_co.gif"));
		imgReg.put(ICN_MIPS_CONSOLE, createImageDesc("/icons/icn/console_view.gif"));
		imgReg.put(ICN_DEBUG_MIPS, createImageDesc("/icons/icn/debug.gif"));
		imgReg.put(ICN_REGISTER_VIEW, createImageDesc("/icons/icn/register_view.gif"));
	}
	
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
	public void execStarted(Sys sys, Assembly asm) {
		for (ExecutionListener l : listener)
			l.execStarted(sys, asm);
	}

	@Override
	public void execPaused(Sys sys, Assembly asm) {
		for (ExecutionListener l : listener)
			l.execPaused(sys, asm);
	}
	
	@Override
	public void execContinued(Sys sys, Assembly asm) {
		for (ExecutionListener l : listener)
			l.execContinued(sys, asm);
	}

	@Override
	public void execStepped(Sys sys, Assembly asm) {
		for (ExecutionListener l : listener)
			l.execStepped(sys, asm);
	}

	@Override
	public void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		for (ExecutionListener l : listener)
			l.execFinished(sys, asm, interrupted);
	}

	@Override
	public void dbgBrkptReached(Sys sys, Assembly asm) {
		for (ExecutionListener l : listener) {
			l.dbgBrkptReached(sys, asm);
			l.execPaused(sys, asm);
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
	private int exitCode;
	private Job runningJob = null;
	
	public synchronized ExecutionState getExecutionState() {
		if (sys == null || sys.getProcessor() == null) 
			return null;
		else
			return sys.getProcessor().state;
	}
	
	public Sys getSys() {
		return sys;
	}

	public Assembly getAsm() {
		return asm;
	}
	
	public int getExitCode() {
		return exitCode;
	}

	public synchronized void setExitCode(int exitCode) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		this.exitCode = exitCode;
	}

	public synchronized void init(int memPages) {
		this.sys = new Sys(memPages, new UIExceptionHandler(), new SysCallDispatcher(new UISyscallImpl(MIPSConsole)));
		this.asm = null;
	}
	
	private void continueExecution(Processor proc) {
		proc.run();
		switch (proc.state) {
		case BREAKPOINT:
			dbgBrkptReached(sys, asm);
			break;
		case HALTED:
			execFinished(sys, asm, false);
			break;
		}
	}
	
	public synchronized void start(final boolean dbg) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		if (runningJob != null) {
			runningJob.cancel();
			execFinished(sys, asm, true);
		}
		
		runningJob = new Job("Run MIPS") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Processor proc = sys.getProcessor();
				proc.state = ExecutionState.RUNNING;
				proc.setIgnoreBreaks(!dbg);
				execStarted(sys, asm);
				continueExecution(proc);
				return Status.OK_STATUS;
			}
			
			@Override
			protected void canceling() {
				MIPSCore.getInstance().pause();
				runningJob = null;
			}
		};
		
		runningJob.setSystem(false);
		runningJob.schedule();
	}
	
	public synchronized void cont() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		if (runningJob != null)
			runningJob.cancel();
		
		runningJob = new Job("Run MIPS") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Processor proc = sys.getProcessor();
				proc.setContinue();
				execContinued(sys, asm);
				continueExecution(proc);
				return Status.OK_STATUS;
			}
			
			@Override
			protected void canceling() {
				Processor proc = sys.getProcessor();
				proc.state = ExecutionState.HALTED;
				execFinished(sys, asm, true);
			}
		};
		
		runningJob.setSystem(false);
		runningJob.schedule();
	}
	
	public synchronized void stopExec() {
		if (sys != null && asm != null)
			pause();
		
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.HALTED;
		
		if (runningJob != null)
			try {
				runningJob.join();
			} catch (InterruptedException e) {
				// WHat shall we do?
			}
		
		execFinished(sys, asm, true);
	}
	
	public synchronized void pause() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.INTERRUPT;
		
		if (runningJob != null)
			try {
				runningJob.join();
			} catch (InterruptedException e) {
				// WHat shall we do?
			}
		
		execPaused(sys, asm);
	}
	
	public synchronized void step() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		Processor proc = sys.getProcessor();
		proc.setContinue();
		boolean ran = proc.step();
		
		switch (proc.state) {
		case BREAKPOINT:
			dbgBrkptReached(sys, asm);
			break;
		case HALTED:
			execFinished(sys, asm, false);
			break;
		default:
			proc.state = ExecutionState.INTERRUPT;
		}
			
		if (ran)
			execStepped(sys, asm);
	}
	
	public synchronized void load(Assembly asm) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");
		
		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");
		
		// TODO Make the error reporter communicate with the GUI
		sys.load(asm, new ErrorReporter<Position>() {
			@Override
			public void warning(String msg, Position arg) {
				MIPSCore.getInstance().getConsoleOut().println(String.format("[ MIPS:ERROR ] %s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg), true);
			}
			@Override public void error(String msg, Position arg) { warning(msg, arg); }
			@Override public int errorsReported() { return 0; }
		});
		this.asm = asm;
	}
	
}

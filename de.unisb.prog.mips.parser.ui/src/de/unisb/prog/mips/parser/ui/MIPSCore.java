package de.unisb.prog.mips.parser.ui;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.parser.ui.launching.IAssemblyLoadListener;
import de.unisb.prog.mips.parser.ui.launching.IExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.UIExceptionHandler;
import de.unisb.prog.mips.parser.ui.launching.UISyscallImpl;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
import de.unisb.prog.mips.parser.ui.util.MarkerUtil;
import de.unisb.prog.mips.parser.ui.views.MIPSConsoleView;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSCore implements IExecutionListener, IAssemblyLoadListener {

	public static final String ICN_RUN_MIPS = "de.unisb.cs.prog.mips.runmips";
	public static final String ICN_RESUME_MIPS = "de.unisb.cs.prog.mips.resumemips";
	public static final String ICN_STEP_MIPS = "de.unisb.cs.prog.mips.stepmips";
	public static final String ICN_SUSPEND_MIPS = "de.unisb.cs.prog.mips.suspendmips";
	public static final String ICN_MIPS_CONSOLE = "de.unisb.cs.prog.mips.mipsconsole";
	public static final String ICN_DEBUG_MIPS = "de.unisb.cs.prog.mips.dbgmips";
	public static final String ICN_REGISTER_VIEW = "de.unisb.cs.prog.mips.registerview";
	public static final String ICN_INST_PTR = "de.unisb.cs.prog.mips.instptr";

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
		imgReg.put(ICN_INST_PTR, createImageDesc("/icons/icn/inst_ptr.gif"));
	}

	// Singleton management ====================

	private static MIPSCore instance = null;

	private MIPSCore() {

	}

	public static MIPSCore getInstance() {
		if (instance == null)
			instance = new MIPSCore();

		return instance;
	}

	// Listener Management =====================

	private final HashSet<IExecutionListener> execListener = new HashSet<IExecutionListener>();

	public void addExecutionListener(IExecutionListener l) {
		this.execListener.add(l);
	}

	public void removeExecutionListener(IExecutionListener l) {
		this.execListener.remove(l);
	}

	@Override
	public void execStarted(Sys sys, Assembly asm) {
		for (IExecutionListener l : this.execListener)
			l.execStarted(sys, asm);

		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);
	}

	@Override
	public void execPaused(Sys sys, Assembly asm) {
		for (IExecutionListener l : this.execListener)
			l.execPaused(sys, asm);

		createExecutionMarker(asm, sys);
	}

	@Override
	public void execContinued(Sys sys, Assembly asm) {
		for (IExecutionListener l : this.execListener)
			l.execContinued(sys, asm);

		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);
	}

	@Override
	public void execStepped(Sys sys, Assembly asm) {
		for (IExecutionListener l : this.execListener)
			l.execStepped(sys, asm);

		createExecutionMarker(asm, sys);
	}

	@Override
	public void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		for (IExecutionListener l : this.execListener)
			l.execFinished(sys, asm, interrupted);

		this.runningJob = null;
		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);
	}

	@Override
	public void dbgBrkptReached(Sys sys, Assembly asm) {
		for (IExecutionListener l : this.execListener) {
			l.dbgBrkptReached(sys, asm);
			l.execPaused(sys, asm);
		}

		createExecutionMarker(asm, sys);
	}

	private final HashSet<IAssemblyLoadListener> loadListener = new HashSet<IAssemblyLoadListener>();

	public void addAssemblyLoadListener(IAssemblyLoadListener l) {
		this.loadListener.add(l);
	}

	public void removeAssemblyLoadListener(IAssemblyLoadListener l) {
		this.loadListener.remove(l);
	}

	@Override
	public void assemblyLoaded(Assembly asm, Sys sys) {
		for (IAssemblyLoadListener l : this.loadListener)
			l.assemblyLoaded(asm, sys);
	}

	@Override
	public void assemblyReset() {
		for (IAssemblyLoadListener l : this.loadListener)
			l.assemblyReset();
	}

	// UI Component Registry ===================

	MIPSConsoleOutput MIPSConsole = null;

	public void setConsoleOut(MIPSConsoleOutput consoleOut) {
		this.MIPSConsole = consoleOut;
	}

	public MIPSConsoleOutput getConsoleOut() {
		if (this.MIPSConsole == null) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MIPSConsoleView.ID);
			} catch (PartInitException e) {
				// Nothing to do
			}
		}

		return this.MIPSConsole;
	}

	// Execution Registry ======================

	private Sys sys = null;
	private Assembly asm = null;
	private int exitCode;
	private Job runningJob = null;

	private void createExecutionMarker(Assembly asm, Sys sys) {
		// Remove old markers
		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);

		if (asm != null && sys != null) {
			// Create debugging marker
			Position pos = asm.getPosition(sys.getProcessor().pc);
			MarkerUtil.markPosition(pos, MarkerUtil.ID_CurrentIP, true, false);
		}
	}

	public synchronized ExecutionState getExecutionState() {
		if (this.sys == null || this.sys.getProcessor() == null)
			return null;
		else
			return this.sys.getProcessor().state;
	}

	public Sys getSys() {
		return this.sys;
	}

	public Assembly getAsm() {
		return this.asm;
	}

	public int getExitCode() {
		return this.exitCode;
	}

	public synchronized void setExitCode(int exitCode) {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		if (this.asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		this.exitCode = exitCode;
	}

	public synchronized void init(int memPages) {
		this.sys = new Sys(memPages, new UIExceptionHandler(), new SysCallDispatcher(new UISyscallImpl(this.MIPSConsole)));
		this.asm = null;
	}

	private void continueExecution(Processor proc) {
		proc.run();
		switch (proc.state) {
		case BREAKPOINT:
			dbgBrkptReached(this.sys, this.asm);
			break;
		case HALTED:
			execFinished(this.sys, this.asm, false);
			break;
		}
	}

	public synchronized void start(final boolean dbg) {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		if (this.asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		if (this.runningJob != null) {
			this.runningJob.cancel();
			execFinished(this.sys, this.asm, true);
		}

		this.runningJob = new Job("Run MIPS") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Processor proc = MIPSCore.this.sys.getProcessor();
				proc.state = ExecutionState.RUNNING;
				proc.setIgnoreBreaks(!dbg);
				execStarted(MIPSCore.this.sys, MIPSCore.this.asm);
				continueExecution(proc);
				return Status.OK_STATUS;
			}

			@Override
			protected void canceling() {
				MIPSCore.getInstance().pause();
				MIPSCore.this.runningJob = null;
			}
		};

		this.runningJob.setSystem(false);
		this.runningJob.schedule();
	}

	public synchronized void cont() {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		if (this.asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		if (this.runningJob != null)
			this.runningJob.cancel();

		this.runningJob = new Job("Run MIPS") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Processor proc = MIPSCore.this.sys.getProcessor();
				proc.setContinue();
				execContinued(MIPSCore.this.sys, MIPSCore.this.asm);
				continueExecution(proc);
				return Status.OK_STATUS;
			}

			@Override
			protected void canceling() {
				Processor proc = MIPSCore.this.sys.getProcessor();
				proc.state = ExecutionState.HALTED;
				execFinished(MIPSCore.this.sys, MIPSCore.this.asm, true);
			}
		};

		this.runningJob.setSystem(false);
		this.runningJob.schedule();
	}

	public synchronized void stopExec() {
		if (this.sys != null) {
			Processor proc = this.sys.getProcessor();
			if (proc.state == ExecutionState.HALTED)
				return;
		}

		if (this.sys != null && this.asm != null)
			pause();

		Processor proc = this.sys.getProcessor();
		proc.state = ExecutionState.HALTED;

		if (this.runningJob != null) {
			try {
				this.runningJob.join();
			} catch (InterruptedException e) {
				// WHat shall we do?
			}
		}

		execFinished(this.sys, this.asm, true);
	}

	public synchronized void pause() {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		if (this.asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		Processor proc = this.sys.getProcessor();
		proc.state = ExecutionState.INTERRUPT;

		if (this.runningJob != null)
			try {
				this.runningJob.join();
			} catch (InterruptedException e) {
				// What shall we do?
			}

			execPaused(this.sys, this.asm);
	}

	public synchronized void step() {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		if (this.asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		Processor proc = this.sys.getProcessor();
		proc.setContinue();
		boolean ran = proc.step();

		switch (proc.state) {
		case BREAKPOINT:
			dbgBrkptReached(this.sys, this.asm);
			break;
		case HALTED:
			execFinished(this.sys, this.asm, false);
			break;
		default:
			proc.state = ExecutionState.INTERRUPT;
		}

		if (ran)
			execStepped(this.sys, this.asm);
	}

	public void unloadASM() {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		Processor proc = this.sys.getProcessor();
		if(proc.state != ExecutionState.HALTED)
			stopExec();

		this.asm = null;
		assemblyReset();
	}

	public synchronized void load(Assembly asm) {
		if (this.sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		final AtomicBoolean hasErrors = new AtomicBoolean(false);
		this.sys.load(asm, new ErrorReporter<Position>() {
			private void createMarker(String msg, Position arg, int severity) throws CoreException {
				IMarker m = MarkerUtil.markPosition(arg, IMarker.PROBLEM, false, false);
				if (m != null) {
					m.setAttribute(IMarker.MESSAGE, String.format("[ MIPS:ERROR ] %s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg));
					m.setAttribute(IMarker.SEVERITY, severity);
				}
			}

			@Override
			public void warning(String msg, Position arg) {
				try {
					createMarker(msg, arg, IMarker.SEVERITY_WARNING);
				} catch (CoreException e) {
					Status stat = new Status(Status.WARNING, "de.unisb.prog.mips.parser.ui", String.format("[ MIPS:WARNING ] %s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg));
					StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
				}
			}

			@Override public void error(String msg, Position arg) {
				hasErrors.set(true);
				try {
					createMarker(msg, arg, IMarker.SEVERITY_ERROR);
				} catch (CoreException e) {
					Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", String.format("[ MIPS:ERROR ] %s(%d): %s", arg.getFilename(), arg.getLineNumber(), msg));
					StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
				}
			}

			@Override public int errorsReported() { return 0; }
		});

		if (!hasErrors.get()) {
			this.asm = asm;
			assemblyLoaded(this.asm, this.sys);
		}
	}

}

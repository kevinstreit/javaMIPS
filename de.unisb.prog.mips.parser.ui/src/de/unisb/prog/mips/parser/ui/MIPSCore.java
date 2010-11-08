package de.unisb.prog.mips.parser.ui;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.statushandlers.StatusManager;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.parser.ui.launching.IAssemblyLoadListener;
import de.unisb.prog.mips.parser.ui.launching.IExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.MIPSBreakpoint;
import de.unisb.prog.mips.parser.ui.launching.RunnableMIPSPropTester;
import de.unisb.prog.mips.parser.ui.launching.UIExceptionHandler;
import de.unisb.prog.mips.parser.ui.launching.UISyscallImpl;
import de.unisb.prog.mips.parser.ui.util.BuildUtil;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
import de.unisb.prog.mips.parser.ui.util.MarkerUtil;
import de.unisb.prog.mips.parser.ui.util.UIErrorReporter;
import de.unisb.prog.mips.parser.ui.views.EditorOpenListener;
import de.unisb.prog.mips.parser.ui.views.MIPSConsoleView;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.util.Pair;

public class MIPSCore {

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
	private EditorOpenListener editorListener;

	private MIPSCore() {
		editorListener = new EditorOpenListener() {
			@Override
			public void editorActivated(IEditorPart editor) {
				if (editor.getEditorInput().exists() && editor.getEditorInput() instanceof FileEditorInput) {
					IFile f = ((FileEditorInput) editor.getEditorInput()).getFile();

					if (RunnableMIPSPropTester.isMIPSRunnable(f)) {
						IProject proj = f.getProject();
						if (MIPSCore.getInstance().getLodedProject() != proj) {
							try {
								proj.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
							} catch (CoreException e) {
								// Nothing to do
							}

							UIErrorReporter error = new UIErrorReporter(true);
							Collection<Assembly> asm = BuildUtil.getASM(proj, error);

							boolean loadable = asm != null && error.errorsReported() == 0;
							if (loadable) {
								loadable = true;
								MIPSCore.getInstance().init(1024);
								loadable = MIPSCore.getInstance().load(asm, proj);
							}

							if ((!loadable || asm != null && asm.isEmpty()) && MIPSCore.getInstance().getSys() != null)
								MIPSCore.getInstance().unloadASM();
						}
					}
				}
			}

			@Override public void editorDeactivated(IEditorPart editor) {}
			@Override public void editorOpened(IEditorPart editor) {
				// This is due to a "bug" in the XTextEditor which doesn't show markers correctly on editor opening
				// So in order to show the breakpoint markers, we set some attribute just to fire a resource changed event to make the editor show it
				IResource res = (IResource) editor.getEditorInput().getAdapter(IResource.class);
				if (res != null) {
					IMarker[] ms;
					try {
						ms = res.findMarkers(MarkerUtil.ID_Breakpoint, true, IResource.DEPTH_INFINITE);
						for (IMarker m : ms) {
							m.setAttribute(IMarker.LINE_NUMBER, m.getAttribute(IMarker.LINE_NUMBER));
						}
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
			@Override public void editorClosed(IEditorPart editor) {
				if (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() == null) {
					try {
						MIPSCore.getInstance().unloadASM();
					} catch (Exception e) {
						// Nothing to be done
					}
				}
			}
		};

		PlatformUI.getWorkbench().addWindowListener(editorListener);
	}

	public static MIPSCore getInstance() {
		if (instance == null)
			instance = new MIPSCore();

		return instance;
	}

	// Listener Management =====================

	private final HashSet<IExecutionListener> execListener = new HashSet<IExecutionListener>();

	public void addExecutionListener(IExecutionListener l) {
		execListener.add(l);
	}

	public void removeExecutionListener(IExecutionListener l) {
		execListener.remove(l);
	}

	private void execStarted(Sys sys, Assembly asm) {
		for (IExecutionListener l : execListener)
			l.execStarted(sys, asm);

		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);
	}

	private void execPaused(Sys sys, Assembly asm) {
		for (IExecutionListener l : execListener)
			l.execPaused(sys, asm);

		createExecutionMarker(asm, sys);
	}

	private void execContinued(Sys sys, Assembly asm) {
		for (IExecutionListener l : execListener)
			l.execContinued(sys, asm);

		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);
	}

	private void execStepped(Sys sys, Assembly asm) {
		for (IExecutionListener l : execListener)
			l.execStepped(sys, asm);

		createExecutionMarker(asm, sys);
	}

	private void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		for (IExecutionListener l : execListener)
			l.execFinished(sys, asm, interrupted);

		if (sys != null)
			sys.getProcessor().state = ExecutionState.HALTED;
		runningJob = null;
		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);
	}

	public void inputModeStarted() {
		for (IExecutionListener l : execListener)
			l.inputModeStarted();
	}

	public void inputModeDone() {
		for (IExecutionListener l : execListener)
			l.inputModeDone();
	}

	private void dbgBrkptReached(Sys sys, Assembly asm) {
		for (IExecutionListener l : execListener) {
			l.dbgBrkptReached(sys, asm);
			l.execPaused(sys, asm);
		}

		createExecutionMarker(asm, sys);
	}

	private final HashSet<IAssemblyLoadListener> loadListener = new HashSet<IAssemblyLoadListener>();

	public void addAssemblyLoadListener(IAssemblyLoadListener l) {
		loadListener.add(l);
	}

	public void removeAssemblyLoadListener(IAssemblyLoadListener l) {
		loadListener.remove(l);
	}

	private void assemblyLoaded(Assembly asm, Sys sys) {
		for (IAssemblyLoadListener l : loadListener)
			l.assemblyLoaded(asm, sys);
	}

	private void assemblyReset() {
		for (IAssemblyLoadListener l : loadListener)
			l.assemblyReset();
	}

	// UI Component Registry ===================

	MIPSConsoleOutput MIPSConsole = null;

	public void setConsoleOut(MIPSConsoleOutput consoleOut) {
		MIPSConsole = consoleOut;
	}

	public MIPSConsoleOutput getConsoleOut() {
		if (MIPSConsole == null) {
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MIPSConsoleView.ID);
			} catch (PartInitException e) {
				// Nothing to do
			}
		}

		return MIPSConsole;
	}

	// Execution Registry ======================

	private Sys sys = null;
	private Assembly asm = null;
	private IProject proj = null;
	private int exitCode;
	private Job runningJob = null;

	private void createExecutionMarker(Assembly asm, Sys sys) {
		// Remove old markers
		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_CurrentIP);

		if (asm != null && sys != null) {
			// Create debugging marker
			Element elm = asm.getElementAt(sys.getProcessor().pc);
			if (elm != null && elm.getPosition() != null)
				MarkerUtil.markPosition(elm.getPosition(), MarkerUtil.ID_CurrentIP, true, false);
		}
	}

	public ExecutionState getExecutionState() {
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

	public IProject getLodedProject() {
		return proj;
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
		sys = new Sys(memPages, new UIExceptionHandler(), new SysCallDispatcher(new UISyscallImpl(MIPSConsole)));
		asm = null;
		proj = null;
	}

	private void continueExecution(Processor proc, boolean ignoreNextBreakpoint) {
		proc.run(ignoreNextBreakpoint);
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
			Processor proc = sys.getProcessor();
			proc.state = ExecutionState.HALTED;
			execFinished(sys, asm, true);
		}

		runningJob = new Job("Run MIPS") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				Processor proc = sys.getProcessor();
				proc.state = ExecutionState.RUNNING;
				proc.setIgnoreBreaks(!dbg);
				execStarted(sys, asm);
				continueExecution(proc, false);
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
				continueExecution(proc, true);
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
		if (sys != null) {
			Processor proc = sys.getProcessor();
			if (proc.state == ExecutionState.HALTED || proc.state == null)
				return;
		}

		if (sys != null && asm != null)
			pause();

		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.HALTED;

		if (runningJob != null) {
			try {
				runningJob.join();
			} catch (InterruptedException e) {
				// What shall we do?
			}
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
				// What shall we do?
			}

			execPaused(sys, asm);
	}

	public synchronized void step(boolean ignoreNextBreak) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		if (asm == null)
			throw new IllegalStateException("Assembly not loaded (asm == null)");

		Processor proc = sys.getProcessor();
		proc.setContinue();
		boolean ran = proc.step(ignoreNextBreak);

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

	public void unloadASM() {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		Processor proc = sys.getProcessor();
		if(proc.state != ExecutionState.HALTED && proc.state != null)
			stopExec();

		asm = null;
		proj = null;
		assemblyReset();
	}

	public synchronized boolean load(Collection<Assembly> assemblies, IProject project) {
		if (sys == null)
			throw new IllegalStateException("MIPSCore was not initialized (sys == null)");

		proj = project;
		Processor proc = sys.getProcessor();
		proc.clearBreakpoints();

		UIErrorReporter error = new UIErrorReporter(true);

		Assembly linked = Assembly.link(assemblies, error);
		linked.prepare();
		boolean runnable;

		if (error.errorsReported() == 0) {
			try {
				runnable = sys.load(linked);
			} catch (Exception e) {
				Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", String.format("Error: Unknown exception occured", e));
				StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
				runnable = false;
			}

			if (runnable) {
				asm = linked;
				Map<Pair<URI, Integer>, Element> elements = linked.computeElementMap();

				try {
					IMarker[] breakpts = project.findMarkers(MarkerUtil.ID_Breakpoint, true, IResource.DEPTH_INFINITE);
					for (IMarker m : breakpts) {
						IBreakpoint brk = DebugPlugin.getDefault().getBreakpointManager().getBreakpoint(m);
						IResource res = m.getResource();
						if (brk != null && res != null && brk instanceof MIPSBreakpoint) {
							MIPSBreakpoint mbrk = (MIPSBreakpoint) brk;
							Element elem = elements.get(new Pair<URI, Integer>(res.getLocationURI(), mbrk.getLineNumber()));

							if (elem != null) {
								proc.addBreakpoint(elem.addressOf());
								continue;
							}
						}

						try {
							// We delete the marker if we didn't succeed in setting a correct breakpoint
							if (brk != null)
								brk.delete();
							m.delete();
						} catch (Exception e) {
							// Nothing
						}
					}
				} catch (CoreException e) {
					// Nothing
				}

				assemblyLoaded(asm, sys);
				return true;
			} else {
				proj = null;
				return false;
			}
		} else {
			return false;
		}
	}

}

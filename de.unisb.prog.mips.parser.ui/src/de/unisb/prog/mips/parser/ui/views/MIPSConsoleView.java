package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.launching.IExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.RunnableMIPSPropTester;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSConsoleView extends ViewPart implements IExecutionListener, IActiveEditorProvider {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.MIPSConsoleView";
	
	private StyledText text;
	private MIPSConsoleOutput out;
	
	private RunMIPSAction runAction;
	private RunMIPSAction debugAction;
	
	private Action stopAction;
	private Action continueAction;
	private Action stepAction;
	
	private IEditorPart lastActiveEditor = null;
	private EditorOpenListener editorListener = null;
	
	private Sys sys = null;
	private Assembly asm = null;

	public MIPSConsoleView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		text = new StyledText(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
		text.setFont(JFaceResources.getTextFont());
		this.out = new MIPSConsoleOutput(text);
		MIPSCore.getInstance().setConsoleOut(this.out);
		MIPSCore.getInstance().addExecutionListener(this);
		makeActions();
		contributeToActionBars();
		
		editorListener = new EditorOpenListener() {
			@Override
			public void editorActivated(IEditorPart editor) {
				lastActiveEditor = RunnableMIPSPropTester.isMIPSRunnable(editor) ? editor : null;
				MIPSConsoleView.this.checkActionEnablement();
			}

			@Override
			public void editorDeactivated(IEditorPart editor) {
				// Nothing
			}

			@Override
			public void editorOpened(IEditorPart editor) {
				// Nothing
			}

			@Override
			public void editorClosed(IEditorPart editor) {
				if (editor == lastActiveEditor) {
					lastActiveEditor = null;
					MIPSConsoleView.this.checkActionEnablement();
				}
			}
		};
		
		PlatformUI.getWorkbench().addWindowListener(editorListener);
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
			editorListener.windowOpened(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			editorListener.windowActivated(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		}
		
		checkActionEnablement();
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}
	
	@Override
	public void dispose() {
		ExecutionState state = MIPSCore.getInstance().getExecutionState();
		
		if (state != null)
			MIPSCore.getInstance().stopExec();
		
		MIPSCore.getInstance().removeExecutionListener(this);
		PlatformUI.getWorkbench().removeWindowListener(editorListener);
	}
	
	private void checkActionEnablement() {
		runAction.checkEnablement();
		debugAction.checkEnablement();
		
		ExecutionState state = MIPSCore.getInstance().getExecutionState();
		
		if (state != null) {
			this.stopAction.setEnabled(state != ExecutionState.HALTED);
			this.continueAction.setEnabled(state != ExecutionState.HALTED);
			this.stepAction.setEnabled(state == ExecutionState.INTERRUPT || state == ExecutionState.BREAKPOINT);
		} else {
			this.stopAction.setEnabled(false);
			this.continueAction.setEnabled(false);
			this.stepAction.setEnabled(false);
		}
	}

	@Override
	public void execStarted(Sys sys, Assembly asm) {
		this.out.clear();
		this.out.println("[ Execution started in " + (sys.getProcessor().ignoresBreak() ? "normal" : "debug") + " mode ]", true);
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_SUSPEND_MIPS));
		checkActionEnablement();
	}

	@Override
	public void execPaused(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));
		checkActionEnablement();
	}
	
	@Override
	public void execContinued(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_SUSPEND_MIPS));
		checkActionEnablement();
	}

	@Override
	public void execStepped(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		checkActionEnablement();
	}

	@Override
	public void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));
		
		if (interrupted)
			this.out.println("[ Execution interrupted ]", true);
		else
			this.out.println("[ Execution finished with return code " + MIPSCore.getInstance().getExitCode() + " ]", true);
		
		checkActionEnablement();
	}

	@Override
	public void dbgBrkptReached(Sys sys, Assembly asm) {
		// Done in execPaused
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(runAction);
		manager.add(debugAction);
		
		manager.add(new Separator());
		
		manager.add(stopAction);
		manager.add(continueAction);
		manager.add(stepAction);
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(runAction);
		manager.add(debugAction);
		
		manager.add(new Separator());
		
		manager.add(stopAction);
		manager.add(continueAction);
		manager.add(stepAction);
	}

	private void makeActions() {
		runAction = new RunMIPSAction(this, false);
		debugAction = new RunMIPSAction(this, true);
		
		continueAction = new Action() {
			public void run() {
				if (sys != null) {
					if (sys.getProcessor().state == ExecutionState.RUNNING)
						MIPSCore.getInstance().pause();
					else
						MIPSCore.getInstance().cont();
				}
			}
		};
		continueAction.setText("Continue");
		continueAction.setToolTipText("Continue a paused MIPS execution");
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));
		
		stepAction = new Action() {
			public void run() {
				if (sys != null) {
					Position pos = asm.getPosition(sys.getProcessor().pc);
					int origLine = pos.getLineNumber();
					int pcBefore;
					do {
						pcBefore = sys.getProcessor().pc;
						MIPSCore.getInstance().step();
						pos = asm.getPosition(sys.getProcessor().pc);
					} while (pos.getLineNumber() == origLine && (sys.getProcessor().pc != pcBefore));
				}
			}
		};
		stepAction.setText("Step");
		stepAction.setToolTipText("Perform one step in a paused MIPS execution");
		stepAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_STEP_MIPS));
		
		stopAction = new Action() {
			public void run() {
				if (sys != null) {
					MIPSCore.getInstance().stopExec();
				}
			}
		};
		stopAction.setText("Stop");
		stopAction.setToolTipText("Stop the currently running execution");
		stopAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP));
		stopAction.setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP_DISABLED));
	}

	@Override
	public IEditorPart getActiveEditor() {
		return lastActiveEditor;
	}
}

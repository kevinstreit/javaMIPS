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
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.launching.IExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.RunnableMIPSPropTester;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
import de.unisb.prog.mips.simulator.Processor;
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
		text = new StyledText(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		text.setFont(JFaceResources.getTextFont());
		out = new MIPSConsoleOutput(text);
		MIPSCore.getInstance().setConsoleOut(out);
		MIPSCore.getInstance().addExecutionListener(this);
		makeActions();
		contributeToActionBars();

		editorListener = new EditorOpenListener() {
			IPropertyListener propListener = new IPropertyListener() {
				public void propertyChanged(Object source, int propId) {
					if (
							source instanceof IEditorPart
							&& propId == IEditorPart.PROP_DIRTY
							&& ((IEditorPart) source).isDirty()
							&& RunnableMIPSPropTester.isMIPSRunnable(((IEditorPart) source).getEditorInput())
					) {
						ExecutionState state = MIPSCore.getInstance().getExecutionState();

						if (state != null) {
							MIPSCore.getInstance().stopExec();
						}
					}
				}
			};

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
				if (RunnableMIPSPropTester.isMIPSRunnable(editor.getEditorInput()))
					editor.addPropertyListener(propListener);
			}

			@Override
			public void editorClosed(IEditorPart editor) {
				if (editor == lastActiveEditor) {
					lastActiveEditor = null;
					MIPSConsoleView.this.checkActionEnablement();
				}
				editor.removePropertyListener(propListener);
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
			stopAction.setEnabled(state != ExecutionState.HALTED);
			continueAction.setEnabled(state != ExecutionState.HALTED);
			stepAction.setEnabled(state == ExecutionState.INTERRUPT || state == ExecutionState.BREAKPOINT);
		} else {
			stopAction.setEnabled(false);
			continueAction.setEnabled(false);
			stepAction.setEnabled(false);
		}
	}

	public void execStarted(Sys sys, Assembly asm) {
		out.clear();
		out.println("[ Execution started in " + (sys.getProcessor().ignoresBreak() ? "normal" : "debug") + " mode ]", true);
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_SUSPEND_MIPS));
		checkActionEnablement();
	}

	public void execPaused(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));
		checkActionEnablement();
	}

	public void execContinued(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_SUSPEND_MIPS));
		checkActionEnablement();
	}

	public void execStepped(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		checkActionEnablement();
	}

	public void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		this.sys = sys;
		this.asm = asm;
		continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));

		if (interrupted)
			out.println("[ Execution interrupted ]", true);
		else
			out.println("[ Execution finished with return code " + MIPSCore.getInstance().getExitCode() + " ]", true);

		checkActionEnablement();
	}

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
			@Override
			public void run() {
				if (sys != null) {
					continueAction.setEnabled(false);

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
			@Override
			public void run() {
				if (sys != null) {
					continueAction.setEnabled(false);
					stepAction.setEnabled(false);

					Thread step = new Thread(new Runnable() {
						public void run() {
							Position pos = asm.getPosition(sys.getProcessor().pc);
							int origLine = pos.getLineNumber();
							int pcBefore;
							Processor proc = sys.getProcessor();

							do {
								pcBefore = sys.getProcessor().pc;
								MIPSCore.getInstance().step(true);
								pos = asm == null || sys == null ? null : asm.getPosition(sys.getProcessor().pc);
							} while (pos != null && pos.getLineNumber() == origLine && sys.getProcessor().pc != pcBefore && proc != null && proc.state == ExecutionState.RUNNING);

							text.getDisplay().syncExec(new Runnable() {
								public void run() {
									stepAction.setEnabled(true);
								}
							});
						}
					});
					step.start();
				}
			}
		};
		stepAction.setText("Step");
		stepAction.setToolTipText("Perform one step in a paused MIPS execution");
		stepAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_STEP_MIPS));

		stopAction = new Action() {
			@Override
			public void run() {
				if (sys != null) {
					continueAction.setEnabled(false);

					MIPSCore.getInstance().stopExec();
				}
			}
		};
		stopAction.setText("Stop");
		stopAction.setToolTipText("Stop the currently running execution");
		stopAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP));
		stopAction.setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP_DISABLED));
	}

	public IEditorPart getActiveEditor() {
		return lastActiveEditor;
	}
}

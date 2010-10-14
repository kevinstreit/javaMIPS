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
		this.text = new StyledText(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
		this.text.setFont(JFaceResources.getTextFont());
		this.out = new MIPSConsoleOutput(this.text);
		MIPSCore.getInstance().setConsoleOut(this.out);
		MIPSCore.getInstance().addExecutionListener(this);
		makeActions();
		contributeToActionBars();

		this.editorListener = new EditorOpenListener() {
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

							// TODO: Eventually move to a more central place (like a builder for example)
							MIPSCore.getInstance().unloadASM();
						}
					}
				}
			};

			@Override
			public void editorActivated(IEditorPart editor) {
				MIPSConsoleView.this.lastActiveEditor = RunnableMIPSPropTester.isMIPSRunnable(editor) ? editor : null;
				MIPSConsoleView.this.checkActionEnablement();
			}

			@Override
			public void editorDeactivated(IEditorPart editor) {
				// Nothing
			}

			@Override
			public void editorOpened(IEditorPart editor) {
				if (RunnableMIPSPropTester.isMIPSRunnable(editor.getEditorInput()))
					editor.addPropertyListener(this.propListener);
			}

			@Override
			public void editorClosed(IEditorPart editor) {
				if (editor == MIPSConsoleView.this.lastActiveEditor) {
					MIPSConsoleView.this.lastActiveEditor = null;
					MIPSConsoleView.this.checkActionEnablement();
				}
				editor.removePropertyListener(this.propListener);
			}
		};

		PlatformUI.getWorkbench().addWindowListener(this.editorListener);
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
			this.editorListener.windowOpened(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			this.editorListener.windowActivated(PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		}

		checkActionEnablement();
	}

	@Override
	public void setFocus() {
		this.text.setFocus();
	}

	@Override
	public void dispose() {
		ExecutionState state = MIPSCore.getInstance().getExecutionState();

		if (state != null)
			MIPSCore.getInstance().stopExec();

		MIPSCore.getInstance().removeExecutionListener(this);
		PlatformUI.getWorkbench().removeWindowListener(this.editorListener);
	}

	private void checkActionEnablement() {
		this.runAction.checkEnablement();
		this.debugAction.checkEnablement();

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

	public void execStarted(Sys sys, Assembly asm) {
		this.out.clear();
		this.out.println("[ Execution started in " + (sys.getProcessor().ignoresBreak() ? "normal" : "debug") + " mode ]", true);
		this.sys = sys;
		this.asm = asm;
		this.continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_SUSPEND_MIPS));
		checkActionEnablement();
	}

	public void execPaused(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		this.continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));
		checkActionEnablement();
	}

	public void execContinued(Sys sys, Assembly asm) {
		this.sys = sys;
		this.asm = asm;
		this.continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_SUSPEND_MIPS));
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
		this.continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));

		if (interrupted)
			this.out.println("[ Execution interrupted ]", true);
		else
			this.out.println("[ Execution finished with return code " + MIPSCore.getInstance().getExitCode() + " ]", true);

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
		manager.add(this.runAction);
		manager.add(this.debugAction);

		manager.add(new Separator());

		manager.add(this.stopAction);
		manager.add(this.continueAction);
		manager.add(this.stepAction);
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(this.runAction);
		manager.add(this.debugAction);

		manager.add(new Separator());

		manager.add(this.stopAction);
		manager.add(this.continueAction);
		manager.add(this.stepAction);
	}

	private void makeActions() {
		this.runAction = new RunMIPSAction(this, false);
		this.debugAction = new RunMIPSAction(this, true);

		this.continueAction = new Action() {
			@Override
			public void run() {
				if (MIPSConsoleView.this.sys != null) {
					if (MIPSConsoleView.this.sys.getProcessor().state == ExecutionState.RUNNING)
						MIPSCore.getInstance().pause();
					else
						MIPSCore.getInstance().cont();
				}
			}
		};
		this.continueAction.setText("Continue");
		this.continueAction.setToolTipText("Continue a paused MIPS execution");
		this.continueAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RESUME_MIPS));

		this.stepAction = new Action() {
			@Override
			public void run() {
				if (MIPSConsoleView.this.sys != null) {
					Position pos = MIPSConsoleView.this.asm.getPosition(MIPSConsoleView.this.sys.getProcessor().pc);
					int origLine = pos.getLineNumber();
					int pcBefore;
					do {
						pcBefore = MIPSConsoleView.this.sys.getProcessor().pc;
						MIPSCore.getInstance().step();
						pos = MIPSConsoleView.this.asm.getPosition(MIPSConsoleView.this.sys.getProcessor().pc);
					} while (pos.getLineNumber() == origLine && MIPSConsoleView.this.sys.getProcessor().pc != pcBefore);
				}
			}
		};
		this.stepAction.setText("Step");
		this.stepAction.setToolTipText("Perform one step in a paused MIPS execution");
		this.stepAction.setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_STEP_MIPS));

		this.stopAction = new Action() {
			@Override
			public void run() {
				if (MIPSConsoleView.this.sys != null) {
					MIPSCore.getInstance().stopExec();
				}
			}
		};
		this.stopAction.setText("Stop");
		this.stopAction.setToolTipText("Stop the currently running execution");
		this.stopAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP));
		this.stopAction.setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_STOP_DISABLED));
	}

	public IEditorPart getActiveEditor() {
		return this.lastActiveEditor;
	}
}

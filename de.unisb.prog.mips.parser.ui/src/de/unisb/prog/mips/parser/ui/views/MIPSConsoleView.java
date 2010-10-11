package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.parser.ui.launching.ExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.MIPSCore;
import de.unisb.prog.mips.parser.ui.util.MIPSConsoleOutput;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSConsoleView extends ViewPart implements ExecutionListener {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.MIPSConsoleView";
	
	private StyledText text;
	private MIPSConsoleOutput out;
	
	private Action stopAction;
	private Action continueAction;
	private Action stepAction;
	
	private Sys sys = null;

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
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}
	
	@Override
	public void dispose() {
		MIPSCore.getInstance().removeExecutionListener(this);
	}

	@Override
	public void execStarted(Sys sys) {
		this.out.clear();
		this.out.println("[ Execution started in " + (sys.getProcessor().ignoresBreak() ? "normal" : "debug") + " mode ]", true);
		this.sys = sys;
		this.continueAction.setEnabled(true);
		this.stepAction.setEnabled(true);
		this.stopAction.setEnabled(true);
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET));
	}

	@Override
	public void execPaused(Sys sys) {
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET_SUSPENDED));
		this.stopAction.setEnabled(true);
		this.continueAction.setEnabled(true);
		this.stepAction.setEnabled(true);
	}
	
	@Override
	public void execContinued(Sys sys) {
		// TODO Auto-generated method stub
		this.sys = sys;
		this.stopAction.setEnabled(true);
		this.continueAction.setEnabled(true);
		this.stepAction.setEnabled(true);
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET));
	}

	@Override
	public void execStepped(Sys sys) {
		
	}

	@Override
	public void execFinished(Sys sys, boolean interrupted) {
		this.sys = null;
		this.continueAction.setEnabled(false);
		this.stepAction.setEnabled(false);
		this.stopAction.setEnabled(false);
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET_TERMINATED));
		
		if (interrupted)
			this.out.println("[ Execution interrupted ]", true);
		else
			this.out.println("[ Execution finished with return code " + MIPSCore.getInstance().getExitCode() + " ]", true);
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		// Done in execPaused
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(stopAction);
		manager.add(continueAction);
		manager.add(stepAction);
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(stopAction);
		manager.add(continueAction);
		manager.add(stepAction);
	}

	private void makeActions() {
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
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET_TERMINATED));
		continueAction.setEnabled(false);
		
		stepAction = new Action() {
			public void run() {
				if (sys != null) {
					MIPSCore.getInstance().step();
				}
			}
		};
		stepAction.setText("Step");
		stepAction.setToolTipText("Perform one step in a paused MIPS execution");
		stepAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
		stepAction.setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD_DISABLED));
		stepAction.setEnabled(false);
		
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
		stopAction.setEnabled(false);
	}
}

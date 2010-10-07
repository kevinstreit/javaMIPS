package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.parser.ui.launching.ExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.MIPSCore;
import de.unisb.prog.mips.parser.ui.util.TextOutputStream;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSConsoleView extends ViewPart implements ExecutionListener {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.MIPSConsoleView";
	
	private static FontRegistry fonts;
	static {
		fonts = new FontRegistry(PlatformUI.getWorkbench().getDisplay());
		fonts.put("code", new FontData[]{new FontData("Courier New", 10, SWT.NORMAL)});
	}
	
	private Text text;
	
	private Action continueAction;
	private Action stepAction;
	
	private Sys sys = null;

	public MIPSConsoleView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
		text.setFont(fonts.get("code"));
		MIPSCore.getInstance().setConsoleOut(new TextOutputStream(text));
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
		this.text.setText("");
		this.sys = sys;
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET));
	}

	@Override
	public void execPaused(Sys sys) {
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET_SUSPENDED));
		this.continueAction.setEnabled(true);
		this.stepAction.setEnabled(true);
	}

	@Override
	public void execStepped(Sys sys) {
		
	}

	@Override
	public void execFinished(Sys sys) {
		this.sys = null;
		this.continueAction.setEnabled(false);
		this.stepAction.setEnabled(false);
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET_TERMINATED));
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		continueAction.setImageDescriptor(DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_OBJS_DEBUG_TARGET_SUSPENDED));
		this.continueAction.setEnabled(true);
		this.stepAction.setEnabled(true);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(continueAction);
		manager.add(new Separator());
		manager.add(stepAction);
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
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
	}
}

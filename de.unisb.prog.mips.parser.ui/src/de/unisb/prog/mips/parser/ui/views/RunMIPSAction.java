package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.JFaceResources;

import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.launching.ExecutableMIPSShortcut;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;

public class RunMIPSAction extends Action {

	private final boolean debug;
	private final IActiveEditorProvider editorProvider;

	public RunMIPSAction(IActiveEditorProvider provider, boolean debug) {
		this.debug = debug;
		this.editorProvider = provider;

		if (debug) {
			setText("Debug");
			setToolTipText("Debug the project of the currently opened file as a MIPS application");
			setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_DEBUG_MIPS));
		} else {
			setText("Run");
			setToolTipText("Run the project of the currently opened file as a MIPS application");
			setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(MIPSCore.ICN_RUN_MIPS));
		}

		setEnabled(false);
	}

	@Override
	public void run() {
		if (this.editorProvider.getActiveEditor() == null)
			return;

		ExecutableMIPSShortcut.launch(this.editorProvider.getActiveEditor(), this.debug);
	}

	public void checkEnablement() {
		setEnabled(
				this.editorProvider.getActiveEditor() != null
				&& (
						MIPSCore.getInstance().getExecutionState() == null
						|| MIPSCore.getInstance().getExecutionState() == ExecutionState.HALTED
				)
		);
	}

	public void dispose() {

	}

}

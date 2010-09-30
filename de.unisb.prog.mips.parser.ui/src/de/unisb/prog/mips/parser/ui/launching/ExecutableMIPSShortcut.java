package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

public class ExecutableMIPSShortcut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		// TODO Auto-generated method stub
		System.out.println("Launch Selection");
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub
		System.out.println("Launch Editor");
	}

}

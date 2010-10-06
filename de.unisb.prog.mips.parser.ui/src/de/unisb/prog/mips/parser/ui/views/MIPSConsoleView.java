package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class MIPSConsoleView extends ViewPart {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.MIPSConsoleView";
	
	private Text text;

	public MIPSConsoleView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}

}

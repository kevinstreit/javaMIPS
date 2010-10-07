package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.parser.ui.launching.ExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.MIPSCore;
import de.unisb.prog.mips.parser.ui.util.TextOutputStream;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSConsoleView extends ViewPart implements ExecutionListener {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.MIPSConsoleView";
	
	private static FontRegistry fonts;
	static {
		fonts = new FontRegistry(PlatformUI.getWorkbench().getDisplay());
		fonts.put("code", new FontData[]{new FontData("Courier New", 10, SWT.NORMAL)});
	}
	
	private Text text;

	public MIPSConsoleView() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
		text.setFont(fonts.get("code"));
		MIPSCore.getInstance().setConsoleOut(new TextOutputStream(text));
		MIPSCore.getInstance().addExecutionListener(this);
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
	}

	@Override
	public void execPaused(Sys sys) {
		
	}

	@Override
	public void execStepped(Sys sys) {
		
	}

	@Override
	public void execFinished(Sys sys) {
		
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		
	}

	
}

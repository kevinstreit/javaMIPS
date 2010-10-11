package de.unisb.prog.mips.parser.ui.util;

import java.io.PrintStream;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

public class MIPSConsoleOutput extends PrintStream {
	
	private final StyledText textField;
	
	static {
		JFaceResources.getColorRegistry().put("m_red", new RGB(255, 0, 0));
		JFaceResources.getColorRegistry().put("m_white", new RGB(255, 255, 255));
	}
	
	public MIPSConsoleOutput(StyledText textField) {
		super(System.out);
		this.textField = textField;
	}

	@Override
	public void print(final String s) {
		print(s, false);
	}
	
	public void print(final String s, final boolean debug) {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				textField.append(s);
				
				if (debug)
					textField.setStyleRange(
						new StyleRange(
							textField.getText().length() - s.length(), 
							s.length(), 
							JFaceResources.getColorRegistry().get("m_red"), 
							JFaceResources.getColorRegistry().get("m_white")
						)
					);
			}
		});
	}
	
	@Override
	public void print(Object obj) {
		print(obj.toString(), false);
	}
	
	public void print(Object obj, boolean debug) {
		print(obj.toString(), debug);
	}
	
	@Override
	public void print(char c) {
		print("" + c, false);
	}
	
	public void print(char c, boolean debug) {
		print("" + c, debug);
	}
	
	@Override
	public void print(int c) {
		print("" + c, false);
	}
	
	public void print(int c, boolean debug) {
		print("" + c, debug);
	}
	
	@Override
	public void println(String x) {
		print(x + "\n", false);
	}
	
	public void println(String x, boolean debug) {
		print(x + "\n", debug);
	}
	
	public void clear() {
		textField.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				textField.setText("");
				textField.setStyleRange(null);
			}
		});
	}
	
}

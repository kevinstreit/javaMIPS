package de.unisb.prog.mips.parser.ui.util;

import java.io.PrintStream;

import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class TextOutputStream extends PrintStream {
	
	private final Text textField;
	
	public TextOutputStream(Text textField) {
		super(System.out);
		this.textField = textField;
	}

	@Override
	public void print(final String s) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				textField.append(s);
			}
		});
	}
	
	@Override
	public void print(Object obj) {
		print(obj.toString());
	}
	
	@Override
	public void print(char c) {
		print("" + c);
	}
	
	@Override
	public void print(int c) {
		print("" + c);
	}
	
	@Override
	public void println(String x) {
		print(x + "\n");
	}
}

package de.unisb.prog.mips.parser.ui.util;

import java.io.PrintStream;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.PlatformUI;

import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class MIPSConsoleOutput extends PrintStream {

	private final StyledText textField;

	public boolean isPrintableChar( char c ) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
		return !Character.isISOControl(c) &&
		block != null &&
		block != Character.UnicodeBlock.SPECIALS;
	}

	private class InputListener implements VerifyKeyListener {
		private StringBuffer buffer = new StringBuffer();

		public void verifyKey(VerifyEvent event) {
			if (editMaxLength == -1) {
				event.doit = false;
				return;
			}

			switch (event.keyCode) {
			case 13 : { // enter
				input = buffer.toString();
				buffer.setLength(0);
				return;
			}
			case 8 : { // backspace
				if (buffer.length() > 0) {
					buffer.setLength(buffer.length() - 1);
					return;
				}
			}
			default: {
				if (isPrintableChar(event.character) && buffer.length() < editMaxLength) {
					buffer.append(event.character);
					return;
				}
			}
			}

			event.doit = false;
		}

		public void reset() {
			buffer.setLength(0);
		}
	}

	private InputListener inputListener = new InputListener();

	private int editMaxLength = -1;
	private String input = null;

	static {
		JFaceResources.getColorRegistry().put("m_red", new RGB(255, 0, 0));
		JFaceResources.getColorRegistry().put("m_white", new RGB(255, 255, 255));
	}

	public MIPSConsoleOutput(StyledText textField) {
		super(System.out);
		this.textField = textField;
		textField.setEditable(true);
		textField.addVerifyKeyListener(inputListener);
	}

	public synchronized String startInput(int maxLength) {
		if (maxLength <= 0)
			throw new IllegalArgumentException("No length less than 1 is allowed");

		if (editMaxLength != -1 || input != null)
			throw new IllegalStateException("Console is already in input mode");

		editMaxLength = maxLength;
		inputListener.reset();

		Sys sys = MIPSCore.getInstance().getSys();
		Processor proc = sys != null ? sys.getProcessor() : null;

		String val = "\0";

		if (proc != null) {
			while (input == null && proc.state == ExecutionState.RUNNING) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// Nothing
				}
			}

			val = input;
		}

		editMaxLength = -1;
		input = null;

		return val;
	}

	@Override
	public void print(final String s) {
		print(s, false);
	}

	public void print(final String s, final boolean debug) {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				if (!textField.isDisposed()) {
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

					textField.setSelection(textField.getText().length());
				}
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
		if (!textField.isDisposed()) {
			textField.getDisplay().syncExec(new Runnable() {
				public void run() {
					textField.setText("");
					textField.setStyleRange(null);
				}
			});
		}
	}

}

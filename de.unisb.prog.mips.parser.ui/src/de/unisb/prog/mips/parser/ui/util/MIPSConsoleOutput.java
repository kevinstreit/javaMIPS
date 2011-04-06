package de.unisb.prog.mips.parser.ui.util;

import java.io.PrintStream;
import java.util.ArrayList;

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

	private ArrayList<String> messages = new ArrayList<String>();
	final int[] textPos = new int[1];

	private Thread refreshingThread = new Thread(new Runnable() {
		public void run() {
			try {
				final ArrayList<String> taken = new ArrayList<String>();
				while (true) {
					taken.clear();

					synchronized (messages) {
						while (messages.isEmpty())
							messages.wait();
					}

					Thread.sleep(50);

					synchronized (messages) {
						taken.addAll(messages);
						messages.clear();
					}

					final StringBuilder buf = new StringBuilder();
					final ArrayList<StyleRange> styles = new ArrayList<StyleRange>();
					for (String s : taken) {
						if (s.charAt(0) == '1') {
							StyleRange range = new StyleRange(
								textPos[0] + buf.length(),
								s.length() - 1,
								JFaceResources.getColorRegistry().get("m_red"),
								JFaceResources.getColorRegistry().get("m_white")
							);
							styles.add(range);
						}
						buf.append(s.substring(1));
					}

					PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
						public void run() {
							if (!textField.isDisposed()) {
								textField.append(buf.toString());

								for (StyleRange style : styles)
									textField.setStyleRange(style);

								textPos[0] += buf.length();
								textField.setSelection(textPos[0]);
							}
						}
					});
				}
			} catch (InterruptedException e) {
				// Nothing to do
			}
		}
	});
	private final StyledText textField;

	private void addOutput(String s) {
		synchronized (messages) {
			messages.add(s);
			messages.notify();
		}
	}

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
				++textPos[0];
				return;
			}
			case 8 : { // backspace
				if (buffer.length() > 0) {
					buffer.setLength(buffer.length() - 1);
					--textPos[0];
					return;
				}
			}
			default: {
				if (isPrintableChar(event.character) && buffer.length() < editMaxLength) {
					buffer.append(event.character);
					++textPos[0];
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
		refreshingThread.start();
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
			MIPSCore.getInstance().inputModeStarted();

			while (input == null && proc.state == ExecutionState.RUNNING) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// Nothing
				}
			}

			MIPSCore.getInstance().inputModeDone();

			val = input;
		}

		editMaxLength = -1;
		input = null;

		return val;
	}

	@Override
	public void print(final String s) {
		addOutput("0" + s);
	}

	public void print(final String s, final boolean debug) {
		addOutput((debug ? "1" : "0") + s);
	}

	@Override
	public void print(Object obj) {
		addOutput("0" + obj.toString());
	}

	public void print(Object obj, boolean debug) {
		addOutput((debug ? "1" : "0") + obj.toString());
	}

	@Override
	public void print(char c) {
		addOutput("0" + c);
	}

	public void print(char c, boolean debug) {
		addOutput((debug ? "1" : "0") + c);
	}

	@Override
	public void print(int c) {
		addOutput("0" + c);
	}

	public void print(int c, boolean debug) {
		addOutput((debug ? "1" : "0") + c);
	}

	@Override
	public void println(String x) {
		addOutput("0" + x + "\n");
	}

	public void println(String x, boolean debug) {
		addOutput((debug ? "1" : "0") + x + "\n");
	}

	public void clear() {
		synchronized (messages) {
			if (!textField.isDisposed()) {
				textField.getDisplay().syncExec(new Runnable() {
					public void run() {
						textField.setText("");
						textField.setStyleRange(null);
						textPos[0] = 0;
					}
				});
			}
			messages.clear();
		}
	}

	public void leaveInputMode() {
		editMaxLength = -1;
		input = null;
	}

}

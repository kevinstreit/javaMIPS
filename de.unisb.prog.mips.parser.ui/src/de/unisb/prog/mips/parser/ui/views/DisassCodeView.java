package de.unisb.prog.mips.parser.ui.views;

import java.io.IOException;
import java.util.TreeMap;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PlatformUI;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.insn.Disassembler;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.launching.IExecutionListener;
import de.unisb.prog.mips.parser.ui.util.MarkerUtil;
import de.unisb.prog.mips.simulator.MemDumpFormatter;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.simulator.Type;

public class DisassCodeView extends DisassemblyView implements IExecutionListener {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.DisassCodeView";
	public static final String HIGHLIGHT = ID + ".highlight";
	public static final String WHITE = ID + ".white";

	public static IPartListener2 highlightDeletionListener = null;
	private TreeMap<Integer, DisAssLine> code = new TreeMap<Integer, DisassCodeView.DisAssLine>();

	static {
		JFaceResources.getColorRegistry().put(HIGHLIGHT, new RGB(236, 215, 238));
		JFaceResources.getColorRegistry().put(WHITE, new RGB(255, 255, 255));
	}

	public DisassCodeView() {
		super(false);
	}

	private class DisAssLine {
		boolean highlighted = false;

		final int addr;
		final int data;
		final String disass;

		public DisAssLine(int addr, int data, String disass) {
			this.addr = addr;
			this.data = data;
			this.disass = disass;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			v.refresh();
		}

		public void dispose() {

		}

		public Object[] getElements(Object parent) {
			if (parent == code) {
				return code.values().toArray(new DisAssLine[code.size()]);
			}
			return new Object[0];
		}
	}

	class ViewLabelProvider extends StyledCellLabelProvider {
		private final Font regFont;

		public ViewLabelProvider(final Font regFont) {
			this.regFont = regFont;
		}

		@Override
		public void update(ViewerCell cell) {
			cell.setFont(regFont);
			if (cell.getElement() instanceof DisAssLine) {
				DisAssLine line = (DisAssLine) cell.getElement();

				switch (cell.getColumnIndex()) {
				case 0:
					cell.setText(String.format("%08x", line.addr));
					break;
				case 1:
					cell.setText(String.format("%08x", line.data));
					break;
				case 2:
					cell.setText(line.disass);
					break;
				}

				if (line.highlighted) {
					cell.setBackground(JFaceResources.getColorRegistry().get(HIGHLIGHT));
				} else {
					cell.setBackground(null);
				}
			}
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				if (DisassCodeView.this.sys != null && DisassCodeView.this.asm != null && event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection sel = (IStructuredSelection) event.getSelection();
					for (Object obj : sel.toArray()) {
						if (obj instanceof DisAssLine) {
							DisAssLine line = (DisAssLine) obj;
							Position pos = DisassCodeView.this.asm.getPosition(line.addr);
							MarkerUtil.cleanAllMarkers(MarkerUtil.ID_Highlighting);
							MarkerUtil.markPosition(pos, MarkerUtil.ID_Highlighting, true, false);
						}
					}
				}
			}
		});

		highlightDeletionListener = new IPartListener2() {
			public void partVisible(IWorkbenchPartReference partRef) {}
			public void partOpened(IWorkbenchPartReference partRef) {}
			public void partInputChanged(IWorkbenchPartReference partRef) {}
			public void partHidden(IWorkbenchPartReference partRef) {}
			public void partClosed(IWorkbenchPartReference partRef) {}
			public void partBroughtToTop(IWorkbenchPartReference partRef) {}
			public void partActivated(IWorkbenchPartReference partRef) {}
			public void partDeactivated(IWorkbenchPartReference partRef) {
				if (partRef.getPart(false) == DisassCodeView.this) {
					MarkerUtil.cleanAllMarkers(MarkerUtil.ID_Highlighting);
				}
			}
		};

		getViewSite().getPage().addPartListener(highlightDeletionListener);

		MIPSCore.getInstance().addExecutionListener(this);
	}

	@Override
	public IStructuredContentProvider getContentProvider() {
		return new ViewContentProvider();
	}

	@Override
	public StyledCellLabelProvider getLabelProvider() {
		return new ViewLabelProvider(JFaceResources.getTextFont());
	}

	@Override
	protected void createColumns() {
		String[] titles = { "Address", "Hex", "Disassembly" };
		int[] bounds = { 80, 80, 300 };

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(false);
			column.getColumn().setAlignment(SWT.LEFT);
		}

		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	@Override
	protected Object getViewerInput() {
		if (asm == null || sys == null)
			return null;

		code.clear();

		try {
			asm.getText().dump(code, sys.getMemory(), new MemDumpFormatter<TreeMap<Integer, DisAssLine>>() {
				public Type granularity() { return Type.WORD; }
				public int chunkSize() { return 1; }
				public void emit(TreeMap<Integer, DisAssLine> output, int addr, int[] data) throws IOException {
					output.put(addr, new DisAssLine(addr, data[0], Disassembler.INSTANCE.disasm(data[0])));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return code;
	}

	@Override
	public void assemblyReset() {
		super.assemblyReset();
		highlight(null);
		code.clear();
		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_Highlighting);
	}

	@Override
	public void dispose() {
		getViewSite().getPage().removePartListener(highlightDeletionListener);
		MIPSCore.getInstance().removeExecutionListener(this);
		super.dispose();
	}

	private DisAssLine highlighted = null;

	private void highlight(final DisAssLine line) {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				if (highlighted != null) {
					highlighted.highlighted = false;
					viewer.update(highlighted, null);
				}

				highlighted = line;

				if (highlighted != null) {
					highlighted.highlighted = true;
					viewer.update(highlighted, null);
					viewer.reveal(highlighted);
				}
			}
		});
	}

	public void execStarted(Sys sys, Assembly asm) {
		highlight(null);
	}

	public void execPaused(Sys sys, Assembly asm) {
		int pc = sys.getProcessor().pc;
		DisAssLine line = code.get(pc);
		highlight(line);
	}

	public void execContinued(Sys sys, Assembly asm) {
		highlight(null);
	}

	public void execStepped(Sys sys, Assembly asm) {
		int pc = sys.getProcessor().pc;
		DisAssLine line = code.get(pc);
		highlight(line);
	}

	public void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		highlight(null);
	}

	public void inputModeStarted() {
		// Nothing
	}

	public void inputModeDone() {
		// Nothing
	}

	public void dbgBrkptReached(Sys sys, Assembly asm) {
		// done in execPaused
	}

}

package de.unisb.prog.mips.parser.ui.views;

import java.io.IOException;
import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.insn.Disassembler;
import de.unisb.prog.mips.parser.ui.util.MarkerUtil;
import de.unisb.prog.mips.simulator.MemDumpFormatter;
import de.unisb.prog.mips.simulator.Type;

public class DisassCodeView extends DisassemblyView {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.DisassCodeView";

	public DisassCodeView() {
		super(false);
	}

	private class DisAssLine {
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
		@Override
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			v.refresh();
		}

		@Override
		public void dispose() {

		}

		@Override
		public Object[] getElements(Object parent) {
			if (parent instanceof DisAssLine[]) {
				return (DisAssLine[]) parent;
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
			cell.setFont(this.regFont);
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
			}
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		this.viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
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
		String[] titles = { "Addr.", "Hex", "Disassembly" };
		int[] bounds = { 80, 80, 300 };

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(this.viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(false);
			column.getColumn().setAlignment(SWT.LEFT);
		}

		Table table = this.viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	@Override
	protected Object getViewerInput() {
		if (this.asm == null || this.sys == null)
			return null;

		ArrayList<DisAssLine> code = new ArrayList<DisAssLine>();

		try {
			this.sys.getMemory().dump(code, this.sys.textStart(), this.asm.getText().size(), new MemDumpFormatter<ArrayList<DisAssLine>>() {
				@Override public Type granularity() { return Type.WORD; }
				@Override public int chunkSize() { return 1; }
				@Override
				public void emit(ArrayList<DisAssLine> output, int addr, int[] data) throws IOException {
					output.add(new DisAssLine(addr, data[0], Disassembler.INSTANCE.disasm(data[0])));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return code.toArray(new DisAssLine[code.size()]);
	}

	@Override
	public void assemblyReset() {
		super.assemblyReset();
		MarkerUtil.cleanAllMarkers(MarkerUtil.ID_Highlighting);
	}

}

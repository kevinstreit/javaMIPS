package de.unisb.prog.mips.parser.ui.views;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Table;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.insn.Disassembler;
import de.unisb.prog.mips.simulator.MemDumpFormatter;
import de.unisb.prog.mips.simulator.Sys;
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
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			v.refresh();
		}
		
		public void dispose() {
			
		}
		
		public Object[] getElements(Object parent) {
			if (parent instanceof DisAssLine[]) {
				return (DisAssLine[]) parent;
			}
			return new Object[0];
		}
	}
	
	class ViewLabelProvider extends StyledCellLabelProvider {
		private Font regFont;
		
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
			}
		}
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
	protected void createColumns(TableViewer viewer) {
		String[] titles = { "Addr.", "Hex", "Disassembly" };
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
	protected Object getViewerInput(Assembly asm, Sys sys) {
		if (asm == null || sys == null)
			return null;
		
		ArrayList<DisAssLine> code = new ArrayList<DisAssLine>();
		
		try {
			sys.getMemory().dump(code, sys.textStart(), asm.getText().size(), new MemDumpFormatter<ArrayList<DisAssLine>>() {
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

}

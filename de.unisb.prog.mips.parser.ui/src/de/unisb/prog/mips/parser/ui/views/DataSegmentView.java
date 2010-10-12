package de.unisb.prog.mips.parser.ui.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
import de.unisb.prog.mips.simulator.MemDumpFormatter;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.simulator.Type;

public class DataSegmentView extends DisassemblyView {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.DataSegmentView";

	public DataSegmentView() {
		super(false);
	}
	
	private class DataSegLine {
		final int addr;
		final int[] data;
		
		public DataSegLine(int addr, int[] data) {
			this.addr = addr;
			this.data = data;
		}
	}
	
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			v.refresh();
		}
		
		public void dispose() {
			
		}
		
		public Object[] getElements(Object parent) {
			if (parent instanceof DataSegLine[]) {
				return (DataSegLine[]) parent;
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
			if (cell.getElement() instanceof DataSegLine) {
				DataSegLine line = (DataSegLine) cell.getElement();
				
				switch (cell.getColumnIndex()) {
				case 0: 
					cell.setText(String.format("%08x", line.addr));
					break;
				case 1: {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < line.data.length; i++)
						sb.append(String.format("%02x ", line.data[i]));
					cell.setText(sb.toString());
					break;
				}
				case 2: {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < line.data.length; i++) {
						char ch = (char) line.data[i];
						sb.append(String.format("%c", Character.isLetterOrDigit(ch) ? ch : '.'));
					}
					cell.setText(sb.toString());
					break;
				}
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
		String[] titles = { "Line", "Content (Hex)", "Content (ASCII)" };
		int[] bounds = { 80, 380, 140 };

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
		
		final ArrayList<DataSegLine> datalines = new ArrayList<DataSegLine>();
		
		try {
			sys.getMemory().dump(datalines, sys.dataStart(), asm.getData().size(), new MemDumpFormatter<ArrayList<DataSegLine>>() {
				@Override public Type granularity() { return Type.BYTE; }
				@Override public int chunkSize() { return 16; }
				@Override
				public void emit(ArrayList<DataSegLine> output, int addr, int[] data) throws IOException {
					output.add(new DataSegLine(addr, Arrays.copyOf(data, data.length)));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return datalines.toArray(new DataSegLine[datalines.size()]);
	}
}

package de.unisb.prog.mips.parser.ui.views;


import java.util.Arrays;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.parser.ui.launching.ExecutionListener;
import de.unisb.prog.mips.parser.ui.launching.MIPSCore;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;

public class RegisterView extends ViewPart implements ExecutionListener {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.RegisterView";

	private TableViewer viewer;
	private Sys system = null;
	private int[] lastRegValues = new int[Reg.values().length];
	private boolean[] regChanged = new boolean[Reg.values().length];

	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			
		}
		
		public void dispose() {
			
		}
		
		public Object[] getElements(Object parent) {
			return Reg.values();
		}
	}
	
	class ViewLabelProvider extends StyledCellLabelProvider {
		private Font regFont, changedFont;
		
		public ViewLabelProvider(final Font regFont, final Font changedFont) {
			this.regFont = regFont;
			this.changedFont = changedFont;
		}
		
		@Override
		public void update(ViewerCell cell) {
			if (cell.getElement() instanceof Reg) {
				Reg reg = (Reg) cell.getElement();
				Processor proc = (system == null) ? null : system.getProcessor();
				cell.setFont(regChanged[reg.ordinal()] ? changedFont : regFont);
				
				switch (cell.getColumnIndex()) {
				case 0:
					cell.setText(reg.name());
					break;
				case 1:
					if (system == null || system.getProcessor().state == ExecutionState.RUNNING)
						cell.setText("---");
					else {
						int val = proc.gp[reg.ordinal()];
						cell.setText("" + val);
					}
					break;
				case 2:
					if (system == null || system.getProcessor().state == ExecutionState.RUNNING)
						cell.setText("---");
					else {
						int val = proc.gp[reg.ordinal()];
						cell.setText(String.format("0x%08x", val));
					}
					break;
				}
			}
		}
		
		@Override
		public String getToolTipText(Object element) {
			if (element instanceof Reg) {
				Reg reg = (Reg) element;
				return reg.description;
			} else {
				return null;
			}
		}
	}
	
	class NameSorter extends ViewerSorter {
	
	}

	public RegisterView() {
	
	}
	
	private void createColumns(TableViewer viewer) {
		String[] titles = { "Reg", "Dec", "Hex"};
		int[] bounds = { 50, 80, 80 };

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(false);
			column.getColumn().setAlignment(SWT.RIGHT);
		}
		
		Table table = viewer.getTable();
		table.setFont(JFaceResources.getTextFont());
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	public void createPartControl(Composite parent) {
		
		viewer = new TableViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		ColumnViewerToolTipSupport.enableFor(viewer);
		Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.TEXT_FONT);
		Font regFont = JFaceResources.getTextFont();
		viewer.getTable().setFont(regFont);
		
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider(regFont, boldFont));
		//viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "de.unisb.prog.mips.parser.ui.viewer");
		
		MIPSCore.getInstance().addExecutionListener(this);
		
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	// Execution Event Handling
	
	@Override
	public void dispose() {
		MIPSCore.getInstance().removeExecutionListener(this);
	}

	@Override
	public void execStarted(Sys sys) {
		Arrays.fill(regChanged, false);
		for (Reg r : Reg.values())
			lastRegValues[r.ordinal()] = sys.getProcessor().gp[r.ordinal()];
		this.system = sys;
	}

	@Override
	public void execPaused(Sys sys) {
		for (Reg r : Reg.values()) {
			int oldVal = lastRegValues[r.ordinal()];
			int newVal = sys.getProcessor().gp[r.ordinal()];
			
			if (oldVal != newVal) {
				regChanged[r.ordinal()] = true;
				lastRegValues[r.ordinal()] = sys.getProcessor().gp[r.ordinal()];
			}
		}
		this.viewer.refresh();
		Arrays.fill(regChanged, false);
	}

	@Override
	public void execStepped(Sys sys) {
		for (Reg r : Reg.values()) {
			int oldVal = lastRegValues[r.ordinal()];
			int newVal = sys.getProcessor().gp[r.ordinal()];
			
			if (oldVal != newVal) {
				regChanged[r.ordinal()] = true;
				lastRegValues[r.ordinal()] = newVal;
			}
		}
		this.viewer.refresh();
		Arrays.fill(regChanged, false);
	}

	@Override
	public void execFinished(Sys sys) {
		for (Reg r : Reg.values()) {
			int oldVal = lastRegValues[r.ordinal()];
			int newVal = sys.getProcessor().gp[r.ordinal()];
			
			if (oldVal != newVal) {
				regChanged[r.ordinal()] = true;
				lastRegValues[r.ordinal()] = sys.getProcessor().gp[r.ordinal()];
			}
		}
		this.viewer.refresh();
		Arrays.fill(regChanged, false);
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		// this is done in execPaused
	}
}
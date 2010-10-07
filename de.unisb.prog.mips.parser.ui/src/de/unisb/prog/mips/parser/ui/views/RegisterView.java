package de.unisb.prog.mips.parser.ui.views;


import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
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

	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			
		}
		
		public void dispose() {
			
		}
		
		public Object[] getElements(Object parent) { 
			return Reg.values();
		}
	}
	
	class ViewLabelProvider extends CellLabelProvider {
		@Override
		public void update(ViewerCell cell) {
			if (cell.getElement() instanceof Reg) {
				Reg reg = (Reg) cell.getElement();
				
				switch (cell.getColumnIndex()) {
				case 0:
					cell.setText(reg.name());
					break;
				case 1:
					if (system == null || system.getProcessor().state == ExecutionState.RUNNING)
						cell.setText("---");
					else {
						Processor proc = system.getProcessor();
						cell.setText("" + proc.gp[reg.ordinal()]);
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
		String[] titles = { "Register", "Value"};
		int[] bounds = { 60, 140 };

		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(false);
		}
		
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}


	public void createPartControl(Composite parent) {
		
		viewer = new TableViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		ColumnViewerToolTipSupport.enableFor(viewer);
		
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
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
		this.system = sys;
	}

	@Override
	public void execPaused(Sys sys) {
		this.viewer.refresh();
	}

	@Override
	public void execStepped(Sys sys) {
		this.viewer.refresh();
	}

	@Override
	public void execFinished(Sys sys) {
		this.viewer.refresh();
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		this.viewer.refresh();
	}
}
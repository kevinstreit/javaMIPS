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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.assembler.Assembly;
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
	private int[] lastPRegValues = new int[PReg.values().length];
	private boolean[] regChanged = new boolean[Reg.values().length];
	private boolean[] pregChanged = new boolean[PReg.values().length];
	
	private enum PReg {
		pc ("The program counter"), 
		lo ("low part of multiplication / division"), 
		hi ("high  part of multiplication / division"); 
		
		public final String description;
		PReg(String desc) {
			this.description = desc;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
			
		}
		
		public void dispose() {
			
		}
		
		public Object[] getElements(Object parent) {
			int gpSize = Reg.values().length;
			Object[] objects = new Object[Reg.values().length + 3];
			System.arraycopy(Reg.values(), 0, objects, 0, Reg.values().length);
			objects[gpSize] = PReg.pc;
			objects[gpSize+1] = PReg.lo;
			objects[gpSize+2] = PReg.hi;
			return objects;
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
			Processor proc = (system == null) ? null : system.getProcessor();
			
			if (cell.getElement() instanceof Reg) {
				Reg reg = (Reg) cell.getElement();
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
			} else if (cell.getElement() instanceof PReg) {
				int val = -1;
				PReg reg = (PReg) cell.getElement();
				cell.setFont(pregChanged[reg.ordinal()] ? changedFont : regFont);
				
				if (system != null) {
					switch (reg) {
					case pc:
						val = system.getProcessor().pc;
						break;
					case hi:
						val = system.getProcessor().hi;
						break;
					case lo:
						val = system.getProcessor().lo;
						break;
					}
				}
				
				switch (cell.getColumnIndex()) {
				case 0:
					cell.setText(reg.name());
					break;
				case 1:
					if (system == null || system.getProcessor().state == ExecutionState.RUNNING)
						cell.setText("---");
					else {
						cell.setText("" + val);
					}
					break;
				case 2:
					if (system == null || system.getProcessor().state == ExecutionState.RUNNING)
						cell.setText("---");
					else {
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
			} else if (element instanceof PReg) {
				PReg reg = (PReg) element;
				return reg.description;
			} else {
				return null;
			}
		}
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
		viewer.setInput(getViewSite());

		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "de.unisb.prog.mips.parser.ui.viewer");
		
		MIPSCore.getInstance().addExecutionListener(this);
		
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	@Override
	public void dispose() {
		MIPSCore.getInstance().removeExecutionListener(this);
	}
	
	private void resetChangedRegs(Sys sys) {
		Arrays.fill(regChanged, false);
		Arrays.fill(pregChanged, false);
		
		Processor proc = sys.getProcessor();
		
		for (Reg r : Reg.values())
			lastRegValues[r.ordinal()] = sys.getProcessor().gp[r.ordinal()];
		lastPRegValues[PReg.pc.ordinal()] = proc.pc;
		lastPRegValues[PReg.hi.ordinal()] = proc.hi;
		lastPRegValues[PReg.lo.ordinal()] = proc.lo;
	}

	private void checkChangedRegs(Sys sys) {
		Processor proc = sys.getProcessor();
		
		for (Reg r : Reg.values()) {
			int oldVal = lastRegValues[r.ordinal()];
			int newVal = proc.gp[r.ordinal()];
			regChanged[r.ordinal()] = oldVal != newVal;
		}
		
		pregChanged[PReg.pc.ordinal()] = lastPRegValues[PReg.pc.ordinal()] != proc.pc;
		pregChanged[PReg.lo.ordinal()] = lastPRegValues[PReg.lo.ordinal()] != proc.lo;
		pregChanged[PReg.hi.ordinal()] = lastPRegValues[PReg.hi.ordinal()] != proc.hi;
	}
	
	// Execution Event Handling

	@Override
	public void execStarted(Sys sys, Assembly asm) {
		this.system = sys;
		resetChangedRegs(sys);
	}

	@Override
	public void execPaused(Sys sys, Assembly asm) {
		checkChangedRegs(sys);
		
		this.viewer.getTable().getDisplay().syncExec(new Runnable() {	
			@Override
			public void run() {
				viewer.refresh();
			}
		});
		
		resetChangedRegs(sys);
	}
	
	@Override
	public void execContinued(Sys sys, Assembly asm) {
		execStarted(sys, asm); // Does the same
	}

	@Override
	public void execStepped(Sys sys, Assembly asm) {
		execPaused(sys, asm); // Does the same
	}

	@Override
	public void execFinished(Sys sys, Assembly asm, boolean interrupted) {
		execPaused(sys, asm); // Does the same
	}

	@Override
	public void dbgBrkptReached(Sys sys, Assembly asm) {
		// this is done in execPaused
	}
}
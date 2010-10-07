package de.unisb.prog.mips.parser.ui.views;


import java.util.Arrays;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISharedImages;
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
	
	class ViewLabelProvider extends CellLabelProvider {
		private Styler boldStyler;
		private Image regChangedImg;
		
		public ViewLabelProvider(final Font boldFont) {
			boldStyler = new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					textStyle.font = boldFont;
				}
			};
			
			regChangedImg = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK).createImage();
		}
		
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
						StyledString sStr = new StyledString();
						String val = "" + proc.gp[reg.ordinal()];
						
						if (regChanged[reg.ordinal()]) {
							sStr.append(val, boldStyler);
							regChanged[reg.ordinal()] = false;
							cell.setImage(regChangedImg);
						} else {
							sStr.append(val);
							cell.setImage(null);
						}
						
						cell.setText(sStr.getString());
						cell.setStyleRanges(sStr.getStyleRanges());
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
		
		@Override
		public void dispose() {
			regChangedImg.dispose();
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

	private static FontData[] getModifiedFontData(FontData[] originalData, int additionalStyle) {
		FontData[] styleData = new FontData[originalData.length];
		for (int i = 0; i < styleData.length; i++) {
			FontData base = originalData[i];
			styleData[i] = new FontData(base.getName(), base.getHeight(), base.getStyle() | additionalStyle);
		}
       	return styleData;
    }

	public void createPartControl(Composite parent) {
		
		viewer = new TableViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		createColumns(viewer);
		ColumnViewerToolTipSupport.enableFor(viewer);
		FontData[] boldFontData = getModifiedFontData(viewer.getControl().getFont().getFontData(), SWT.BOLD);
		Font boldFont = new Font(Display.getCurrent(), boldFontData);
		
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider(boldFont));
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
	}

	@Override
	public void dbgBrkptReached(Sys sys) {
		// this is done in execPaused
	}
}
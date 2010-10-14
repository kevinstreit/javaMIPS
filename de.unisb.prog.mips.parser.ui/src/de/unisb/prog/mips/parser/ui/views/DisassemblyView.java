package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.launching.IAssemblyLoadListener;
import de.unisb.prog.mips.simulator.Sys;

public abstract class DisassemblyView extends ViewPart implements IAssemblyLoadListener {
	protected TableViewer viewer;
	private final boolean needTooltipSupport;

	protected Assembly asm = null;
	protected Sys sys = null;

	public DisassemblyView(boolean needTooltipSupport) {
		this.needTooltipSupport = needTooltipSupport;
	}

	protected abstract void createColumns();
	protected abstract IStructuredContentProvider getContentProvider();
	protected abstract StyledCellLabelProvider getLabelProvider();
	protected abstract Object getViewerInput();

	@Override
	public void createPartControl(Composite parent) {
		this.viewer = new TableViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
		createColumns();
		Font regFont = JFaceResources.getTextFont();
		this.viewer.getTable().setFont(regFont);
		if (this.needTooltipSupport)
			ColumnViewerToolTipSupport.enableFor(this.viewer);

		this.viewer.setContentProvider(getContentProvider());
		this.viewer.setLabelProvider(getLabelProvider());
		this.viewer.setInput(null);

		MIPSCore.getInstance().addAssemblyLoadListener(this);

		Sys tsys = MIPSCore.getInstance().getSys();
		Assembly tasm = MIPSCore.getInstance().getAsm();

		if (tsys != null && tasm != null) {
			assemblyLoaded(tasm, tsys);
		}
	}

	@Override
	public void setFocus() {
		this.viewer.refresh();
	}

	@Override
	public void dispose() {
		MIPSCore.getInstance().removeAssemblyLoadListener(this);
	}

	public void assemblyLoaded(Assembly asm, Sys sys) {
		if (sys == null || asm == null)
			throw new IllegalArgumentException("Sys and asm must not be null!");

		this.asm = asm;
		this.sys = sys;

		this.viewer.setInput(getViewerInput());
	}

	public void assemblyReset() {
		this.asm = null;
		this.sys = null;
		this.viewer.setInput(null);
	}

}

package de.unisb.prog.mips.parser.ui.views;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

import de.unisb.prog.mips.doc.Documentation;
import de.unisb.prog.mips.doc.InsnDoc;
import de.unisb.prog.mips.doc.InsnDocGroup;

public class ISAView extends ViewPart {
	public static final String ID = "de.unisb.prog.mips.parser.ui.views.ISAView";

	private TreeViewer viewer;

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

		public Object[] getElements(Object inputElement) {
			return Documentation.getInsnDocumentation();
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof InsnDocGroup)
				return ((InsnDocGroup) parentElement).getInsns();

			return null;
		}

		public Object getParent(Object element) {
			if (element instanceof InsnDoc)
				return ((InsnDoc) element).parentGroup;

			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof InsnDocGroup)
				return ((InsnDocGroup) element).getInsns().length > 0;

				return false;
		}

	}

	class ViewLabelProvider extends StyledCellLabelProvider {
		private final Styler boldStyler;

		public ViewLabelProvider(final Font boldFont) {
			this.boldStyler = new Styler() {
				@Override
				public void applyStyles(TextStyle textStyle) {
					textStyle.font = boldFont;
				}
			};
		}

		@Override
		public void update(ViewerCell cell) {
			if (cell.getElement() instanceof InsnDoc) {
				cell.setText(cell.getElement().toString());
			} else if (cell.getElement() instanceof InsnDocGroup) {
				StyledString str = new StyledString();
				str.append(cell.getElement().toString(), this.boldStyler);
				cell.setText(str.toString());
				cell.setStyleRanges(str.getStyleRanges());
			}
		}

		@Override
		public String getToolTipText(Object element) {
			if (element instanceof InsnDoc) {
				InsnDoc insn = (InsnDoc) element;
				return insn.getHelp();
			} else {
				return null;
			}
		}
	}

	class NameSorter extends ViewerSorter {

	}

	public ISAView() {

	}

	private static FontData[] getModifiedFontData(FontData[] originalData, int additionalStyle) {
		FontData[] styleData = new FontData[originalData.length];
		for (int i = 0; i < styleData.length; i++) {
			FontData base = originalData[i];
			styleData[i] = new FontData(base.getName(), base.getHeight(), base.getStyle() | additionalStyle);
		}
		return styleData;
	}

	@Override
	public void createPartControl(Composite parent) {
		this.viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		FontData[] boldFontData = getModifiedFontData(this.viewer.getControl().getFont().getFontData(), SWT.BOLD);
		Font boldFont = new Font(Display.getCurrent(), boldFontData);
		ColumnViewerToolTipSupport.enableFor(this.viewer);
		this.viewer.setContentProvider(new ViewContentProvider());
		this.viewer.setLabelProvider(new ViewLabelProvider(boldFont));
		this.viewer.setSorter(new NameSorter());
		this.viewer.setInput(getViewSite());
	}

	@Override
	public void setFocus() {
		this.viewer.getControl().setFocus();
	}

}

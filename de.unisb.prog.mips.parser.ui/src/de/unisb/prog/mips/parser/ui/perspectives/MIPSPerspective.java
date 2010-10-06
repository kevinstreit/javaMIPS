package de.unisb.prog.mips.parser.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.unisb.prog.mips.parser.ui.views.ISAView;
import de.unisb.prog.mips.parser.ui.views.MIPSConsoleView;
import de.unisb.prog.mips.parser.ui.views.RegisterView;

public class MIPSPerspective implements IPerspectiveFactory {

	private IPageLayout factory;

	public MIPSPerspective() {
		super();
	}

	public void createInitialLayout(IPageLayout factory) {
		this.factory = factory;
		addViews();
		addNewWizardShortcuts();
		addViewShortcuts();
	}

	private void addViews() {
		IFolderLayout topLeft =
			factory.createFolder(
				"topLeft", //NON-NLS-1
				IPageLayout.LEFT,
				0.2f,
				factory.getEditorArea());
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
		
		IFolderLayout topRight =
			factory.createFolder(
				"topRight", //NON-NLS-1
				IPageLayout.RIGHT,
				0.75f,
				factory.getEditorArea());
		topRight.addView(IPageLayout.ID_OUTLINE);
		
		IFolderLayout bottomLeft =
			factory.createFolder(
				"bottomLeft", //NON-NLS-1
				IPageLayout.BOTTOM,
				0.5f,
				"topLeft");
		bottomLeft.addView(ISAView.ID);
		
		IFolderLayout bottomRight =
			factory.createFolder(
				"bottomRight", //NON-NLS-1
				IPageLayout.BOTTOM,
				0.5f,
				"topRight");
		bottomRight.addView(RegisterView.ID);
		
		IFolderLayout bottomCenter =
			factory.createFolder(
				"bottomCenter", //NON-NLS-1
				IPageLayout.BOTTOM,
				0.6f,
				factory.getEditorArea());
		bottomCenter.addView(MIPSConsoleView.ID);
	}

	private void addNewWizardShortcuts() {
		factory.addNewWizardShortcut("de.unisb.prog.mips.parser.ui.mipsProjectWizard");//NON-NLS-1
		factory.addNewWizardShortcut("de.unisb.prog.mips.parser.ui.mipsFileWizard");//NON-NLS-1
	}

	private void addViewShortcuts() {
		factory.addShowViewShortcut(ISAView.ID);
		factory.addShowViewShortcut(RegisterView.ID);
		factory.addShowViewShortcut(MIPSConsoleView.ID);
		factory.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		factory.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		factory.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
	}

}

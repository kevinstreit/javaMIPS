package de.unisb.prog.mips.parser.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class NewMIPSFileWizard extends Wizard implements INewWizard {
	
	private WizardNewFileCreationPage pageOne;
	private IStructuredSelection sel;

	public NewMIPSFileWizard() {
		setWindowTitle("New MIPS File");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.sel = selection;
	}

	@Override
	public boolean performFinish() {
		IFile newFile = pageOne.createNewFile();
		
		if (newFile != null) {
			// TODO: Create sample content
			return true;
		}
		
		return false;
	}
	
	@Override
	public void addPages() {
		super.addPages();
		
		pageOne = new WizardNewFileCreationPage("New MIPS File", sel);
		pageOne.setFileExtension("mips");
		pageOne.setFileName("hello.mips");
		pageOne.setTitle("MIPS File");
		pageOne.setDescription("Create a new MIPS file.");
		
		pageOne.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/wzrdHd/newmipsfile_wiz.png"));
		
		addPage(pageOne);
	}

}

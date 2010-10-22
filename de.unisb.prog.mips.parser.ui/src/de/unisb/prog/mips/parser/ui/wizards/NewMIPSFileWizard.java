package de.unisb.prog.mips.parser.ui.wizards;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

public class NewMIPSFileWizard extends Wizard implements INewWizard {

	private class MIPSWizardNewFileCreationPage extends WizardNewFileCreationPage {
		public MIPSWizardNewFileCreationPage(String pageName, IStructuredSelection selection) {
			super(pageName, selection);
		}

		@Override
		protected InputStream getInitialContents() {
			return NewMIPSFileWizard.class.getResourceAsStream("/config/defaultFile.mips");
		}
	}

	private MIPSWizardNewFileCreationPage pageOne;
	private IStructuredSelection sel;

	public NewMIPSFileWizard() {
		setWindowTitle("New MIPS File");
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		sel = selection;
	}

	@Override
	public boolean performFinish() {
		IFile newFile = pageOne.createNewFile();

		if (newFile != null) {
			try {
				IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), newFile, false);
			} catch (PartInitException e) {
				// Nothing to do. The file was created anyway.
			}
			return true;
		}

		return false;
	}

	@Override
	public void addPages() {
		super.addPages();

		pageOne = new MIPSWizardNewFileCreationPage("New MIPS File", sel);
		pageOne.setFileExtension("mips");
		pageOne.setFileName("hello.mips");
		pageOne.setTitle("MIPS File");
		pageOne.setDescription("Create a new MIPS file.");

		pageOne.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/wzrdHd/newmipsfile_wiz.png"));

		addPage(pageOne);
	}

}

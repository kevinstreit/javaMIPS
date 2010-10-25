package de.unisb.prog.mips.parser.ui.wizards;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
			final IPath containerPath = getContainerFullPath();
			IPath newFilePath = containerPath.append(getFileName());
			final IFile newFileHandle = createFileHandle(newFilePath);

			IProject proj = newFileHandle.getProject();

			if (proj != null && proj.exists() && proj.isOpen()) {
				final boolean[] containsMIPSFile = new boolean[]{ false };

				try {
					proj.accept(new IResourceVisitor() {
						public boolean visit(IResource resource) throws CoreException {
							if (resource instanceof IFile && resource.getFileExtension().equals("mips"))
								containsMIPSFile[0] = true;

							return !containsMIPSFile[0];
						}
					});
				} catch (CoreException e) {
					containsMIPSFile[0] = false;
				}

				return containsMIPSFile[0] ? null : NewMIPSFileWizard.class.getResourceAsStream("/config/defaultFile.mips");
			}

			return null;
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

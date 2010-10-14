package de.unisb.prog.mips.parser.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.xtext.ui.XtextProjectHelper;

public class NewMIPSProjectWizard extends Wizard implements INewWizard, IExecutableExtension {

	private WizardNewProjectCreationPage pageOne;
	private IConfigurationElement configurationElement;

	public NewMIPSProjectWizard() {
		setWindowTitle("New MIPS Project");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {

	}

	@Override
	public boolean performFinish() {
		IProject newProject = this.pageOne.getProjectHandle();

		try {
			newProject.create(null);
			newProject.open(null);
		} catch (CoreException ex) {
			this.pageOne.setErrorMessage("Could not create the new project.");
			ex.printStackTrace();
			return false;
		}

		try {
			IProjectDescription projectDesc = newProject.getDescription();
			projectDesc.setNatureIds(new String[] {
					XtextProjectHelper.NATURE_ID
			});
			newProject.setDescription(projectDesc, null);
		} catch (CoreException ex) {
			this.pageOne.setErrorMessage("Could not add the MIPS Nature to the project.");
			ex.printStackTrace();
			return false;
		}

		BasicNewProjectResourceWizard.updatePerspective(this.configurationElement);

		return true;
	}

	@Override
	public void addPages() {
		super.addPages();

		this.pageOne = new WizardNewProjectCreationPage("New MIPS Project");
		this.pageOne.setTitle("MIPS Project");
		this.pageOne.setDescription("Create a new MIPS project.");

		this.pageOne.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/wzrdHd/newmipsprj_wiz.png"));

		addPage(this.pageOne);
	}

	@Override
	public void setInitializationData(IConfigurationElement config,	String propertyName, Object data) throws CoreException {
		this.configurationElement = config;
	}

}

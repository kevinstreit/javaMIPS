package de.unisb.prog.mips.parser.ui.launching;

import java.io.IOException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.Saveable;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.parser.generate.Generate;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.views.MIPSConsoleView;

public class ExecutableMIPSShortcut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		System.out.println("Launch Selection");
	}

	@Override
	public void launch(IEditorPart editor, final String mode) {
		launch(editor, "debug".equals(mode));
	}

	private static boolean askToSave(XtextEditor editor) {
		MessageDialog saveConfirmation =
			new MessageDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
					"Save resource",
					null,
					"'" + editor.getEditorInput().getName() + "'" + " has been modified. You need to save the changes before running. Save changes?",
					MessageDialog.QUESTION,
					new String[] { "Yes", "No", "Cancel" },
					0);

		saveConfirmation.open();

		if (saveConfirmation.getReturnCode() == 0) {
			editor.doSave(null);
			try {
				for (Saveable s : editor.getSaveables())
					s.doSave(null);
			} catch (CoreException e2) {
				return false;
			}
			return true;
		}

		return false;
	}

	public static void launch(IEditorPart editor, final boolean debug) {
		if (editor instanceof XtextEditor) {
			final XtextEditor e = (XtextEditor) editor;

			if (e.isDirty() && !askToSave(e))
				return;

			while (e.isDirty()){}

			if (e.getEditorInput().exists()) {
				IResource res = (IResource) e.getEditorInput().getAdapter(IResource.class);
				if (res != null) {
					try {
						IMarker[] problems = res.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
						if (problems.length > 0) {
							Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", "Errors exist in the project you want to run. Fix them first.");
							StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
							return;
						}
					} catch (CoreException e1) {
						// Should not happen
					}
				}
			}

			IXtextDocument doc = e.getDocument();

			Assembly asm = doc.readOnly(new IUnitOfWork<Assembly, XtextResource>() {
				@Override
				public Assembly exec(XtextResource state) throws Exception {
					if (state.getErrors().size() > 0) {
						Status stat = new Status(Status.ERROR, "de.unisb.prog.mips.parser.ui", "Errors exist in the project you want to run. Fix them first.");
						StatusManager.getManager().handle(stat, StatusManager.SHOW | StatusManager.BLOCK);
						return null;
					}


					Asm a = (Asm) state.getContents().get(0);
					Assembly asm = new Assembly();
					Generate gen = new Generate(asm);
					gen.generate(a);

					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MIPSConsoleView.ID);

					MIPSCore.getInstance().init(1024);
					MIPSCore.getInstance().load(asm);
					MIPSCore.getInstance().start(debug);

					return asm;
				}
			});

			if (asm != null) {
				try {
					asm.append(System.out);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}

package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.xtext.ui.editor.XtextEditor;

public class MIPSBreakpointAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, @SuppressWarnings("rawtypes") Class adapterType) {
		if (adaptableObject instanceof XtextEditor) {
			XtextEditor editorPart = (XtextEditor) adaptableObject;
			IResource resource = (IResource) editorPart.getEditorInput().getAdapter(IResource.class);
			if (resource != null) {
				String extension = resource.getFileExtension();
				if (extension != null && extension.equals("mips")) {
					return new MIPSBreakpointAdapter();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[] { MIPSBreakpointAdapter.class };
	}

}

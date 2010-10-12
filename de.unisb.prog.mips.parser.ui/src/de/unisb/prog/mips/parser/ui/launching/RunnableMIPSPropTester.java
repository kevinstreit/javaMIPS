package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class RunnableMIPSPropTester extends org.eclipse.core.expressions.PropertyTester {

	public RunnableMIPSPropTester() {
		System.out.println("PropTester create");
	}
	
	public static boolean isMIPSRunnable(IFile file) {
		return true;
	}

	public static boolean isMIPSRunnable(IEditorInput inp) {
		IFile f = (IFile) inp.getAdapter(IFile.class);
		return (f != null && isMIPSRunnable(f));
	}
	
	public static boolean isMIPSRunnable(IAdaptable obj) {
		if (obj instanceof IEditorPart) {
			IEditorPart editor = (IEditorPart) obj;
			return isMIPSRunnable((IEditorInput) editor.getEditorInput());
		}
		
		if (obj instanceof IEditorInput) {
			return isMIPSRunnable((IEditorInput) obj);
		}
		
		if (obj instanceof IFile) {
			return isMIPSRunnable((IFile) obj);
		}
		
		return false;
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		// TODO: Check if the selected objects are actually MIPS objects
		System.out.println("PropTester test: " + receiver.getClass().getCanonicalName());
		return (receiver instanceof IAdaptable && isMIPSRunnable((IAdaptable) receiver));
	}

}

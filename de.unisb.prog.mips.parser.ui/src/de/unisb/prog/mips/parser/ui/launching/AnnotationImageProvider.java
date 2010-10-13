package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.texteditor.IAnnotationImageProvider;

import de.unisb.prog.mips.parser.ui.MIPSCore;

public class AnnotationImageProvider implements IAnnotationImageProvider {

	public AnnotationImageProvider() {

	}

	@Override
	public Image getManagedImage(Annotation annotation) {
		return JFaceResources.getImage(MIPSCore.ICN_INST_PTR);
	}

	@Override
	public String getImageDescriptorId(Annotation annotation) {
		System.out.println("descID");
		return null;
	}

	@Override
	public ImageDescriptor getImageDescriptor(String imageDescritporId) {
		System.out.println("desc");
		return null;
	}

}

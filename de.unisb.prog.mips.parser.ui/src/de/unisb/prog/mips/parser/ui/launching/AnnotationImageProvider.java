package de.unisb.prog.mips.parser.ui.launching;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.texteditor.IAnnotationImageProvider;

import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.util.MarkerUtil;

public class AnnotationImageProvider implements IAnnotationImageProvider {

	public AnnotationImageProvider() {

	}

	public Image getManagedImage(Annotation annotation) {
		if (annotation.getType().equals(MarkerUtil.ANN_ID_CurrentIP))
			return JFaceResources.getImage(MIPSCore.ICN_INST_PTR);
		else
			return null;
	}

	public String getImageDescriptorId(Annotation annotation) {
		return null;
	}

	public ImageDescriptor getImageDescriptor(String imageDescritporId) {
		return null;
	}

}

package de.unisb.prog.mips.parser.ui.launching;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.parser.ui.util.BuildUtil;
import de.unisb.prog.mips.parser.ui.util.UIErrorReporter;

public class XTextBuildParticipant implements IXtextBuilderParticipant {

	public XTextBuildParticipant() {

	}

	public void build(IBuildContext context, IProgressMonitor monitor) throws CoreException {
		IProject proj = context.getBuiltProject();
		proj.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);

		UIErrorReporter error = new UIErrorReporter(true);
		Collection<Assembly> asm = BuildUtil.getASM(proj, error);

		if (asm != null) {
			MIPSCore.getInstance().init(1024);
			MIPSCore.getInstance().load(asm, proj);
		} else {
			if (MIPSCore.getInstance().getSys() != null)
				MIPSCore.getInstance().unloadASM();
			// TODO: Evtl. create marker

		}
	}

}

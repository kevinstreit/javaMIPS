package de.unisb.prog.mips.parser.ui.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.parser.generate.Generate;
import de.unisb.prog.mips.parser.mips.Asm;

public class BuildUtil {
	public static Collection<Assembly> getASM(IProject project, final ErrorReporter<Position> error) {
		final ResourceSet resSet = new ResourceSetImpl();

		try {
			project.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile && ((IFile) resource).getFileExtension().equals("mips")) {
						IFile file = (IFile) resource;
						resSet.getResource(URI.createURI(file.getRawLocationURI().toString()), true);
						return false;
					}
					return true;
				}
			});

			List<Assembly> assemblies = new LinkedList<Assembly>();

			for (Resource res : resSet.getResources()) {
				if (res.getErrors().size() > 0) {
					for (Diagnostic diag : res.getErrors())
						error.error(diag.getMessage());
					return null;
				} else if (res.getContents().size() > 0) {
					EObject eobj = res.getContents().get(0);
					if (eobj instanceof Asm) {
						Asm a = (Asm) eobj;
						Assembly asm = new Assembly(error);
						new Generate(asm).generate(a);
						assemblies.add(asm);
					}
				}
			}

			return assemblies;
		} catch (CoreException e) {
			return null;
		} catch (IllegalStateException e) {
			return null;
		}
	}
}

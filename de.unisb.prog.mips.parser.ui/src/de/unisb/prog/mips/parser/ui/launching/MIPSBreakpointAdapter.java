package de.unisb.prog.mips.parser.ui.launching;

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.parser.generate.Generate;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.parser.ui.MIPSCore;
import de.unisb.prog.mips.util.Pair;

public class MIPSBreakpointAdapter implements IToggleBreakpointsTarget {
	public void toggleLineBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		if (part instanceof XtextEditor && selection instanceof TextSelection) {
			XtextEditor textEditor = (XtextEditor) part;
			if (textEditor != null) {
				IResource resource = (IResource) textEditor.getEditorInput().getAdapter(IResource.class);
				ITextSelection textSelection = (ITextSelection) selection;
				int lineNumber = textSelection.getStartLine();

				IBreakpoint[] breakpoints = DebugPlugin.getDefault().getBreakpointManager().getBreakpoints(MIPSBreakpoint.DEBUG_MODEL_ID);
				for (int i = 0; i < breakpoints.length; i++) {
					IBreakpoint breakpoint = breakpoints[i];
					if (resource.equals(breakpoint.getMarker().getResource())) {
						if (((ILineBreakpoint)breakpoint).getLineNumber() == lineNumber + 1) {
							breakpoint.delete();
							return;
						}
					}
				}

				IXtextDocument doc = textEditor.getDocument();
				int charStart, charEnd;

				Assembly asm = doc.readOnly(new IUnitOfWork<Assembly, XtextResource>() {
					public Assembly exec(XtextResource state) throws Exception {
						MIPSCore.getInstance().init(1024);
						Asm a = (Asm) state.getContents().get(0);
						Assembly asm = new Assembly();
						Generate gen = new Generate(asm);
						gen.generate(a);
						return asm;
					}
				});

				if (asm != null) {
					Map<Pair<String, Integer>, Element> elts = asm.computeElementMap();
					for (Pair<String, Integer> p : elts.keySet())
						System.out.println(" + " + p.fst() + ":" + p.snd());

					System.out.println(" ? " + resource.getFullPath().toString() + ":" + (lineNumber+1));
					Element elt = elts.get(new Pair<String, Integer>(resource.getFullPath().toString(), (lineNumber+1)));

					if (elt != null) {
						try {
							charStart = doc.getLineOffset(lineNumber);
							charEnd = charStart + doc.getLineLength(lineNumber);
						} catch (BadLocationException e) {
							charStart = charEnd = -1;
						}
						MIPSBreakpoint lineBreakpoint = new MIPSBreakpoint(resource, lineNumber + 1, charStart, charEnd);
						DebugPlugin.getDefault().getBreakpointManager().addBreakpoint(lineBreakpoint);
					}
				}
			}
		}
	}

	public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {
		return part instanceof XtextEditor;
	}

	public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		// Nothing
	}

	public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) {
		return false;
	}

	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
		// Nothing
	}

	public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {
		return false;
	}
}

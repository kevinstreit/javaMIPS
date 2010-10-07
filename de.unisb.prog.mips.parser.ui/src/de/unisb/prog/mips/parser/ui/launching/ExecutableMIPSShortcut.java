package de.unisb.prog.mips.parser.ui.launching;

import java.io.IOException;

import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.os.DefaultSysCallHandler;
import de.unisb.prog.mips.parser.generate.Generate;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.simulator.Sys;

public class ExecutableMIPSShortcut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		// TODO Auto-generated method stub
		System.out.println("Launch Selection");
	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub
		XtextEditor e = (XtextEditor) editor;
		IXtextDocument doc = e.getDocument();
		Assembly asm = doc.readOnly(new IUnitOfWork<Assembly, XtextResource>() {
			@Override
			public Assembly exec(XtextResource state) throws Exception {
				Sys sys = new Sys(1024, new DefaultSysCallHandler());
				Asm a = (Asm) state.getContents().get(0);
				Assembly asm = new Assembly();
				Generate gen = new Generate(asm);
				gen.generate(a);
				asm.prepare(sys);
				sys.run(asm);
				return asm;
			}
		});
		try {
			asm.append(System.out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

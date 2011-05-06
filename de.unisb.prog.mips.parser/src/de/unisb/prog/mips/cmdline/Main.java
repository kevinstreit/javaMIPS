package de.unisb.prog.mips.cmdline;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.ErrorReporter;
import de.unisb.prog.mips.assembler.Position;
import de.unisb.prog.mips.os.DefaultSysCallImpl;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.parser.MipsStandaloneSetup;
import de.unisb.prog.mips.parser.generate.Generate;
import de.unisb.prog.mips.parser.mips.Asm;
import de.unisb.prog.mips.simulator.ExceptionHandler;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.simulator.SysCallHandler;

public class Main {

	private static XtextResourceSet resourceSet;
	private static final ErrorReporter<Position> reporter = ErrorReporter.POSITION_STD_REPORTER;

	private static void init() {
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new MipsStandaloneSetup().createInjectorAndDoEMFRegistration();
		resourceSet = injector.getInstance(XtextResourceSet.class);
	}

	private static Assembly parseFile(String fileName, Reader r) throws IOException {
		Resource resource = resourceSet.createResource(URI.createURI("dummy:/" + fileName));
		InputStream in = new FileInputStream(fileName);
		resource.load(in, resourceSet.getLoadOptions());
		Asm a = (Asm) resource.getContents().get(0);

		EList<Resource.Diagnostic> errors = resource.getErrors();
		if (!errors.isEmpty()) {
			for (Resource.Diagnostic e : errors)
				System.err.println(e);
			return null;
		}
		Assembly asm = new Assembly();
		new Generate(asm).generate(a);
		return asm;
	}

	private static List<Assembly> assembleFiles(String[] args) throws IOException {
		List<Assembly> asms = new LinkedList<Assembly>();
		for (String a : args) {
			Reader r;
			try {
				r = new FileReader(a);
			} catch (FileNotFoundException e) {
				System.err.println("no such file: " + a + "\n");
				break;
			}
			Assembly asm = parseFile(a, r);
			asms.add(asm);
		}
		return asms;
	}

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("syntax: Main <mips files...>");
			System.exit(1);
		}
		init();
		List<Assembly> asms = assembleFiles(args);
		Assembly asm = Assembly.link(asms, reporter);
		if (reporter.errorsReported() > 0)
			return;
		asm.prepare();

		SysCallHandler sysh = new SysCallDispatcher(DefaultSysCallImpl.INSTANCE);
		ExceptionHandler exch = new CLIExceptionHandler(System.err);
		Sys sys = new Sys(1024, exch, sysh);
		sys.load(asm);
		if (reporter.errorsReported() > 0)
			return;
		Processor p = sys.getProcessor();
		p.state = ExecutionState.RUNNING;
		p.run(true);
	}

}

package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.os.SysCallDispatcher;
import de.unisb.prog.mips.os.SysCallImplementation;
import de.unisb.prog.mips.simulator.ExceptionHandler;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.simulator.Type;
import de.unisb.prog.mips.util.Option;

public class Test {
	public static void main(String[] args) throws IOException, AssemblerException, LabelAlreadyDefinedException {
		Collection<Assembly> asms = new LinkedList<Assembly>();
		Assembly asm = new Assembly();
		Text t = asm.getText();
		Data d = asm.getData();
		asms.add(asm);
		Element e;

		e = d.string("Hallo Welt\n", true);
		e.setLabel("str");
		asm.defineLabel(e);
		asm.makeGlobal(e);
		d.align(3);
		List<Expr> vals = new ArrayList<Expr>();
		for (int i = 0; i < 10; i++)
			vals.add(Expressions.constantInt(i));
		e = d.values(vals, Type.WORD);
		e.setLabel("arr");
		asm.defineLabel(e);
		asm.makeGlobal(e);

		e = t.address(Reg.a0, asm.createRef("str"));
		e.setLabel("main");
		asm.defineLabel(e);
		asm.makeGlobal(e);
		e = t.absjump(Opcode.jal, asm.createRef("tgt"));
		e = t.constant(Reg.v0, 4);
		e = t.normal(IntFunct.syscall, Reg.zero, Reg.zero, Reg.zero, 0);
		e = t.constant(Reg.v0, 10);
		e = t.normal(IntFunct.syscall, Reg.zero, Reg.zero, Reg.zero, 0);

		asm = new Assembly();
		asms.add(asm);
		t = asm.getText();
		e = t.normal(IntFunct.jr, Reg.ra, Reg.zero, Reg.zero);
		e.setLabel("tgt");
		asm.defineLabel(e);
		asm.makeGlobal(e);
		e = t.normal(IntFunct.add, Reg.v0, Reg.a0, Reg.t2, 0);
		e.setLabel("test");
		asm.defineLabel(e);
		t.constant(Reg.t1, 0x12345678);
		t.condjump(Opcode.beq, Reg.t5, Reg.t6, asm.createRef("test"));
		t.loadstore(Opcode.lw, Reg.t7, Option.empty(Reg.class), asm.createRef("arr"));
		t.loadstore(Opcode.lw, Reg.t4, Option.empty(Reg.class), asm.createRef("str"));
		t.address(Reg.a3, asm.createRef("test"));
		// asm.append(System.out);

		Sys sys = new Sys(1000, ExceptionHandler.SILENT, new SysCallDispatcher(SysCallImplementation.DEFAULT));
		Assembly linked = Assembly.link(asms, ErrorReporter.POSITION_STD_REPORTER);
		linked.prepare();
		sys.load(linked);
		linked.append(System.out);
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.RUNNING;
		proc.run(false);
	}

}

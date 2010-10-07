package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.os.DefaultSysCallHandler;
import de.unisb.prog.mips.simulator.Processor;
import de.unisb.prog.mips.simulator.Sys;
import de.unisb.prog.mips.simulator.Type;
import de.unisb.prog.mips.simulator.ProcessorState.ExecutionState;
import de.unisb.prog.mips.util.Option;

public class Test {
	
	public static void main(String[] args) throws IOException, AssemblerException {
		Assembly asm = new Assembly();
		Text t = asm.getText();
		Data d = asm.getData();
		Element e;
		
		e = d.string("Hallo Welt\n", true);
		e.setLabel("str");
		asm.addLabel(e);
		d.align(3);
		List<Expr> vals = new ArrayList<Expr>();
		for (int i = 0; i < 10; i++)
			vals.add(Expressions.constantInt(i));
		e = d.values(vals, Type.WORD);
		e.setLabel("arr");
		asm.addLabel(e);
		
		e = t.address(Reg.a0, asm.createRef("str"));
		e = t.constant(Reg.v0, 4);
		e = t.normal(IntFunct.syscall, Reg.zero, Reg.zero, Reg.zero, 0);
		e = t.constant(Reg.v0, 10);
		e = t.normal(IntFunct.syscall, Reg.zero, Reg.zero, Reg.zero, 0);
		
		e = t.normal(IntFunct.add, Reg.v0, Reg.a0, Reg.t2, 0);
		e.setLabel("test");
		asm.addLabel(e);
		t.constant(Reg.t1, 0x12345678);
		t.condjump(Opcode.beq, Reg.t5, Reg.t6, asm.createRef("test"));
		t.loadstore(Opcode.lw, Reg.t7, Option.empty(Reg.class), asm.createRef("arr"));
		t.loadstore(Opcode.lw, Reg.t4, Option.empty(Reg.class), asm.createRef("str"));
		asm.append(System.out);
		
		Sys sys = new Sys(1000, new DefaultSysCallHandler());
		asm.prepare(sys);
		asm.append(System.out);
		sys.load(asm);
		Processor proc = sys.getProcessor();
		proc.state = ExecutionState.RUNNING;
		proc.run();
	}

}

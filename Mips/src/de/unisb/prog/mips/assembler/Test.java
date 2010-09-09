package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.simulator.Type;

public class Test {
	
	public static final MemoryLayout ml = new MemoryLayout() {
		
		@Override
		public int textStartOffset() { return 0; }
		
		@Override
		public int textStart() { return 0x400000; }
		
		@Override
		public int dataStartOffset() { return -0x8000; }
		
		@Override
		public int dataStart() { return 0x10000000; }
	};
	
	public static void main(String[] args) throws IOException, AssemblerException {
		Assembly asm = new Assembly();
		Text t = asm.getText();
		Data d = asm.getData();
		Element e;
		
		e = d.string("Hallo", true);
		e.setLabel("str");
		List<Expr<Integer>> vals = new ArrayList<Expr<Integer>>();
		for (int i = 0; i < 10; i++)
			vals.add(Expressions.constantInt(i));
		e = d.values(vals, Type.WORD);
		e.setLabel("arr");
		
		
		e = t.normal(IntFunct.add, 3, 4, 2, 0);
		e.setLabel("test");
		t.constant(22, 0x12345678);
		t.condjump(Opcode.beq, 3, 4, asm.createLabelRef("test"));
		t.loadstore(Opcode.lw, 4, asm.createLabelRef("arr"));
		t.loadstore(Opcode.lw, 5, asm.createLabelRef("str"));
		asm.append(System.out);
		asm.prepare(ml);
		asm.append(System.out);
	}

}

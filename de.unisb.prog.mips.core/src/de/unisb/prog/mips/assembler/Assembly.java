package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.JumpTargetOutOfRange;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Instructions;
import de.unisb.prog.mips.simulator.Memory;

public class Assembly {
	
	private Text text = new Text(this);
	private Data data = new Data(this);
	private Scope currScope = new Scope();
	
	public Text getText() {
		return text;
	}
	
	public Data getData() {
		return data;
	}
	
	public void prepare(MemoryLayout layout, ErrorReporter<Position> reporter) throws AssemblerException {
		data.prepare(reporter);
		text.prepare(reporter);
		data.relocate(layout.dataStart(), reporter);
		text.relocate(layout.textStart(), reporter);
	}
	
	public void writeToMem(Memory mem, MemoryLayout layout) throws JumpTargetOutOfRange {
		data.writeToMem(mem, layout.dataStart() - layout.dataStartOffset());
		text.writeToMem(mem, layout.textStart() - layout.textStartOffset());
	}
	
	public void append(Appendable app) throws IOException {
		app.append(".data\n");
		data.append(app);
		app.append(".text\n");
		text.append(app);
	}
	
	public void addLabel(Element e) {
		currScope.add(e.getLabel(), e);
	}

	public LabelRef createRef(String name) {
		return currScope.createRef(name);
	}
	
	public Instruction parse(String mnemonic) {
		return Instructions.get(mnemonic);
	}
	
	public Position getPosition(int addr) {
		if (text.isInside(addr))
			return text.getElementAt(addr);
		if (data.isInside(addr))
			return data.getElementAt(addr);
		return Position.ILLEGAL;
	}
}

package de.unisb.prog.mips.assembler;

import java.io.IOException;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.Space;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.Instruction;
import de.unisb.prog.mips.insn.Instructions;
import de.unisb.prog.mips.simulator.Memory;

public class Assembly {
	
	private Text text = new Text(this);
	private Data data = new Data(this);
	private Scope currScope = new Scope();
	
	private static final String NULL_NAME = "@NULL";
	
	@SuppressWarnings({ "unused" })
	private Segment seg0 = new Segment(this) {
		{
			Element elm = new Space(this, 0);
			elm.setLabel(NULL_NAME);
			add(elm);
			addLabel(elm);
		}
		
		@Override protected void relocate(int startAddress) { }
		@Override public Kind getKind() { return Kind.NULL; }
	};
	
	public final LabelRef nullRef = createRef(NULL_NAME);
	
	
	public Text getText() {
		return text;
	}
	
	public Data getData() {
		return data;
	}
	
	public void prepare(MemoryLayout layout) throws AssemblerException {
		data.assignOffsets(layout.dataStartOffset());
		
		// rewrite instructions using an address 
		// patch jumps
		text.prepare(layout);
	}
	
	public void writeToMem(Memory mem, MemoryLayout layout) {
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
}

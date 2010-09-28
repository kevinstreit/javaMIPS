package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.simulator.Memory;

public class Assembly {
	
	private Text text = new Text();
	private Data data = new Data();
	
	private Map<String, LabelRef> refs = new HashMap<String, LabelRef>();
	
	public Text getText() {
		return text;
	}
	
	public Data getData() {
		return data;
	}
	
	public LabelRef createLabelRef(String name) {
		LabelRef r = refs.get(name);
		if (r == null) {
			r = new LabelRef(name);
			refs.put(name, r);
		}
		return r;
	}
	
	public void prepare(MemoryLayout layout) throws AssemblerException {
		// connect label references to labels
		Map<String, Element> labels = new HashMap<String, Element>();
		data.collectLabels(labels);
		text.collectLabels(labels);
		
		for (LabelRef r : refs.values()) {
			r.patch(labels);
		}
		
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
}

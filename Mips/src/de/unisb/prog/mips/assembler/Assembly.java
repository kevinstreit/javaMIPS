package de.unisb.prog.mips.assembler;

import java.util.HashMap;
import java.util.Map;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;

public class Assembly extends SymbolByteBuffer {
	
	private Text text = new Text();
	private Data data = new Data();
	
	private Map<String, LabelRef> refs = new HashMap<String, LabelRef>();
	
	public LabelRef createLabelRef(String name) {
		LabelRef r = refs.get(name);
		if (r == null) {
			r = new LabelRef(name);
			refs.put(name, r);
		}
		return r;
	}
	
	public void finish() throws AssemblerException {
		// connect label references to labels
		Map<String, Element> labels = new HashMap<String, Element>();
		data.collectLabels(labels);
		text.collectLabels(labels);
		
		for (LabelRef r : refs.values()) {
			r.patch(labels);
		}
		
		// The text segment finishes it all
		text.finish(data);
	}
	
}

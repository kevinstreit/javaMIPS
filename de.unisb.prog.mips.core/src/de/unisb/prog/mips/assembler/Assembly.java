package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.simulator.Memory;

public class Assembly {

	private Text text = new Text(this);
	private Data data = new Data(this);
	private Scope currScope = new Scope();
	private Scope rootScope = currScope;
	private final Set<String> globalLabels = new HashSet<String>();

	public Text getText() {
		return text;
	}

	public Data getData() {
		return data;
	}

	public void prepare(MemoryLayout layout, ErrorReporter<Position> reporter) {
		data.prepare(reporter);
		text.prepare(reporter);
		data.relocate(layout.dataStart(), reporter);
		text.relocate(layout.textStart(), reporter);
	}

	public void writeToMem(Memory mem, MemoryLayout layout) {
		data.writeToMem(mem, layout.dataStart());
		text.writeToMem(mem, layout.textStart());
	}

	public void append(Appendable app) throws IOException {
		app.append(".data\n");
		data.append(app);
		app.append(".text\n");
		text.append(app);
	}

	public void addGlobalLabel(String label) {
		globalLabels.add(label);
	}

	public void addLabel(Element e) {
		String label = e.getLabel();
		if (label == null)
			throw new IllegalStateException("Cannot add null label");
		if (globalLabels.contains(label))
			rootScope.add(label, e);
		else
			currScope.add(label, e);
	}

	public LabelRef createRef(String name) {
		return currScope.createRef(name);
	}

	public Position getPosition(int addr) {
		// TODO This can be done nicer: Check for nulls in getElementAt
		if (text.isInside(addr))
			return text.getElementAt(addr).getPosition();
		if (data.isInside(addr))
			return data.getElementAt(addr).getPosition();
		return Position.ILLEGAL;
	}
}

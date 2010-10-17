package de.unisb.prog.mips.assembler;

import java.io.IOException;
import java.util.Collection;

import de.unisb.prog.mips.assembler.segments.Data;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.simulator.Memory;

public class Assembly extends SymbolTable {

	private Text text = new Text(this);
	private Data data = new Data(this);

	private final ErrorReporter<Position> reporter;

	public Assembly(ErrorReporter<Position> reporter) {
		this.reporter = reporter;
	}

	public Assembly() {
		this(ErrorReporter.POSITION_STD_REPORTER);
	}

	public Text getText() {
		return text;
	}

	public Data getData() {
		return data;
	}

	public ErrorReporter<Position> getReporter() {
		return reporter;
	}

	public void prepare() {
		text.prepare(reporter);
		data.prepare(reporter);
	}

	public void relocate(MemoryLayout layout) {
		// set base addresses of segments
		text.setBase(layout.textStart());
		data.setBase(layout.dataStart());
		text.relocate(reporter);
		data.relocate(reporter);
	}

	public void writeToMem(Memory mem) {
		text.writeToMem(mem);
		data.writeToMem(mem);
	}

	public void append(Appendable app) throws IOException {
		app.append("Assembly\n");
		data.append(app);
		text.append(app);
	}

	public Element getElementAt(int addr) {
		if (text.isInside(addr))
			return text.getElementAt(addr);
		if (data.isInside(addr))
			return data.getElementAt(addr);
		return null;
	}

	public Position getPosition(int addr) {
		Element elm = getElementAt(addr);
		return elm != null ? elm.getPosition() : Position.ILLEGAL;
	}

	public void linkWith(Assembly asm) throws LabelAlreadyDefinedException {
		// integrate the other symbol table
		integrate(asm);

		// add the other segments' elements to this one.
		text.addAll(asm.text);
		data.addAll(asm.data);
	}

	public static Assembly link(Collection<Assembly> assemblies, ErrorReporter<Position> reporter) {
		Assembly linked = new Assembly(reporter);
		for (Assembly asm : assemblies) {
			try {
				linked.linkWith(asm);
			} catch (LabelAlreadyDefinedException e) {
				Element labelled = e.definer;
				reporter.error(labelled.getPosition(), "label \"%\" multiply defined", labelled.getLabel());
			}
		}

		for (String u : linked.getUnresolved().keySet())
			reporter.error("symbol \"%s\" cannot be resolved", u);
		return linked;
	}

}

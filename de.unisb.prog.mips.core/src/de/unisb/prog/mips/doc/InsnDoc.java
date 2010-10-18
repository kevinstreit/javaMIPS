package de.unisb.prog.mips.doc;

import java.io.IOException;
import java.util.Collection;

import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.assembler.generators.InstructionGenerator;

public class InsnDoc {
	public final InsnDocGroup parentGroup;

	public final String mnemonic; 		// e.g. addi
	public final String longName;		// e.g. Add Immediate
	public final String description;	// e.g. Adds an immediate to the value read from $src and stores the result in $dst.

	public InsnDoc(
		InsnDocGroup parent,
		String mnemonic,
		String longName,
		String description
	) {
		this.parentGroup = parent;
		this.mnemonic = mnemonic;
		this.longName = longName;
		this.description = description;
	}

	public void appendExamples(Appendable app) throws IOException {
		String example = "";
		if (Generators.getInstance().contains(mnemonic)) {
			Collection<InstructionGenerator> gens = Generators.getInstance().get(mnemonic);
			app.append("Example");
			app.append(gens.size() > 1 ? "s" : "");
			app.append(":\n");
			for (InstructionGenerator g : gens) {
				app.append(mnemonic);
				app.append(' ');
				app.append(g.stringRepr());
				app.append('\n');
			}
		}
	}

	public String makeExamples() {
		StringBuffer sb = new StringBuffer();
		try {
			appendExamples(sb);
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}

	public String getHelp() {
		StringBuffer sb = new StringBuffer();
		sb.append(mnemonic);
		sb.append('\n');
		sb.append('\n');
		sb.append(longName);
		sb.append('\n');
		sb.append('\n');
		try {
			appendExamples(sb);
		} catch (IOException e) {
		}
		sb.append('\n');
		sb.append(description);
		return sb.toString();
	}

	@Override
	public String toString() {
		return mnemonic + " (" + longName + ")";
	}
}

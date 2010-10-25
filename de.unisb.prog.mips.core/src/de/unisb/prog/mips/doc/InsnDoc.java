package de.unisb.prog.mips.doc;

import java.io.IOException;
import java.util.Collection;

import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.assembler.generators.InstructionGenerator;
import de.unisb.prog.mips.insn.Instructions;

public class InsnDoc {
	public final InsnDocGroup parentGroup;

	public final String mnemonic; 		// e.g. addi
	public final String shortDesc;		// e.g. Add Immediate
	public final String comments;	    // e.g. Adds an immediate to the value read from $src and stores the result in $dst.
	public final String pseudo;	        // e.g. dst <- op1 + op2

	public InsnDoc(
		InsnDocGroup parent,
		String mnemonic,
		String pseudo,
		String shortDesc,
		String comments
	) {
		this.parentGroup = parent;
		this.mnemonic = mnemonic;
		this.shortDesc = shortDesc;
		this.comments = comments;
		this.pseudo   = pseudo;
	}

	public void appendExamples(Appendable app) throws IOException {
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
		sb.append("Description: " + shortDesc);
		sb.append('\n');
		sb.append("Operation: " + pseudo);
		sb.append('\n');
		sb.append("Pseudo operation: " + (Instructions.get(mnemonic).valid() ? "no" : "yes"));
		sb.append('\n');
		try {
			appendExamples(sb);
		} catch (IOException e) {
		}
		sb.append('\n');
		if (comments != null && comments.length() > 0) {
			sb.append("Comments: ");
			sb.append(comments);
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return mnemonic + " (" + shortDesc + ")";
	}
}

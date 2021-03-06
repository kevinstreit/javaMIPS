/*
 * generated by Xtext
 */
package de.unisb.prog.mips.parser.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import de.unisb.prog.mips.assembler.generators.Generators;
import de.unisb.prog.mips.doc.Documentation;
import de.unisb.prog.mips.doc.InsnDoc;
/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class MipsProposalProvider extends AbstractMipsProposalProvider {

	private void add(String opcode, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		ICompletionProposal compProposal = createCompletionProposal(opcode, context);

		if (compProposal instanceof ConfigurableCompletionProposal) {
			ConfigurableCompletionProposal proposal = (ConfigurableCompletionProposal) compProposal;
			InsnDoc insn = Documentation.getInsnDoc(opcode);
			if (insn != null) {
				proposal.setAdditionalProposalInfo(insn.getHelp());
			}
			acceptor.accept(proposal);
		} else {
			acceptor.accept(compProposal);
		}
	}

	@Override
	public void complete_Insn(EObject model, RuleCall ruleCall,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		for (String opcode : Generators.getInstance().getAvailableOpcodes())
			add(opcode, context, acceptor);
	}
}

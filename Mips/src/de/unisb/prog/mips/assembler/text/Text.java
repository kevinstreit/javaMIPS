package de.unisb.prog.mips.assembler.text;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import de.unisb.prog.mips.assembler.Label;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Segment;
import de.unisb.prog.mips.insn.Encode;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Text extends LinkedList<Insn> implements Segment {
	
	private final Map<Insn, Label> labels = new HashMap<Insn, Label>();
	
	public void normal(IntFunct f, int rt, int rs, int rd, int shamt) {
		add(new Normal(Encode.r(f, rt, rs, rd, shamt)));
	}
	
	public void imm(Opcode opc, int rt, int rs, int imm) {
		add(new Normal(Encode.i(opc, rt, rs, imm)));
	}

	public void loadstore(Opcode opc, int rt, LabelRef ref) {
		add(new DataRef(opc, rt, ref));
	}

	public void condjump(Opcode opc, int rt, int rs, LabelRef ref) {
	}

	public void condjump(Opcode r, int rs, LabelRef ref) {
	}

	public void condjump(RegImm r, int rs, LabelRef ref) {
	}

	@Override
	public Label createLabelOnLast() {
		return null;
	}

	@Override
	public Segment.Type getType() {
		return Segment.Type.TEXT;
	}
	
	private void generateDataAddresses() {
		ListIterator<Insn> it = this.listIterator();
		
		while (it.hasNext()) {
			Insn curr = it.next();
			List<Insn> code = curr.generateDataAddr(baseReg, tgtReg, addr);
			it.add(e)
			
			
		}
	}
	
	private void computeLabelAddresses() {
		int ofs = 0;
		for (Insn insn : this) {
			insn.offset = ofs;
			Label l = labels.get(insn);
			if (l != null) 
				l.setOffset(ofs);
			ofs = insn.nextElementPos(ofs);
		}
	}

	public resolveLabels() {
		generateDataAddresses();
		computeLabelAddresses();
		generateTextAddresses();
	}

	@Override
	public void patchLabels() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

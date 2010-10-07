package de.unisb.prog.mips.assembler.generators;

import java.util.Arrays;
import java.util.List;

import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.util.Bitfield;

public abstract class InstructionGenerator {
	
	private final AddressMode am;
	private final List<Bitfield> regFields;
	
	public AddressMode getAddressMode() {
		return am;
	}
	
	public int getNumberOfRegs() {
		return regFields.size();
	}
	
	public InstructionGenerator(AddressMode am, Bitfield... regFields) {
		this(am, Arrays.asList(regFields));
	}
	
	public InstructionGenerator(AddressMode am, List<Bitfield> regFields) {
		super();
		this.am = am;
		this.regFields = regFields;
	}
	
	public final int encodeRegisters(int word, List<Reg> regs) {
		for (int i = 0, n = Math.min(regs.size(), regFields.size()); i < n; i++)
			word = regFields.get(i).insert(word, regs.get(i).ordinal());
		return word;
	}
	
	public boolean isLegal() {
		return true;
	}
	
	public abstract Element generate(Text text, String opcode, OperandInstance inst);
}

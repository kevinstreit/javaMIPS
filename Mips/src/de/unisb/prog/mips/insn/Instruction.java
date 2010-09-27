package de.unisb.prog.mips.insn;

import java.util.EnumSet;
import java.util.Set;

public interface Instruction {
	
	public static final Set<Attribute> BRANCH     = EnumSet.of(Attribute.CHANGES_PC, Attribute.SEXT_IMM_16);
	public static final Set<Attribute> BRANCH0    = EnumSet.of(Attribute.CHANGES_PC, Attribute.SEXT_IMM_16, Attribute.CMP_AGAINST_0);
	public static final Set<Attribute> JUMP       = EnumSet.of(Attribute.CHANGES_PC, Attribute.ZEXT_IMM_26);
	public static final Set<Attribute> INDIR_JUMP = EnumSet.of(Attribute.CHANGES_PC, Attribute.INDIR_JUMP);
	public static final Set<Attribute> SEXT       = EnumSet.of(Attribute.SEXT_IMM_16);
	public static final Set<Attribute> ZEXT       = EnumSet.of(Attribute.ZEXT_IMM_16);
	public static final Set<Attribute> REG        = EnumSet.of(Attribute.THREE_REG);
	
	public boolean valid();
	public boolean has(Attribute attr);
	public Set<Attribute> attributes();
	

}

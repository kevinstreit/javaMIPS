package de.unisb.prog.mips.asm;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.PolymorphicDispatcher;

import com.google.inject.Injector;

import de.unisb.prog.mips.asm.mips.Align;
import de.unisb.prog.mips.asm.mips.Asm;
import de.unisb.prog.mips.asm.mips.DataItem;
import de.unisb.prog.mips.asm.mips.DataSegment;
import de.unisb.prog.mips.asm.mips.IArithForm;
import de.unisb.prog.mips.asm.mips.IBr2Form;
import de.unisb.prog.mips.asm.mips.IExpForm;
import de.unisb.prog.mips.asm.mips.ILabelForm;
import de.unisb.prog.mips.asm.mips.Instruction;
import de.unisb.prog.mips.asm.mips.LoadAddress;
import de.unisb.prog.mips.asm.mips.LoadConstant;
import de.unisb.prog.mips.asm.mips.RForm;
import de.unisb.prog.mips.asm.mips.SpecialInsn;
import de.unisb.prog.mips.asm.mips.TextItem;
import de.unisb.prog.mips.asm.mips.TextSegment;
import de.unisb.prog.mips.asm.mips.Word;
import de.unisb.prog.mips.assembler.Assembly;
import de.unisb.prog.mips.assembler.Expr;
import de.unisb.prog.mips.assembler.LabelRef;
import de.unisb.prog.mips.assembler.Reg;
import de.unisb.prog.mips.assembler.segments.Element;
import de.unisb.prog.mips.assembler.segments.Segment;
import de.unisb.prog.mips.assembler.segments.text.Text;
import de.unisb.prog.mips.insn.Attribute;
import de.unisb.prog.mips.insn.IntFunct;
import de.unisb.prog.mips.insn.Opcode;
import de.unisb.prog.mips.insn.RegImm;

public class Generate {
	PolymorphicDispatcher<Element> dispatcher = PolymorphicDispatcher.createForSingleTarget("generate", 1, 2, this); 
	PolymorphicDispatcher<Expr<Integer>> exprD = PolymorphicDispatcher.createForSingleTarget("expr", 1, 2, this);
	
	public void generate(Asm a, Assembly asm) {
		for (EObject o : a.getElem()) 
			dispatcher.invoke(o, asm);
	}
	
	public void generate(TextSegment t, Assembly asm) {
		for (TextItem ti : t.getItems())
			dispatcher.invoke(ti, asm);
	}
	
	public void generate(DataSegment d, Assembly asm) {
		for (DataItem di : d.getItems())
			dispatcher.invoke(di, asm);
	}
	
	public Element generate(TextItem t, Assembly asm) {
		Text text = asm.getText();
		Element elm = dispatcher.invoke(t.getItem(), text);
		if (t.getLabel() != null) 
			elm.setLabel(t.getLabel().getName());
		return elm;
	}
	
	public Element generate(Word w, Segment seg) {
		Element res = null;
		for (Integer i : w.getVal().getVals()) {
			Element elm = seg.word(i);
			if (res == null)
				res = elm;
		}
		return res;
	}
	
	public Element generate(Align a, Segment seg) {
		return seg.align(a.getAlign());
	}
	
	public Element generate(Instruction i, Assembly asm) {
		return dispatcher.invoke(i.getForm(), i, asm);
	}
	
	public Element generate(RForm f, Instruction i, Assembly asm) {
		return asm.getText().normal(lookup(IntFunct.class, i), reg(f.getRs()), reg(f.getRt()), reg(f.getRd()), f.getShamt());
	}
	
	public Element generate(IArithForm f, Instruction i, Assembly asm) {
		return asm.getText().imm(lookup(Opcode.class, i), reg(f.getRs()), reg(f.getRt()), f.getImm());
	}
	
	public Element generate(IExpForm f, Instruction i, Assembly asm) {
		return asm.getText().imm(lookup(Opcode.class, i), reg(f.getRs()), reg(f.getRt()), f.getImm());
	}
	
	public Element generate(ILabelForm f, Instruction i, Assembly asm) {
		Text t       = asm.getText();
		Reg r        = reg(f.getReg());
		String label = f.getLabel().getName();
		LabelRef ref = asm.createLabelRef(label);
		
		if (has(RegImm.class, i)) {
			return t.condjump(lookup(RegImm.class, i), r, ref);
		}
		else {
			Opcode opc   = lookup(Opcode.class, i);
			return opc.has(Attribute.CHANGES_PC) ? t.condjump(opc, r, ref) : t.loadstore(opc, r, ref);
		}
	}
	
	public Element generate(IBr2Form f, Instruction i, Assembly asm) {
		Text t       = asm.getText();
		String label = f.getLabel().getName();
		LabelRef ref = asm.createLabelRef(label);
		Opcode opc   = lookup(Opcode.class, i);
		return t.condjump(opc, reg(f.getRs()), reg(f.getRt()), ref);
	}
	
	public Element generate(SpecialInsn i, Assembly asm) {
		return dispatcher.invoke(i.getInsn(), asm);
	}
	
	public Element generate(LoadAddress i, Assembly asm) {
		String label = i.getLabel().getName();
		LabelRef ref = asm.createLabelRef(label);
		return asm.getText().address(reg(i.getRt()), ref);
	}
	
	public Element generate(LoadConstant i, Assembly asm) {
		return asm.getText().constant(reg(i.getRt()), i.getVal());
	}
	
	private static <T extends Enum<T>> boolean has(Class<T> cls, Instruction i) {
		try {
			Enum.valueOf(cls, i.getOpcode());
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private <T extends Enum<T>> T lookup(Class<T> cls, Instruction i) {
		T res;
		try {
			res =  Enum.valueOf(cls, i.getOpcode());
		}
		catch (IllegalArgumentException e) {
			handleIllegalOpcode(i);
			res = cls.getEnumConstants()[0];
		}
		return res;
	}
	
	private Reg reg(de.unisb.prog.mips.asm.mips.Reg r) {
		String name = r.getName();
		Reg res;
		if (name != null) {
			try {
				res = Reg.valueOf(name);
			}
			catch (IllegalArgumentException e) {
				handleIllegalRegister(r);
				res = Reg.zero;
			}
		}
		
		else {
			int idx = r.getNum();
			if (idx < 0 || idx > 31)
				handleIllegalRegister(r);
			res = Reg.values()[idx];
		}
		return res;
	}
	
	private void handleIllegalRegister(de.unisb.prog.mips.asm.mips.Reg r) {
		// TODO Auto-generated method stub
	}
	
	private void handleIllegalOpcode(Instruction i) {
		// TODO Auto-generated method stub
		
	}


	public Assembly generate() {
		Assembly assembly = new Assembly();
		
		new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new MipsStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(
		    URI.createURI("platform:/resource/org.xtext.example.mydsl/src/example.mydsl"), true);
		Asm a = (Asm) resource.getContents().get(0);
		dispatcher.invoke(a, assembly);
		return assembly;
	}

}

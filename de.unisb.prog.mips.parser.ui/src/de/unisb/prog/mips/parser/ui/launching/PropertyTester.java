package de.unisb.prog.mips.parser.ui.launching;

public class PropertyTester extends org.eclipse.core.expressions.PropertyTester {

	public PropertyTester() {
		System.out.println("PropTester create");
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		System.out.println("PropTester test: " + receiver.getClass().getCanonicalName());
		return true;
	}

}

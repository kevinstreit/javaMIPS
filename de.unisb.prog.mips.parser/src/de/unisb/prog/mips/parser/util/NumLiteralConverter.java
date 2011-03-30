package de.unisb.prog.mips.parser.util;

import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractValueConverter;
import org.eclipse.xtext.parsetree.AbstractNode;


public class NumLiteralConverter extends AbstractValueConverter<Integer> {

	public String toString(Integer value) {
		if (value == null)
			throw new ValueConverterException("INT-value may not be null. (null indeed, zero is ok)", null, null);
		return value.toString();
	}

	public Integer toValue(String string, AbstractNode node) {
		try {
			long v = Long.decode(string);
			int hi = (int) (v >>> 32);
			int lo = (int) (v & 0xffffffff);
			if (hi == -1 || hi == 0)
				return lo;
			throw new ValueConverterException("Couldn't convert '" + string + "' to int.", node, null);
		}
		catch (NumberFormatException e) {
			throw new ValueConverterException("Couldn't convert '" + string + "' to int.", node, e);
		}
	}

}

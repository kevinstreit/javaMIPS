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
		if (string.length() == 0)
			throw new ValueConverterException("Couldn't convert empty string to int.", node, null);
		if (string.startsWith("0x") || string.startsWith("0x")) {
			try {
				return Integer.parseInt(string.substring(2), 16);
			}
			catch (NumberFormatException e) {
				throw new ValueConverterException("Couldn't convert '" + string + "' to int.", node, e);
			}
		}
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException e) {
			throw new ValueConverterException("Couldn't convert '" + string + "' to int.", node, e);
		}
	}

}

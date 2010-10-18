package de.unisb.prog.mips.parser.util;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;

public class MyConverters extends AbstractDeclarativeValueConverterService {

	@ValueConverter(rule = "HEXINT")
	public IValueConverter<Integer> HEXINT() {
		return new NumLiteralConverter();
	}

}

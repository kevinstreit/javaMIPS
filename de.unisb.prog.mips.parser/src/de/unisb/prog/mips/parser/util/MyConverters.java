package de.unisb.prog.mips.parser.util;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;
import org.eclipse.xtext.conversion.impl.STRINGValueConverter;

public class MyConverters extends AbstractDeclarativeValueConverterService {

	private static final IValueConverter<Integer> intConverter = new NumLiteralConverter();

	private static final IValueConverter<String> strConverter = new STRINGValueConverter();

	@ValueConverter(rule = "HEXINT")
	public IValueConverter<Integer> HEXINT() {
		return intConverter;
	}

	@ValueConverter(rule = "STRING")
	public IValueConverter<String> STRING() {
		return strConverter;
	}

}

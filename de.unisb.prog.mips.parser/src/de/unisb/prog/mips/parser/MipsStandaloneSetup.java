
package de.unisb.prog.mips.parser;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class MipsStandaloneSetup extends MipsStandaloneSetupGenerated{

	public static void doSetup() {
		new MipsStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}


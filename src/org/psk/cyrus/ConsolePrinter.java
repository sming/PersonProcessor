package org.psk.cyrus;

/**
 * The exercise specification required results be output to Console. However with a little
 * effort we can make the code easily extendable to output to other media e.g. file, GUI.
 * @author Pete
 */
class ConsolePrinter implements Publisher {

	@Override
	public void out(String txt) {
		System.out.println(txt);
	}

	@Override
	public void err(String txt) {
		System.err.println("Problem: "+txt);
	}

	@Override
	public void cleanUp() {
		// nothing to do
	}

	
}

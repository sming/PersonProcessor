/**
 * 
 */
package org.psk.cyrus;

/**
 * A factory that, given the first line of input, knows which Person Factory to create.
 * @author Pete
 */
public class PersonFactoryFactory {
	
	/**
	 * Given the line of data, determine the correct Person Factory to create. Note that
	 * ordering is important since CSV data also contains whitespace, which is a valid
	 * delimiter. Therefore the test for whitespace MUST come last.
	 * @param firstLine
	 * @return
	 */
	static IPersonFactory create(final String firstLine) {
		if (firstLine.contains(","))
			return new CSVPersonFactory();
		else if (firstLine.contains("|"))
			return new PipePersonFactory();
		else if (firstLine.contains(" "))	// NOTE: take care if re-ordering this
			return new WhitespacePersonFactory();
		else {
			Util.err("Couldn't determine the input file's delimiter. "
					+"Defaulting to using whitespace as the delimiter.");
			return new WhitespacePersonFactory();
		}
	}
}

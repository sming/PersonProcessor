/**
 * 
 */
package org.psk.cyrus;

import java.util.Arrays;
import java.util.List;

/**
 * A factory that takes w/s delimited data and creates Person objects from it. 
 * Naturally that means that we do not trim the w/s from the data as we process them.
 * @author Pete
 */
public class WhitespacePersonFactory extends AbstractPersonFactory {

	@Override
	public IPerson makePersonHook(List<String> flds) {
		// NOTE: field #2 Middle Initial is not processed
		return new Person(flds.get(0), flds.get(1), flds.get(3), flds.get(4),
				flds.get(5));
	}

	@Override
	public List<String> splitLineHook(String line) {
		String[] fields = line.split(" ");
		return Arrays.asList(fields);
	}

	boolean trimWhitespace() {
		return false;	// we don't want ws erased since that's our delim!
	}

}

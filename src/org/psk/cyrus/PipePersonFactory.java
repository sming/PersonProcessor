package org.psk.cyrus;

import java.util.Arrays;
import java.util.List;

/**
 * Factory for creating Person objects from pipe (i.e. the | character) delimited data.
 * @author Pete 
 */
public class PipePersonFactory extends AbstractPersonFactory {

	@Override
	public IPerson makePersonHook(List<String> flds) {
		// NOTE: field #2 Middle Initial is not processed and DOB & Fav Col
		// are transposed.
		return new Person(flds.get(0), flds.get(1), flds.get(3), flds.get(5),
				flds.get(4));
	}

	@Override
	public List<String> splitLineHook(String line) {
		// Note that pipe is a special character and must be escaped. That escape itself
		// must be escaped since it itself is special and would otherwise terminate the 
		// string.
		String[] fields = line.split("\\|");	
		return Arrays.asList(fields);
	}
}

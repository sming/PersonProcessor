/**
 * 
 */
package org.psk.cyrus;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Factory that takes CSV data and creates Person objects
 * @author Pete 
 */
public class CSVPersonFactory extends AbstractPersonFactory {

	@Override
	public IPerson makePersonHook(List<String> flds) {
		// NOTE: DOB & Fav Col are transposed.
		return new Person(flds.get(0), flds.get(1), flds.get(2), flds.get(4),
				flds.get(3));
	}
	
	@Override
	void initializeDateFormat() {
		// The CSV file 
		df = DateFormat.getDateInstance(DateFormat.SHORT);	
	}

	@Override
	public List<String> splitLineHook(String line) {
		String[] fields = line.split(",");
		return Arrays.asList(fields);
	}

}

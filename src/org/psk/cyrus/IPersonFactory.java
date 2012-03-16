/**
 * 
 */
package org.psk.cyrus;

import java.util.List;

/**
 * A Factory Interface for People object creation. subclasses are required to be able
 * to instantiate a Person object from a list of fields which itself is from
 * a single line of one of the input files. They also must know how to split
 * up an input line appropriately. 
 * @author Pete
 */
interface IPersonFactory {
	/**
	 * @param line single line from an input source that must be split into fields 
	 * @return list of fields from the input line
	 */
	List<String> splitLine(String line);

	/**
	 * @param flds data fields from which a Person object must be instantiated
	 * @return Person object which encapsulates the input fields
	 */
	IPerson makePerson(List<String> flds);
}

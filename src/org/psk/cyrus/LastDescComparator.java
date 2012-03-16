/**
 * 
 */
package org.psk.cyrus;

import java.util.Comparator;

/**
 * Used for imposing Last name ordering of outputs.
 * NOTE: sorts by First Name if Last name's are equal. This is usually preferable than 
 * relying on "natural" ordering.

 * @param <T> IPerson or subclass thereof
 * @author Pete
 */
public class LastDescComparator<T extends IPerson> implements Comparator<T> {

	@Override
	public int compare(IPerson p1, IPerson p2) {
		// Note that we're testing p2 against p1 which results in descending order
		int ret = p2.getLastName().compareTo(p1.getLastName());
		if (ret != 0)
			return ret;
		
		// This is not part of the spec but IMO defined sorting is better
		// than undefined/"natural" sorting.
		ret = p1.getFirstName().compareTo(p2.getFirstName());
		return ret;
	}

}

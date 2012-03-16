/**
 * 
 */
package org.psk.cyrus;

import java.util.Comparator;

/**
 * Used for imposing DOB ordering of outputs.
 * NOTE: sorts by Last Name if DOB's are equal. This is usually preferable than relying
 * on "natural" ordering.
 * 
 * @param <T> an IPerson or subclass thereof.
 * @author Pete
 */
public class DOBComparator<T extends IPerson> implements Comparator<T> {

	@Override
	public int compare(IPerson p1, IPerson p2) {
				
		int ret = p1.getDateDOB().compareTo(p2.getDateDOB());
		if (ret != 0)
			return ret;
		
		// This is not part of the spec but IMO defined sorting is better
		// than undefined/"natural" sorting.
		ret = p1.getLastName().compareTo(p2.getLastName());
		return ret;
	}

}

/**
 * 
 */
package org.psk.cyrus;

import java.util.Comparator;

/**
 * Used for imposing Gender, then Last name ordering of outputs.
 *
 * @param <T> - IPerson or a subclass thereof
 * @author Pete
 */
public class GenderLastComparator<T extends IPerson> implements Comparator<T> {

	@Override
	public int compare(IPerson p1, IPerson p2) {
		int ret = p1.getGender().compareTo(p2.getGender());
		if (ret != 0)
			return ret;
		
		ret = p1.getLastName().compareTo(p2.getLastName());
		return ret;
	}

}

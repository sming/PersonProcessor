package org.psk.cyrus;

import java.util.Date;

/**
 * Encapsulation of typical Person-al attributes such as DOB, Gender and Name.   
 * @author Pete
 */
public interface IPerson {
	abstract Date   getDateDOB();
	abstract String getDOB();
	abstract String getFavColor();
	abstract String getGender();
	abstract String getFirstName();
	abstract String getLastName();
	
	/**
	 * Exposed so that the Person factory can populate the Person's DOB
	 * @param dateDOB Date object that the factory has parsed and calculated
	 */
	abstract void setDateDOB(Date dateDOB);
	
	/**
	 * Render the Person object as required in the specification e.g.
	 * @return e.g. Kournikova Anna Female 6/3/1975 Red
	 */
	abstract String asOutputText();
	abstract String getFormattedGender();
}
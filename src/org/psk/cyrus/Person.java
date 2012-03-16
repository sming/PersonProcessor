package org.psk.cyrus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Entirely implements the IPerson interface.
 * @author Pete
 */
/**
 * 
 * @author Pete
 */
class Person implements IPerson {
	private String lastName, firstName, gender, DOB, favColor;
	private Date dateDOB = null;
	private static DateFormat outputDF = new SimpleDateFormat("M/d/yyyy");

	/**
	 * @param lastName
	 * @param firstName
	 * @param gender
	 * @param dOB
	 * @param favColor
	 */
	Person(String lastName, String firstName, String gender, String dOB,
			String favColor) {

		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.DOB = dOB;
		this.favColor = favColor;

		normalizeGender();
	}

	/**
	 * So far the set of Gender values are M,F,Male,Female. So it's adequate at
	 * present to take the first Character. If other values are encountered
	 * subsequently, this method can be overridden.
	 */
	private void normalizeGender() {
		gender = gender.substring(0, 1);
	}

	@Override
	public Date getDateDOB() {
		return dateDOB;
	}

	@Override
	public void setDateDOB(Date dateDOB) {
		this.dateDOB = dateDOB;
	}

	@Override
	public String toString() {
		return "Person ["
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (gender != null ? "gender=" + gender + ", " : "")
				+ (DOB != null ? "DOB=" + getDateDOB() + ", " : "")
				+ (favColor != null ? "favColor=" + favColor : "") + "]";
	}

	@Override
	public String asOutputText() {
		return getLastName() + " " + getFirstName() + " " + getFormattedGender() + " "
				+ getFormattedDOB() + " " + getFavColor();
	}

	/**
	 * Gender is stored as M or F. Output is required to be Female or Male.
	 * @return Male or Female. "Unknown" is returned in an error scenario.
	 */
	@Override
	public String getFormattedGender() {
		if (gender.charAt(0) == 'F')
			return "Female";
		if (gender.charAt(0) == 'M')
			return "Male";
		
		Util.err("Unexpected value in Gender field, returning Unknown");
		return "Unknown";
	}

	private String getFormattedDOB() {
		return outputDF.format(dateDOB);
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getGender() {
		return gender;
	}

	@Override
	public String getFavColor() {
		return favColor;
	}

	@Override
	public String getDOB() {
		return DOB;
	}
}

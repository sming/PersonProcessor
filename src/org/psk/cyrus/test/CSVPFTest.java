package org.psk.cyrus.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.psk.cyrus.CSVPersonFactory;
import org.psk.cyrus.IPerson;

/**
 * Last Name, First Name, Gender, Favorite Color, Date Of Birth
Abercrombie, Neil, Male, Tan, 2/13/1943
Bishop, Timothy, Male, Yellow, 4/23/1967
Kelly, Sue, Female, Pink, 7/12/1959
 * @author Pete
 *
 */
public class CSVPFTest {
	
	static org.psk.cyrus.CSVPersonFactory fac;
	private static List<String> perArr1;
	private static List<String> perArr2;
	private static List<String> perArr3;
	private static IPerson per1;
	private static IPerson per2;
	private static IPerson per3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setUpBeforeClass()");
		
		fac = new CSVPersonFactory();
		
		perArr1 = fac.splitLine("Abercrombie, Neil, Male, Tan, 2/13/1943");
		perArr2 = fac.splitLine("Bishop, Timothy, Male, Yellow, 4/23/1967");
		perArr3 = fac.splitLine("Kelly, Sue, Female, Pink, 7/12/1959");
		
		per1 = fac.makePerson(perArr1);
		per2 = fac.makePerson(perArr2);
		per3 = fac.makePerson(perArr3);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPersonCreation() {
		System.out.println("test()");
		assertEquals(per1.getDOB(), "2/13/1943");
		assertEquals(per1.getFavColor(), "Tan");
		assertEquals(per1.getFirstName(), "Neil");
		assertEquals(per1.getFormattedGender(), "Male");
		assertEquals(per1.getLastName(), "Abercrombie");
		
		assertEquals(per2.getDOB(), "4/23/1967");
		assertEquals(per2.getFavColor(), "Yellow");
		assertEquals(per2.getFirstName(), "Timothy");
		assertEquals(per2.getFormattedGender(), "Male");
		assertEquals(per2.getLastName(), "Bishop");
		
		assertEquals(per3.getDOB(), "7/12/1959");
		assertEquals(per3.getFavColor(), "Pink");
		assertEquals(per3.getFirstName(), "Sue");
		assertEquals(per3.getFormattedGender(), "Female");
		assertEquals(per3.getLastName(), "Kelly");
	}
}

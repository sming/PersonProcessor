package org.psk.cyrus.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.psk.cyrus.PersonProcessor;

public class OutputTest {

	/**
	 * dump the Person objects to file, parse as strings, then compare to the model output.
	 * This gives us a high-level, empirical test of the whole program.
	 */
	@Test
	public void testModelActualOutput() {
		String[] args = { "domain_files\\comma.txt", "domain_files\\pipe.txt",
				"domain_files\\space.txt", "output-to-file" };

		PersonProcessor pp = new PersonProcessor(args);
		pp.start(); // this dumps the Person's to output.txt

		diffFiles();
	}

	/**
	 * Note that this does string comparison which would not scale well for large numbers
	 * of Person objects but it serves our needs just fine.
	 */
	private void diffFiles() {
		String output = readFileIntoString("output.txt");
		String model = readFileIntoString("domain_files\\model_output.txt");
		assertEquals(output, model);
	}

	/**
	 * Open the supplied file for reading and slurp it into a string
	 * @param fileName name of the file for reading from
	 * @return concatenated string of the file's contents.
	 */
	public String readFileIntoString(String fileName) {
		FileInputStream fActual;
		
		try {
			fActual = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			org.junit.Assert.fail(fileName+" not found");
			return "";
		}
		
		BufferedReader myInput = new BufferedReader
	              (new InputStreamReader(fActual));
		StringBuilder sb = new StringBuilder();
		
		String thisLine = null;
		try {
			while ((thisLine= myInput.readLine()) != null) {
				sb.append(thisLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			org.junit.Assert.fail("couldn't read from file");
		}
		
		return sb.toString();
	}

}

package org.psk.cyrus;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Output the master list to file. Useful for testing.
 * 
 * @author Pete
 */
class FilePrinter implements Publisher {
	
	static private String fileName = new String("output.txt");

	private FileWriter fw;

	/**
	 * Constructors shouldn't throw but in this case, I want the program to exit
	 * since it's fatal so having a malformed object around for a brief period
	 * is not important.
	 * 
	 * @throws IOException
	 *             file couldn't be opened for writing
	 */
	FilePrinter() throws IOException {
		super();
		
		try {
			fw = new FileWriter(fileName, false);
		} catch (IOException e) {
			System.err.println("Couldn't create "+fileName+" for writing: "+e.getMessage());
			throw e;
		}
	}

	@Override
	public void out(String txt) {
		try {
			fw.append(txt+"\n");
		} catch (IOException e) {
			err("Couldn't write to "+fileName);
		}
	}

	@Override
	public void err(String txt) {
		System.err.println("Problem: "+txt+"\n");
	}

	@Override
	public void cleanUp() {
		try {
			fw.close();
		} catch (IOException e) {
			err("Info: Couldn't close file");
		}
	}

}

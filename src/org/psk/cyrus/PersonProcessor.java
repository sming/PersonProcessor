package org.psk.cyrus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * "Driver" / "main" application class which initiates and controls the
 * file-reading, parsing, Person-creating and Person outputting process.
 * 
 * @author Pete
 */
public class PersonProcessor {
	private ArrayList<String> args;
	private IPersonFactory fac;
	private List<IPerson> masterList; // of instantiated Person objects
	private boolean toFile = false;

	/**
	 * simple driver routine
	 * 
	 * @param args
	 *            parameters at invocation
	 */
	public static void main(String args[]) {
		PersonProcessor pp = new PersonProcessor(args);
		pp.start();
	}

	/**
	 * release any resources consumed.
	 */
	private void cleanup() {
		Util.pub.cleanUp();
	}

	/**
	 * One PersonProcessor is created per execution run. The master Person list
	 * implementation and mechanism of output publication are intended to be
	 * easily extendable.
	 * 
	 * @param args
	 */
	public PersonProcessor(String[] args) {
		super();
		processArgs(args);

		buildMasterList();

		buildOutputter();
	}

	/**
	 * If the "output to file" flag has been passed, we want to both record that fact and
	 * remove the flag from our arg list.
	 * @param args
	 */
	private void processArgs(String[] args) {
		this.args = new ArrayList<String>(Arrays.asList(args));
		toFile = this.args.remove("output-to-file");
	}

	/**
	 * In a larger system, the Publisher would be built via Dependency Injection
	 * mechanism or similar.
	 */
	void buildOutputter() {
		if (Util.pub == null) {
			if (toFile) {
				try {
					Util.pub = new FilePrinter();
				} catch (IOException e) {
					System.err.println("Couldn't create output file for writing, "+
				"outputting to console instead.");
					Util.pub = new ConsolePrinter();
				}
			} else {
				Util.pub = new ConsolePrinter();
			}
		}
	}

	/**
	 * In a larger system, the master list's implementation (e.g. Set, Map,
	 * List) would be built/chosen via Dependency Injection mechanism or
	 * similar.
	 */
	void buildMasterList() {
		masterList = new ArrayList<IPerson>();
	}

	/**
	 * check we have enough inputs then sequentially process them: open, read,
	 * parse then finally output them all together.
	 */
	public void start() {
		if (!validateInputs())
			return;

		for (String arg : args)
			processInput(arg);

		output();
		
		cleanup();
	}

	/**
	 * Spit the assembled Person master list out in the ordering permutations as
	 * specified in the coding challenge spec.
	 */
	private void output() {
		List<Comparator<IPerson>> compList = new ArrayList<Comparator<IPerson>>();
		compList.add(new GenderLastComparator<IPerson>());
		compList.add(new DOBComparator<IPerson>());
		compList.add(new LastDescComparator<IPerson>());

		output(compList);
	}

	/**
	 * For each ordering (comparator), sort then spit out the master list.
	 * 
	 * @param compList
	 *            list of comparators to sort the master list by.
	 */
	private void output(List<Comparator<IPerson>> compList) {
		int count = 0;
		for (Comparator<IPerson> comp : compList) {
			++count;

			Collections.sort(masterList, comp);

			Util.out("Output " + count + ":");
			outputMasterList();
		}
	}

	/**
	 * spit the Person's out according to the current sort order
	 */
	private void outputMasterList() {
		for (IPerson per : masterList)
			Util.out(per.asOutputText());

		Util.out(""); // empty line separates the outputs
	}

	/**
	 * processInput binds this PersonProcessor to processing files. In order to
	 * make it more flexible, typically this method would employ Dependency
	 * Injection so that this class could become ignorant of the mechanics of
	 * fetching the data and the kind of media (in this case it's file-based).
	 * 
	 * Read the file line-by-line, creating a Person object from each line.
	 * 
	 * @param fileName
	 *            input file to be read
	 */
	public void processInput(String fileName) {

		FileReader fr = null;

		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			Util.err("the file named " + fileName
					+ " could not be opened for reading."
					+ " The error encountered was: " + e.getMessage());
			return;
		}

		Scanner lineScanner = new Scanner(fr);
		while (lineScanner.hasNextLine()) {
			processLine(lineScanner.nextLine());
		}

		// we're finished with this file. The next file [if there is one]
		// could have a different delimiter. Hence reset it [and make current
		// factory object eligible for GC].
		fac = null;
	}

	/**
	 * Use the PersonFactory factory to create the correct PersonFactory. Use
	 * the PersonFactory to correctly split out the fields and then to create a
	 * Person object. Lastly add the Person to the master list.
	 * 
	 * @param line
	 */
	private void processLine(String line) {
		if (fac == null)
			fac = PersonFactoryFactory.create(line);

		List<String> l = fac.splitLine(line);

		IPerson per = null;
		try {
			per = fac.makePerson(l);
		} catch (ArrayIndexOutOfBoundsException e) {
			Util.err("unexpected number of fields in line: " + line
					+ " Unexpected field index: " + e.getMessage());
			return; // don't add this person to the master list
		}

		masterList.add(per);
	}

	/**
	 * Simple test that we have at least one input file
	 * 
	 * @return true if no args have been supplied, else false
	 */
	private boolean validateInputs() {
		if (args.size() < 1) {
			Util.out("Usage: PersonProcessor <file 1 [ file 2, file 3 ...] >");
			Util.out("Usage: include path to file name e.g. domain_files\\pipe.txt");
			return false;
		}

		return true;
	}
}

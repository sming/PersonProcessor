/**
 * 
 */
package org.psk.cyrus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class that provides functionality common to the process of instantiating
 * Person objects such as trimming whitespace and capturing DOB information.
 * 
 * Subclasses can specialize the Person instantiation procedure by implementing
 * makePersonHook(). This is an implementation of the Template Method pattern.
 * 
 * @author Pete
 */
abstract public class AbstractPersonFactory implements IPersonFactory {
	// pat matches all whitespace
	private Pattern pat = Pattern.compile("\\s");

	// df can be instantiated by subclasses, hence protected visibility
	protected DateFormat df = null;

	static private Date DEFAULT_DOB = null;

	static protected Date getDefaultDOB() {
		if (DEFAULT_DOB == null) {
			try {
				DEFAULT_DOB = DateFormat.getInstance().parse("01/01/2000");
			} catch (ParseException e) {
				// We're in big trouble if we get here. Warn the user best as
				// poss.
				Util.err("couldn't obtain a default date, which is most unexpected");
				e.printStackTrace();
			}
		}
		return DEFAULT_DOB;
	}

	/**
	 * Template Method pattern which allows subclasses to specialize Person
	 * creation. For example, the fields are in different orders depending on
	 * the file's delimiter.
	 * 
	 * @param flds
	 *            input data fields, from a file for example
	 * @return a new Person object
	 */
	abstract IPerson makePersonHook(List<String> flds);

	@Override
	public IPerson makePerson(List<String> flds) {
		IPerson per = makePersonHook(flds);

		if (df == null) // different inputs have different date formats
			initializeDateFormat();

		populateDateDOB(per); // parse the textual DOB into a Date

		return per;
	}

	/**
	 * Attempt to parse the textual date field into a java.util.Date. If this
	 * fails, the Date is defaulted to DEFAULT_DOB.
	 * 
	 * @param per
	 */
	void populateDateDOB(IPerson per) {
		String txtDOB = per.getDOB();

		try {
			per.setDateDOB(df.parse(txtDOB));
		} catch (ParseException e) {
			Util.err("DOB of " + txtDOB + " could not be parsed. "
					+ "Setting DOB to " + getDefaultDOB());
			per.setDateDOB(getDefaultDOB());
		}
	}

	/**
	 * "Most" input formats supplied so far use this date format so provide it
	 * as default behaviour.
	 */
	void initializeDateFormat() {
		df = new SimpleDateFormat("M-d-yyyy");
	}

	@Override
	public List<String> splitLine(String line) {
		String trimmedLine = null;

		// only strip the w/s if the factory says so
		if (trimWhitespace()) {
			Matcher mat = pat.matcher(line);
			trimmedLine = mat.replaceAll("");
		} else {
			trimmedLine = line;
		}

		return splitLineHook(trimmedLine); // let the subclass specialize
											// behaviour
	}

	/**
	 * @return yes: remove all w/s. no: don't
	 */
	boolean trimWhitespace() {
		return true; // most delimited files would want this behaviour
	}

	/**
	 * This is an implementation of the Template Method pattern. The steps to be
	 * taken are specified here but subclasses define those steps.
	 * 
	 * @param trimmedLine
	 * @return
	 */
	abstract List<String> splitLineHook(String trimmedLine);

}

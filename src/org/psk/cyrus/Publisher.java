/**
 * 
 */
package org.psk.cyrus;

/**
 * Interface for communicating the outputs. Could be to console, file or any other 
 * resource.  
 * @author Pete
 */
interface Publisher {
	/**
	 * err is used to communicate error information i.e. when something goes wrong
	 * @param txt the text to communicate
	 */
	void err(String txt);
	
	/**
	 * out is used to communicate non-error info including the resulting Person data
	 * @param txt the text to communicate
	 */
	void out(String txt);
	
	/**
	 * clean up any used resources
	 */
	void cleanUp();
}

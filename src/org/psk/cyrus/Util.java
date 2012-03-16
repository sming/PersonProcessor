package org.psk.cyrus;

/**
 * Utility class. Currently used for logging purposes only. Brevity in usage has been 
 * preferred over strict encapsulation (e.g. pub is not private).
 * @author Pete
 */
public class Util {
	static Publisher pub = null;	// instantiated immediately after start up
	
	/**
	 * @param text message to communicate out
	 */
	static void out(String text) {
		pub.out(text);
	}
	
	/**
	 * @param text error message to communicate out
	 */
	static void err(String text) {
		pub.err(text);
	}
}

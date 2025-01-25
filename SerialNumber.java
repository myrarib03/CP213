package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Myra Ribeiro 169030590
 * @version 2024-09-01
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {
	// your code here
	boolean digits = true;
	int count = 0;
	while (count < str.length() && digits == true) {
	    char currentchar = str.charAt(count);
	    if (!Character.isDigit(currentchar)) {
		digits = false;
	    }
	    count += 1;
	}
	return digits;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {
	// your code here
	boolean answer = true;
	if (sn == null || sn.length() != 11) {
	    answer = false;
	}
	if (!sn.startsWith("SN/")) {
	    answer = false;
	}
	for (int i = 3; i < 7; i++) {
	    if (!Character.isDigit(sn.charAt(i))) {
		answer = false;
	    }
	}
	if (sn.charAt(7) != '-') {
	    answer = false;
	}
	for (int i = 8; i < 10; i++) {
	    if (!Character.isDigit(sn.charAt(i))) {
		answer = false;
	    }
	}
	return answer;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {
	// your code here
	while (fileIn.hasNextLine()) {
	    String sn = fileIn.nextLine().trim();
	    if (validSn(sn)) {
		goodSns.println(sn);
	    } else {
		badSns.println(sn);
		return;
	    }
	}
    }
}

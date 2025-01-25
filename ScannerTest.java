package cp213;

import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author your name here
 * @version 2022-01-08
 */
public class ScannerTest {

    /**
     * Count lines in the scanned file.
     *
     * @param source Scanner to process
     * @return number of lines in scanned file
     */
    public static int countLines(final Scanner source) {
	int count = 0;
	// your code here
	while (source.hasNextLine()) {
	    count += 1;
	    source.nextLine();
	}
	return count;
    }

    /**
     * Count tokens in the scanned object.
     *
     * @param source Scanner to process
     * @return number of tokens in scanned object
     */
    public static int countTokens(final Scanner source) {
	int tokens = 0;
	// your code here
	while (source.hasNext()) {
	    tokens += 1;
	    source.next();
	}
	return tokens;
    }

    /**
     * Ask for and total integers from the keyboard.
     *
     * @param source Scanner to process
     * @return total of positive integers entered from keyboard
     */
    public static int readNumbers(final Scanner keyboard) {
	int total = 0;
	String input = ""; // This will store the user's input

	System.out.println("Please enter an int or 'q' to quit: ");

	// Loop until the user enters "q"
	while (!input.equals("q")) {
	    if (keyboard.hasNextInt()) {
		// If the next input is an integer, add it to the total
		total += keyboard.nextInt();
	    } else {
		// If the next input is not an integer, read it as a string
		input = keyboard.next();

		// If it's not "q", print an error message
		if (!input.equals("q")) {
		    System.out.println(input + " is not an integer. Please enter an int or 'q' to quit: ");
		}
	    }
	}
	return total; // Return the total sum of integers entered
    }
}

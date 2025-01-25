package cp213;

/**
 * @author Myra Ribeiro 169030590
 * @version 2024-09-01
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {
	int shift = n % ALPHA_LENGTH;
	StringBuilder enciphered = new StringBuilder();
	for (int i = 0; i < s.length(); i++) {
	    char currentChar = s.charAt(i);
	    if (Character.isLetter(currentChar)) {
		char upperChar = Character.toUpperCase(currentChar);
		int originalPos = ALPHA.indexOf(upperChar);
		int newPos = (originalPos + shift + ALPHA_LENGTH) % ALPHA_LENGTH;
		enciphered.append(ALPHA.charAt(newPos));
	    } else {
		enciphered.append(currentChar);
	    }
	}
	return enciphered.toString();
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {
	// your code here
	if (ciphertext.length() != 26) {
	    throw new IllegalArgumentException("Ciphertext must be 26 characters long.");
	}
	StringBuilder substituted = new StringBuilder();
	for (int i = 0; i < s.length(); i++) {
	    char currentChar = s.charAt(i);
	    if (Character.isLetter(currentChar)) {
		char upperChar = Character.toUpperCase(currentChar);
		int originalPos = ALPHA.indexOf(upperChar);
		substituted.append(ciphertext.charAt(originalPos));
	    } else {
		substituted.append(currentChar);
	    }
	}
	return substituted.toString();
    }

}
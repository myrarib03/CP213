package cp213;

/**
 * @author Myra Ribeiro 169030590
 * @version 2024-09-01
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {
	// your code here
	String cleaned = "";
	String reversed = "";
	for (int i = 0; i < string.length(); i++) {
	    char charact = string.charAt(i);
	    if (Character.isLetter(charact)) {
		cleaned += Character.toLowerCase(charact);
	    }
	}
	for (int i = cleaned.length() - 1; i >= 0; i--) {
	    reversed += cleaned.charAt(i);
	}
	boolean palindrome = true;
	palindrome = cleaned.equals(reversed);
	return palindrome;
    }

    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {
	// your code here
	boolean outcome = true;

	if (name == null || name.isEmpty()) {
	    outcome = false;
	}
	char firstChar = name.charAt(0);

	if (!(Character.isLetter(firstChar) || firstChar == '_')) {
	    outcome = false;
	}
	if (name.equals("_")) {
	    outcome = false;
	}
	for (int i = 1; i < name.length(); i++) {
	    char currentChar = name.charAt(i);
	    if (!(Character.isLetter(currentChar) || Character.isDigit(currentChar) || currentChar == '_')) {
		outcome = false;
	    }
	}
	return outcome;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
	String result = "";
	if (word == null || word.isEmpty()) {
	    result = word;

	} else {
	    String vowels = "AEIOUaeiou";
	    boolean isFirstUpperCase = Character.isUpperCase(word.charAt(0));
	    String lowerWord = word.toLowerCase();
	    if (vowels.indexOf(lowerWord.charAt(0)) != -1) {
		result = lowerWord + "way";

	    } else {
		int index = 0;
		while (index < lowerWord.length() && !vowels.contains("" + lowerWord.charAt(index))
			&& !(index > 0 && lowerWord.charAt(index) == 'y')) {
		    index++;
		}

		String consonantPrefix = lowerWord.substring(0, index);
		String restOfWord = lowerWord.substring(index);
		result = restOfWord + consonantPrefix + "ay";
	    }

	    if (isFirstUpperCase) {
		result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
	    }
	}
	return result;
    }
}
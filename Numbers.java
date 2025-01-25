package cp213;

/**
 * @author Myra Ribeiro 169030590
 * @version 2024-09-01
 */
public class Numbers {

    /**
     * Determines closest value of two values to a target value.
     *
     * @param target the target value
     * @param v1     first comparison value
     * @param v2     second comparison value
     * @return one of v1 or v2 that is closest to target, v1 is the value chosen if
     *         v1 and v2 are an equal distance from target
     */
    public static double closest(final double target, final double v1, final double v2) {
	// your code here
	double dist1 = (target - v1) < 0 ? -(target - v1) : (target - v1);
	double dist2 = (target - v2) < 0 ? -(target - v2) : (target - v2);
	double answer = v1;
	if (dist2 < dist1) {
	    answer = v2;
	} else {
	    answer = v1;
	}
	return answer;
    }

    /**
     * Determines if n is a prime number. Prime numbers are whole numbers greater
     * than 1, that have only two factors - 1 and the number itself. Prime numbers
     * are divisible only by the number 1 or itself.
     *
     * @param n an integer
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrime(final int n) {
	// your code here
	boolean answer = true;
	if (0 > n) {
	    answer = false;
	} else {
	    int count = 2;
	    while ((count < ((n / 2) + 1)) && (answer == true)) {
		if ((n % count) == 0) {
		    answer = false;
		}
		count += 1;
	    }
	}
	return answer;
    }

    /**
     * Sums and returns the total of a partial harmonic series. This series is the
     * sum of all terms 1/i, where i ranges from 1 to n (inclusive). Ex:
     *
     * n = 3: sum = 1/1 + 1/2 + 1/3 = 1.8333333333333333
     *
     * @param n an integer
     * @return sum of partial harmonic series from 1 to n
     */
    public static double sumPartialHarmonic(final int n) {
	// your code here
	int count = 1;
	float total = 0;
	while (count <= n) {
	    total += ((float) 1 / (float) count);
	    count += 1;
	}

	return total;
    }

}

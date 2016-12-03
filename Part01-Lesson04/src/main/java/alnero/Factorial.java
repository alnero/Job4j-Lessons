package alnero;

/**
 * Class with a method to calculate factorial using loops.
 */
public class Factorial {

    /**
     * Returns factorial of the supplied parameter.
     * @param n number for which factorial is calculated
     * @return factorial of the supplied number
     * @throws IllegalArgumentException when supplied parameter is a negative number
     */
    public int getFactorialOf(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial for negative numbers is not defined!");
        }

        int result = 1;

        for (int i = 1; i <= n; i++) {
            result = result * i;
        }

        return result;
    }
}

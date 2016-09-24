package alnero;

/**
 * Class for simple calculations: add, subtract, multiply and divide.
 * Precision of calculations is 8 decimal digits.
 * Division by zero throws an exception.
 */
public class Calculator {
    /**
     * Precision of calculations, number of decimal digits in calculation results.
     */
    private static final int NUMBER_OF_DECIMALS = 8;

    /**
     * Variable to store the result of calculations, initialized by zero.
     */
    private double result = 0.0;

    /**
     * Performs addition.
     * @param a first argument
     * @param b second argument
     */
    public void add(double a, double b) {
        this.result = a + b;
    }

    /**
     * Performs subtraction.
     * @param a first argument
     * @param b second argument
     */
    public void subtract(double a, double b) {
        this.result = a - b;
    }

    /**
     * Performs multiplication
     * @param a first argument
     * @param b second argument
     */
    public void multiply(double a, double b) {
        this.result = a * b;
    }

    /**
     * Performs division
     * @param a first argument
     * @param b second argument
     * @throws IllegalArgumentException is thrown when divisor is zero.
     */
    public void divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisor can't be zero.");
        } else {
            this.result = a / b;
        }
    }

    /**
     * Get the result rounded to certain decimal places.
     * @return rounded result of calculations
     */
    public double getResult() {
        return roundResultToDecimalPlaces(NUMBER_OF_DECIMALS);
    }

    /**
     * Performs rounding of the result of calculations.
     * @param numOfDecimal number of digits after the decimal
     * @return result rounded to the specified number of decimal places
     */
    private double roundResultToDecimalPlaces(int numOfDecimal) {
        double multiplier = Math.pow(10, numOfDecimal);
        return Math.round(this.result * multiplier) / multiplier;
    }

}
package alnero;

import alnero.output.ConsoleOutput;
import alnero.output.Output;

/**
 * Class to calculate function values for the quadratic equation y = a * x^2 + b * x + c.
 */
public class Square {
    /**
     * Precision of calculations, number of decimal digits in calculation results.
     */
    private static final int NUMBER_OF_DECIMALS = 1;

    /**
     * Variable hold reference to main output system used by the class.
     * On instantiation the default output will be to system console.
     */
    private Output output = new ConsoleOutput(System.out);

    /**
     * Variables hold main coefficients of the quadratic equation.
     */
    /** a coefficient. */
    private float a;
    /** b coefficient. */
    private float b;
    /** c coefficient. */
    private float c;

    /**
     * Receives main coefficients for the quadratic equation a, b, c.
     * @param a a coefficient
     * @param b b coefficient
     * @param c c coefficient
     */
    public Square(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Method to set main output system used by the class.
     * @param output Output object, performing printouts to output
     */
    public void setOutput(Output output) {
        this.output = output;
    }

    /**
     * Calculate value of the function y = a * x^2 + b * x + c with predefined coefficients for the supplied argument.
     * @param x argument for which value of the function will be calculated
     * @return calculated value of the function
     */
    public float calculate(float x) {
        float result = this.a * (float) Math.pow(x, 2) + this.b * x + this.c;
        return roundToDecimalPlaces(result, NUMBER_OF_DECIMALS);
    }

    /**
     * Method to print to output results of function calculation on section from start value to finish value with defined step.
     * Calculations for start and finish values are included in printout: [start, finish]
     * Finish value is always grater then start value.
     * Step is always taken by absolute value.
     * @param start start argument
     * @param finish finish argument
     * @param step step of calculations
     */
    public void show(float start, float finish, float step) {
        // step is always positive
        step = Math.abs(step);

        // finish is always greater then start
        if (start > finish) {
            float temp = start;
            start = finish;
            finish = temp;
        }

        StringBuilder dataStr = new StringBuilder();

        String delimiter = "";
        for (float i = start; i < finish; i += step) {
            float value = this.calculate(i);
            dataStr.append(delimiter).append(value);
            delimiter = "  ";
        }

        // function value for finish argument is always included
        dataStr.append(delimiter).append(this.calculate(finish));

        output.println(dataStr);
    }

    /**
     * Performs rounding of the result of calculations.
     * @param value number to be rounded
     * @param numOfDecimal number of digits after the decimal
     * @return result rounded to the specified number of decimal places
     */
    private float roundToDecimalPlaces(float value, int numOfDecimal) {
        float multiplier = (float) Math.pow(10, numOfDecimal);
        return Math.round(value * multiplier) / multiplier;
    }
}

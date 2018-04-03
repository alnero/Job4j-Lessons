package alnero;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Class with handy methods for console inputs, implements common interface for Tracker inputs.
 */
public class ConsoleInput implements TrackerInput {
    /**
     * Common Scanner object for reading input from console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Read line entered in console.
     * @return string line entered in console
     */
    public String readInputLine() {
        return this.scanner.nextLine();
    }

    /**
     * Return line entered in console. Only predefined values accepted as input,
     * for the rest method throws exception.
     * @param inputRangeOfStringValues accepted for input range of values
     * @return string line entered in console
     */
    @Override
    public String readInputLine(String[] inputRangeOfStringValues) {
        String userInput = this.scanner.nextLine();

        if (Arrays.asList(inputRangeOfStringValues).contains(userInput)) {
            return userInput;
        }

        // if user input is not within allowed values
        throw new OutOfInputRangeValuesException("Out of input range values exception.");
    }

    /**
     * Read id entered in console. Id is a long number, so before reading method checks for long in console.
     * @return entered id (long number) or -1 if chunk entered in console is not a long number
     */
    public long readInputId() {
        long result = -1;

        if (this.scanner.hasNextLong()) {
            result = Long.parseLong(this.scanner.nextLine());
        }

        return result;
    }

    /**
     * Check if positive response was given in console for yes/no question.
     * Entering "y" of "Y" counts for positive response, else inputs are negative by default.
     * @return true if "y" or "Y" entered in console, false in all other cases
     */
    public boolean positiveAnswerGiven() {
        String userYesNoInput = this.scanner.nextLine().toLowerCase();

        boolean result = false;

        if ("y".equals(userYesNoInput)) {
            result = true;
        }

        return result;
    }
}

package alnero.decoratorPattern;

import alnero.OutOfInputRangeValuesException;
import alnero.TrackerInput;

import java.util.NoSuchElementException;

/**
 * Check user input for menu items, loop is used until input is correct.
 * Implemented via decorator pattern.
 */
public class ValidateMenuInput implements TrackerInput {
    /**
     * Input system used in Tracker app.
     */
    private TrackerInput input;

    /**
     * Input system used in Tracker app is defined in constructor.
     * @param input input system to be validated
     */
    public ValidateMenuInput(TrackerInput input) {
        this.input = input;
    }

    @Override
    public String readInputLine() {
        return this.input.readInputLine();
    }

    /**
     * Return line entered in console.
     * We use method of input system to get input
     * but handle some possible exceptions here.
     * @param inputRangeOfStringValues accepted for input range of values
     * @return string line entered in console
     */
    @Override
    public String readInputLine(String[] inputRangeOfStringValues) {
        String userInput;

        while (true) {
            try {
                userInput = this.input.readInputLine(inputRangeOfStringValues);
                break;
                // user inputs wrong numbers or even strings for menu items
            } catch (OutOfInputRangeValuesException e) {
                System.out.println("Please select correct key from Menu.");
                // if no line was found
            } catch (NoSuchElementException e) {
                System.err.println(e);
            }
        }

        return userInput;
    }

    @Override
    public long readInputId() {
        return this.input.readInputId();
    }

    @Override
    public boolean positiveAnswerGiven() {
        return this.input.positiveAnswerGiven();
    }
}
package alnero.handlingExceptions;

import alnero.ConsoleInput;
import alnero.OutOfInputRangeValuesException;

import java.util.NoSuchElementException;

/**
 * Check user input for menu items, loop is used until input is correct.
 */
public class ValidateMenuInput extends ConsoleInput {
    /**
     * Return line entered in console.
     * We use parent method to get input but handle some possible exceptions here.
     * @param inputRangeOfStringValues accepted for input range of values
     * @return string line entered in console
     */
    @Override
    public String readInputLine(String[] inputRangeOfStringValues) {
        String userInput;
        while (true) {
            try {
                userInput = super.readInputLine(inputRangeOfStringValues);
                break;
            } catch (OutOfInputRangeValuesException e) {
                System.out.println("Please select correct key from Menu.");
            } catch (NoSuchElementException e) {
                System.err.println(e);
            }
        }
        return userInput;
    }
}

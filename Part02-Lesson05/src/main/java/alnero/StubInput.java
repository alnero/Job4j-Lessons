package alnero;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Class with methods to supply automatic inputs for testing, implements common interface for Tracker inputs.
 */
public class StubInput implements TrackerInput {

    /** Counter for input lines. */
    private int stubInputLineCounter = 0;
    /** Counter for input ids. */
    private int stubInputIdCounter = 0;
    /** Counter for input yes/no answers.    */
    private int stubInputYesNoAnswerCounter = 0;

    /** Array with stub input lines. */
    private String[] stubInputLine;
    /** Array with stub input ids. */
    private String[] stubInputId;
    /** Array with stub input yes/no answers. */
    private String[] stubYesNoAnswer;

    /**
     * Constructor for stub input class.
     * @param stubInput HashMap object with relevant arrays for stub inputs: lines, ids, yes/no answers
     */
    public StubInput(HashMap<String, String[]> stubInput) {
        this.stubInputLine = stubInput.get("stubInputLine");
        this.stubInputId = stubInput.get("stubInputId");
        this.stubYesNoAnswer = stubInput.get("stubYesNoAnswer");
    }

    /**
     * Supply lines from relevant stub input array.
     * @return string line from stub input array
     */
    public String readInputLine() {
        return stubInputLine[stubInputLineCounter++];
    }

    /**
     * Supply lines from relevant stub input array.
     * Only predefined values accepted as input,
     * for the rest method throws exception.
     * @param inputRangeOfStringValues accepted for input range of values
     * @return string line from stub input array
     */
    @Override
    public String readInputLine(String[] inputRangeOfStringValues) {
        String userInput = stubInputLine[stubInputLineCounter++];

        if (Arrays.asList(inputRangeOfStringValues).contains(userInput)) {
            return userInput;
        }

        throw new OutOfInputRangeValuesException("Out of input range values exception.");
    }

    /**
     * Supply id from relevant stub input array.
     * Id is a long number, so method tries to parse string from array into a long number.
     * @return stub id (long number) or -1 if string from array is not parsable into a long number
     */
    public long readInputId() {
        long result = -1;

        try {
            result = Long.parseLong(stubInputId[stubInputIdCounter++]);
        } catch (Exception e) {
            /*NOP*/
        }

        return result;
    }

    /**
     * Check if the stub input from yes/no array is positive.
     * Strings "y" of "Y" count for positive response, else stub inputs are negative by default.
     * @return true if "y" or "Y" are supplied from stub input, false in all other cases
     */
    public boolean positiveAnswerGiven() {
        String yesNoStubInput = stubYesNoAnswer[stubInputYesNoAnswerCounter++];

        boolean result = false;

        if ("y".equals(yesNoStubInput.toLowerCase())) {
            result = true;
        }

        return result;
    }
}

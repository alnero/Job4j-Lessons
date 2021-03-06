package alnero;

/**
 * Abstraction of input system for the Tracker app.
 */
public interface TrackerInput {
    /**
     * Read input line.
     * @return string representation of input line
     */
    String readInputLine();

    /**
     * Read input line within only accepted range of values.
     * @param inputRangeOfStringValues accepted for input range of values
     * @return string representation of input line
     */
    String readInputLine(String[] inputRangeOfStringValues);

    /**
     * Read input id. Id is a long number.
     * @return input id (long number) or any special values
     */
    long readInputId();

    /**
     * Check if positive response was given for yes/no question.
     * @return true or false depending on what counts for positive input
     */
    boolean positiveAnswerGiven();
}
package alnero;

/**
 * Exception if input is not within allowed values.
 */
public class OutOfInputRangeValuesException extends RuntimeException {
    /**
     * Constructor receives info about exception.
     * @param msg info or message about exception
     */
    public OutOfInputRangeValuesException(String msg) {
        super(msg);
    }
}

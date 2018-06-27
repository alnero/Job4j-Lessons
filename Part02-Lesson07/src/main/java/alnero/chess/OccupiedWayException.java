package alnero.chess;

/**
 * Exception if there is another figure on the way.
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Constructor receives info about exception.
     * @param msg info or message about exception
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}

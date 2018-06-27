package alnero.chess;

/**
 * Exception if such move is impossible for figure.
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Constructor receives info about exception.
     * @param msg info or message about exception
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}

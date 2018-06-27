package alnero.chess;

/**
 * Exception if figure for move is not found.
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Constructor receives info about exception.
     * @param msg info or message about exception
     */
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
package alnero.chess.figures.black;

import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * Black Queen.
 */
public class QueenBlack implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;
    /**
     * Figure stores its position.
     * @param position position of figure
     */
    public QueenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }
}

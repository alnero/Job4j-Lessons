package alnero.chess.figures.black;

import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * Black Knight.
 */
public class KnightBlack implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;
    /**
     * Figure stores its position.
     * @param position position of figure
     */
    public KnightBlack(final Cell position) {
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
        return new KnightBlack(dest);
    }
}

package alnero.chess.figures.white;

import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * White King.
 */
public class KingWhite implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;
    /**
     * Figure stores its position.
     * @param position position of figure
     */
    public KingWhite(final Cell position) {
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
        return new KingWhite(dest);
    }
}

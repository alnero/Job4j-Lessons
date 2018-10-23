package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
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
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        int deltaX = source.x - dest.x;
        int deltaY = source.y - dest.y;

        if ((1 != Math.abs(deltaX) || 2 != Math.abs(deltaY))
            && (2 != Math.abs(deltaX) || 1 != Math.abs(deltaY))) {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        Cell[] steps = new Cell[1];
        steps[0] = Cell.find(source.x - deltaX, source.y - deltaY);
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }
}

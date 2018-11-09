package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * Black King.
 */
public class KingBlack implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;
    /**
     * Figure stores its position.
     * @param position position of figure
     */
    public KingBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (2 <= Math.abs(source.getX() - dest.getX()) || 2 <= Math.abs(source.getY() - dest.getY())) {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        Cell[] steps = new Cell[1];

        int deltaX = Integer.compare(source.getX(), dest.getX());
        int deltaY = Integer.compare(source.getY(), dest.getY());

        steps[0] = Cell.find(source.getX() - deltaX, source.getY() - deltaY);

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}

package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * Black Bishop.
 */
public class BishopBlack implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;

    /**
     * Figure stores its position.
     *
     * @param position position of figure
     */
    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (Math.abs(source.x - dest.x) != Math.abs(source.y - dest.y)) {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        int numberOfSteps = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[numberOfSteps];

        int deltaX = Integer.compare(source.x, dest.x);
        int deltaY = Integer.compare(source.y, dest.y);

        for (int i = 0; i < numberOfSteps; i++) {
            steps[i] = Cell.find(source.x - deltaX * (1 + i), source.y - deltaY * (1 + i));
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}

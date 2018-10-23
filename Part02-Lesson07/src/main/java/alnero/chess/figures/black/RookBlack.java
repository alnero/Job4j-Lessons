package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * Black Rook.
 */
public class RookBlack implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;
    /**
     * Figure stores its position.
     * @param position position of figure
     */
    public RookBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (source.x != dest.x && source.y != dest.y) {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        int deltaX = Integer.compare(source.x, dest.x);
        int deltaY = Integer.compare(source.y, dest.y);

        int numberOfSteps = Math.abs(deltaX * (source.x - dest.x)) + Math.abs(deltaY * (source.y - dest.y));
        Cell[] steps = new Cell[numberOfSteps];

        for (int i = 0; i < numberOfSteps; i++) {
            steps[i] = Cell.find(source.x - deltaX * (1 + i), source.y - deltaY * (1 + i));
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}

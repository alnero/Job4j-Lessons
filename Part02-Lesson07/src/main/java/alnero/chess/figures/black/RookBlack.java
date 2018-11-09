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
        int deltaX = Integer.compare(source.getX(), dest.getX());
        int deltaY = Integer.compare(source.getY(), dest.getY());

        if (deltaX != 0 && deltaY != 0) {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        int numberOfSteps = Math.abs(deltaX * (source.getX() - dest.getX())) + Math.abs(deltaY * (source.getY() - dest.getY()));
        Cell[] steps = new Cell[numberOfSteps];

        for (int i = 0; i < numberOfSteps; i++) {
            steps[i] = Cell.find(source.getX() - deltaX * (1 + i), source.getY() - deltaY * (1 + i));
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}

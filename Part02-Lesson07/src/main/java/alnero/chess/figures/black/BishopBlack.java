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
            // move North-East
            if (deltaX == -1 && deltaY == 1) {
                for (Cell cell : Cell.values()) {
                    if (cell.x == source.x + 1 + i && cell.y == source.y - 1 - i) {
                        steps[i] = cell;
                    }
                }
            }
            // move South-East
            if (deltaX == -1 && deltaY == -1) {
                for (Cell cell : Cell.values()) {
                    if (cell.x == source.x + 1 + i && cell.y == source.y + 1 + i) {
                        steps[i] = cell;
                    }
                }
            }
            // move South-West
            if (deltaX == 1 && deltaY == -1) {
                for (Cell cell : Cell.values()) {
                    if (cell.x == source.x - 1 - i && cell.y == source.y + 1 + i) {
                        steps[i] = cell;
                    }
                }
            }
            // move North-West
            if (deltaX == 1 && deltaY == 1) {
                for (Cell cell : Cell.values()) {
                    if (cell.x == source.x - 1 - i && cell.y == source.y - 1 - i) {
                        steps[i] = cell;
                    }
                }
            }
        }

        if (steps.length > 0 && !steps[steps.length - 1].equals(dest)) {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}

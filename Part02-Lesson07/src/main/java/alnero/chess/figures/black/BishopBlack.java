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
        Cell[] steps = new Cell[0];

        if (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)) {
            int numberOfSteps = Math.abs(source.x - dest.x);
            steps = new Cell[numberOfSteps];

            for (int i = 0; i < numberOfSteps; i++) {
                // move North-East
                if (source.x < dest.x && source.y > dest.y) {
                    for (Cell cell : Cell.values()) {
                        if (cell.x == source.x + 1 + i && cell.y == source.y - 1 - i) {
                            steps[i] = cell;
                        }
                    }
                // move South-East
                } else if (source.x < dest.x && source.y < dest.y) {
                    for (Cell cell : Cell.values()) {
                        if (cell.x == source.x + 1 + i && cell.y == source.y + 1 + i) {
                            steps[i] = cell;
                        }
                    }
                // move South-West
                } else if (source.x > dest.x && source.y < dest.y) {
                    for (Cell cell : Cell.values()) {
                        if (cell.x == source.x - 1 - i && cell.y == source.y + 1 + i) {
                            steps[i] = cell;
                        }
                    }
                // move North-West
                } else {
                    for (Cell cell : Cell.values()) {
                        if (cell.x == source.x - 1 - i && cell.y == source.y - 1 - i) {
                            steps[i] = cell;
                        }
                    }
                }
            }
        } else {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}

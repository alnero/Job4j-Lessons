package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 * Black Pawn.
 */
public class PawnBlack implements Figure {
    /**
     * Position of figure.
     */
    private final Cell position;
    /**
     * Figure stores its position.
     * @param position position of figure
     */
    public PawnBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        Cell[] steps = new Cell[0];

        if (source.x == dest.x && source.y == 6 && source.y == dest.y + 2) {
            steps = new Cell[2];
            for (Cell cell : Cell.values()) {
                if (cell.x == source.x && cell.y == dest.y + 1) {
                    steps[0] = cell;

                }
            }
            steps[1] = dest;
        } else if (source.x == dest.x && source.y == dest.y + 1) {
            steps = new Cell[] {dest};
        } else {
            throw new ImpossibleMoveException(this.getClass().getName() + " impossible move.");
        }

        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnBlack(dest);
    }
}

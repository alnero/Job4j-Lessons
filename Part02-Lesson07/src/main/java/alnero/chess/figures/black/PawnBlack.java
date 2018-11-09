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

        if (source.getX() == dest.getX() && source.getY() == 6 && source.getY() == dest.getY() + 2) {
            steps = new Cell[2];
            for (Cell cell : Cell.values()) {
                if (cell.getX() == source.getX() && cell.getY() == dest.getY() + 1) {
                    steps[0] = cell;

                }
            }
            steps[1] = dest;
        } else if (source.getX() == dest.getX() && source.getY() == dest.getY() + 1) {
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

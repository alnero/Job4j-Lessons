package alnero.chess;

import alnero.chess.figures.Cell;
import alnero.chess.figures.Figure;

/**
 *
 */
public class Logic {
    /**
     * Array of figures.
     */
    private final Figure[] figures = new Figure[32];
    /**
     * Start index for array of figures.
     */
    private int index = 0;

    /**
     * Add figure to array.
     * @param figure figure to add to array
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Check if figure can move from source place to destination place.
     * @param source start cell
     * @param dest finish cell
     * @return can figure move like this
     * @throws FigureNotFoundException if figure in start point not found
     * @throws ImpossibleMoveException if figure can't move this way
     * @throws OccupiedWayException if way is occupied by another figures
     */
    public boolean move(Cell source, Cell dest) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Figure not found.");
        }

        Cell[] steps = this.figures[index].way(source, dest);
        for (Cell cell : steps) {
            if (this.findBy(cell) != -1) {
                throw new OccupiedWayException("Way occupied by another figure.");
            }
        }

        this.figures[index] = this.figures[index].copy(dest);

        return true;
    }

    /**
     * Null array of figures and reset index.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Find index of cell in array of figures.
     * @param cell find index of this cell
     * @return index found
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int position = 0; position != this.figures.length; position++) {
            if (this.figures[position] != null && this.figures[position].position().equals(cell)) {
                rst = position;
                break;
            }
        }
        return rst;
    }
}
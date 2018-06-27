package alnero.chess.figures;

import alnero.chess.ImpossibleMoveException;

/**
 * All figures will have these common methods.
 */
public interface Figure {
    /**
     * Figure will return its position.
     * @return position of figure
     */
    Cell position();

    /**
     * Figure will know its way.
     * @param source start cell
     * @param dest finish cell
     * @return array of cells which figure have to pass on its way
     * @throws ImpossibleMoveException if figure can't move this way
     */
    Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Return name of icon file for figure.
     * @return name of png file for figure representation
     */
    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    /**
     * Figure will re-create itself with new position.
     * @param dest position of figure
     * @return figure itself
     */
    Figure copy(Cell dest);

}
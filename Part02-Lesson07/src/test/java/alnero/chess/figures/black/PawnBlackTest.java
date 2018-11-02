package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of black pawn movement on the chess board.
 */
public class PawnBlackTest {
    /**
     * Move one cell to the north from initial position.
     */
    @Test
    public void whenPawnBlackMovesFromD7toD6thenItMovesOneCellToNorth() {
        PawnBlack pawn = new PawnBlack(Cell.D7);
        Cell[] actualWay = pawn.way(pawn.position(), Cell.D6);
        Cell[] expectedWay = {Cell.D6};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move two cells to the north from initial position.
     */
    @Test
    public void whenPawnBlackMovesFromD7toD5thenItMovesTwoCellsToNorth() {
        PawnBlack pawn = new PawnBlack(Cell.D7);
        Cell[] actualWay = pawn.way(pawn.position(), Cell.D5);
        Cell[] expectedWay = {Cell.D6, Cell.D5};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move three cells to the north gives exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnBlackMovesFromD7toD4thenThereIsImpossibleMoveException() {
        PawnBlack pawn = new PawnBlack(Cell.D7);
        Cell[] actualWay = pawn.way(pawn.position(), Cell.D4);
    }

    /**
     * Move to the south (backward) gives exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnBlackMovesFromD6toD7thenThereIsImpossibleMoveException() {
        PawnBlack pawn = new PawnBlack(Cell.D6);
        Cell[] actualWay = pawn.way(pawn.position(), Cell.D7);
    }

    /**
     * North-west move gives exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnBlackMovesFromD7toE6thenThereIsImpossibleMoveException() {
        PawnBlack pawn = new PawnBlack(Cell.D7);
        Cell[] actualWay = pawn.way(pawn.position(), Cell.E6);
    }

    /**
     * North-east move gives exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnBlackMovesFromD7toC6thenThereIsImpossibleMoveException() {
        PawnBlack pawn = new PawnBlack(Cell.D7);
        Cell[] actualWay = pawn.way(pawn.position(), Cell.C6);
    }
}

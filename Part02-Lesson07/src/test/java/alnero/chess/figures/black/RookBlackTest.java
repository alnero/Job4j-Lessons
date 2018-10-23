package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of black rook movement on the chess board.
 */
public class RookBlackTest {
    /**
     * Move south.
     */
    @Test
    public void whenBlackRookMovesFromA1toH1thenItMovesSouth() {
        RookBlack rook = new RookBlack(Cell.A1);
        Cell[] actualWay = rook.way(rook.position(), Cell.H1);
        Cell[] expectedWay = {Cell.B1, Cell.C1, Cell.D1, Cell.E1, Cell.F1, Cell.G1, Cell.H1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move east.
     */
    @Test
    public void whenBlackRookMovesFromH1toH8thenItMovesEast() {
        RookBlack rook = new RookBlack(Cell.H1);
        Cell[] actualWay = rook.way(rook.position(), Cell.H8);
        Cell[] expectedWay = {Cell.H2, Cell.H3, Cell.H4, Cell.H5, Cell.H6, Cell.H7, Cell.H8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north.
     */
    @Test
    public void whenBlackRookMovesFromH8toA8thenItMovesNorth() {
        RookBlack rook = new RookBlack(Cell.H8);
        Cell[] actualWay = rook.way(rook.position(), Cell.A8);
        Cell[] expectedWay = {Cell.G8, Cell.F8, Cell.E8, Cell.D8, Cell.C8, Cell.B8, Cell.A8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move west.
     */
    @Test
    public void whenBlackRookMovesFromA8toA1thenItMovesWest() {
        RookBlack rook = new RookBlack(Cell.A8);
        Cell[] actualWay = rook.way(rook.position(), Cell.A1);
        Cell[] expectedWay = {Cell.A7, Cell.A6, Cell.A5, Cell.A4, Cell.A3, Cell.A2, Cell.A1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Impossible move to south-east drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackRookMakesImpossibleMoveFromA1toH8thenThereIsImpossibleMoveException() {
        RookBlack rook = new RookBlack(Cell.A1);
        Cell[] actualWay = rook.way(rook.position(), Cell.H8);
    }

    /**
     * Impossible move to south-west drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackRookMakesImpossibleMoveFromA8toH1thenThereIsImpossibleMoveException() {
        RookBlack rook = new RookBlack(Cell.A8);
        Cell[] actualWay = rook.way(rook.position(), Cell.H1);
    }

    /**
     * Impossible move to north-west drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackRookMakesImpossibleMoveFromH8toA1thenThereIsImpossibleMoveException() {
        RookBlack rook = new RookBlack(Cell.H8);
        Cell[] actualWay = rook.way(rook.position(), Cell.A1);
    }

    /**
     * Impossible move to north-east drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackRookMakesImpossibleMoveFromH1toA8thenThereIsImpossibleMoveException() {
        RookBlack rook = new RookBlack(Cell.H1);
        Cell[] actualWay = rook.way(rook.position(), Cell.A8);
    }
}

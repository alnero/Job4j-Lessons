package alnero.chess.figures.black;

import alnero.chess.figures.Cell;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of black bishop movement on the chess board.
 */
public class BishopBlackTest {
    /**
     * Move north-east.
     */
    @Test
    public void whenBlackBishopMovesFromH1toA8thenItMovesNorthEast() {
        BishopBlack bishop = new BishopBlack(Cell.H1);
        Cell[] actualWay = bishop.way(bishop.position(), Cell.A8);
        Cell[] expectedWay = {Cell.G2, Cell.F3, Cell.E4, Cell.D5, Cell.C6, Cell.B7, Cell.A8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-east.
     */
    @Test
    public void whenBlackBishopMovesFromA1toH8thenItMovesSouthEast() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Cell[] actualWay = bishop.way(bishop.position(), Cell.H8);
        Cell[] expectedWay = {Cell.B2, Cell.C3, Cell.D4, Cell.E5, Cell.F6, Cell.G7, Cell.H8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-west.
     */
    @Test
    public void whenBlackBishopMovesFromA8toH1thenItMovesSouthWest() {
        BishopBlack bishop = new BishopBlack(Cell.A8);
        Cell[] actualWay = bishop.way(bishop.position(), Cell.H1);
        Cell[] expectedWay = {Cell.B7, Cell.C6, Cell.D5, Cell.E4, Cell.F3, Cell.G2, Cell.H1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north-west.
     */
    @Test
    public void whenBlackBishopMovesFromH8toA1thenItMovesNorthWest() {
        BishopBlack bishop = new BishopBlack(Cell.H8);
        Cell[] actualWay = bishop.way(bishop.position(), Cell.A1);
        Cell[] expectedWay = {Cell.G7, Cell.F6, Cell.E5, Cell.D4, Cell.C3, Cell.B2, Cell.A1};
        assertThat(actualWay, is(expectedWay));
    }
}

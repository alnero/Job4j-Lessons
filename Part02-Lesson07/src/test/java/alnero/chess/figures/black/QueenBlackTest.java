package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of black queen movement on the chess board.
 */
public class QueenBlackTest {
    /**
     * Move north.
     */
    @Test
    public void whenBlackQueenMovesFromH1toA1thenItMovesNorth() {
        QueenBlack queen = new QueenBlack(Cell.H1);
        Cell[] actualWay = queen.way(queen.position(), Cell.A1);
        Cell[] expectedWay = {Cell.G1, Cell.F1, Cell.E1, Cell.D1, Cell.C1, Cell.B1, Cell.A1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north-east.
     */
    @Test
    public void whenBlackQueenMovesFromH1toA8thenItMovesNorthEast() {
        QueenBlack queen = new QueenBlack(Cell.H1);
        Cell[] actualWay = queen.way(queen.position(), Cell.A8);
        Cell[] expectedWay = {Cell.G2, Cell.F3, Cell.E4, Cell.D5, Cell.C6, Cell.B7, Cell.A8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move east.
     */
    @Test
    public void whenBlackQueenMovesFromH1toH8thenItMovesEast() {
        QueenBlack queen = new QueenBlack(Cell.H1);
        Cell[] actualWay = queen.way(queen.position(), Cell.H8);
        Cell[] expectedWay = {Cell.H2, Cell.H3, Cell.H4, Cell.H5, Cell.H6, Cell.H7, Cell.H8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-east.
     */
    @Test
    public void whenBlackQueenMovesFromA1toH8thenItMovesSouthEast() {
        QueenBlack queen = new QueenBlack(Cell.A1);
        Cell[] actualWay = queen.way(queen.position(), Cell.H8);
        Cell[] expectedWay = {Cell.B2, Cell.C3, Cell.D4, Cell.E5, Cell.F6, Cell.G7, Cell.H8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south.
     */
    @Test
    public void whenBlackQueenMovesFromA8toH8thenItMovesSouth() {
        QueenBlack queen = new QueenBlack(Cell.A8);
        Cell[] actualWay = queen.way(queen.position(), Cell.H8);
        Cell[] expectedWay = {Cell.B8, Cell.C8, Cell.D8, Cell.E8, Cell.F8, Cell.G8, Cell.H8};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-west.
     */
    @Test
    public void whenBlackQueenMovesFromA8toH1thenItMovesSouthWest() {
        QueenBlack queen = new QueenBlack(Cell.A8);
        Cell[] actualWay = queen.way(queen.position(), Cell.H1);
        Cell[] expectedWay = {Cell.B7, Cell.C6, Cell.D5, Cell.E4, Cell.F3, Cell.G2, Cell.H1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move west.
     */
    @Test
    public void whenBlackQueenMovesFromA8toA1thenItMovesWest() {
        QueenBlack queen = new QueenBlack(Cell.A8);
        Cell[] actualWay = queen.way(queen.position(), Cell.A1);
        Cell[] expectedWay = {Cell.A7, Cell.A6, Cell.A5, Cell.A4, Cell.A3, Cell.A2, Cell.A1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north-west.
     */
    @Test
    public void whenBlackQueenMovesFromH8toA1thenItMovesNorthWest() {
        QueenBlack queen = new QueenBlack(Cell.H8);
        Cell[] actualWay = queen.way(queen.position(), Cell.A1);
        Cell[] expectedWay = {Cell.G7, Cell.F6, Cell.E5, Cell.D4, Cell.C3, Cell.B2, Cell.A1};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Black queen impossible move drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackQueenMakesImpossibleMoveFromE5ThenThereIsImpossibleMoveException() {
        Cell[] correctPositionsFromE5 = {
                Cell.D5, Cell.C5, Cell.B5, Cell.A5,
                Cell.D6, Cell.C7, Cell.B8,
                Cell.E6, Cell.E7, Cell.E8,
                Cell.F6, Cell.G7, Cell.H8,
                Cell.F5, Cell.G5, Cell.H5,
                Cell.F4, Cell.G3, Cell.H2,
                Cell.E4, Cell.E3, Cell.E2, Cell.E1,
                Cell.D4, Cell.C3, Cell.B2, Cell.A1
        };
        Cell randomDestinationPosition = Cell.D5;
        boolean isCorrectPosition = true;

        while (isCorrectPosition) {
            if (Arrays.asList(correctPositionsFromE5).contains(randomDestinationPosition)) {
                randomDestinationPosition = Cell.values()[new Random().nextInt(Cell.values().length)];
                continue;
            }
            isCorrectPosition = false;
        }

        QueenBlack queen = new QueenBlack(Cell.E5);
        Cell[] actualWay = queen.way(queen.position(), randomDestinationPosition);
    }
}

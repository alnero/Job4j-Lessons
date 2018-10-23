package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of black knight movement on the chess board.
 */
public class KnightBlackTest {
    /**
     * Move north-north-east.
     */
    @Test
    public void whenBlackKnightMovesFromE5toC6thenItMovesNorthNorthEast() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.C6);
        Cell[] expectedWay = {Cell.C6};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move east-north-east.
     */
    @Test
    public void whenBlackKnightMovesFromE5toD7thenItMovesEastNorthEast() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.D7);
        Cell[] expectedWay = {Cell.D7};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move east-south-east.
     */
    @Test
    public void whenBlackKnightMovesFromE5toF7thenItMovesEastSouthEast() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.F7);
        Cell[] expectedWay = {Cell.F7};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-south-east.
     */
    @Test
    public void whenBlackKnightMovesFromE5toG6thenItMovesSouthSouthEast() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.G6);
        Cell[] expectedWay = {Cell.G6};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-south-west.
     */
    @Test
    public void whenBlackKnightMovesFromE5toG4thenItMovesSouthSouthWest() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.G4);
        Cell[] expectedWay = {Cell.G4};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move west-south-west.
     */
    @Test
    public void whenBlackKnightMovesFromE5toF3thenItMovesWestSouthWest() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.F3);
        Cell[] expectedWay = {Cell.F3};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move west-north-west.
     */
    @Test
    public void whenBlackKnightMovesFromE5toD3thenItMovesWestNorthWest() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.D3);
        Cell[] expectedWay = {Cell.D3};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north-north-west.
     */
    @Test
    public void whenBlackKnightMovesFromE5toC4thenItMovesNorthNorthWest() {
        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), Cell.C4);
        Cell[] expectedWay = {Cell.C4};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Impossible move drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackKnightMakesImpossibleMoveFromE5ThenThereIsImpossibleMoveException() {
        Cell[] correctPositionsFromE5 = {Cell.C6, Cell.D7, Cell.F7, Cell.G6, Cell.G4, Cell.F3, Cell.D3, Cell.C4};
        Cell randomDestinationPosition = Cell.C6;
        boolean isCorrectPosition = true;

        while (isCorrectPosition) {
            if (Arrays.asList(correctPositionsFromE5).contains(randomDestinationPosition)) {
                randomDestinationPosition = Cell.values()[new Random().nextInt(Cell.values().length)];
                continue;
            }
            isCorrectPosition = false;
        }

        KnightBlack knight = new KnightBlack(Cell.E5);
        Cell[] actualWay = knight.way(knight.position(), randomDestinationPosition);
    }
}

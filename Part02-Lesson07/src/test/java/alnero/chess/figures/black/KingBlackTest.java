package alnero.chess.figures.black;

import alnero.chess.ImpossibleMoveException;
import alnero.chess.figures.Cell;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing of black king movement on the chess board.
 */
public class KingBlackTest {
    /**
     * Move north.
     */
    @Test
    public void whenBlackKingMovesFromE5toD5thenItMovesNorth() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.D5);
        Cell[] expectedWay = {Cell.D5};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north-east.
     */
    @Test
    public void whenBlackKingMovesFromE5toD6thenItMovesNorthEast() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.D6);
        Cell[] expectedWay = {Cell.D6};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move east.
     */
    @Test
    public void whenBlackKingMovesFromE5toE6thenItMovesEast() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.E6);
        Cell[] expectedWay = {Cell.E6};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-east.
     */
    @Test
    public void whenBlackKingMovesFromE5toF6thenItMovesSouthEast() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.F6);
        Cell[] expectedWay = {Cell.F6};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south.
     */
    @Test
    public void whenBlackKingMovesFromE5toF5thenItMovesSouth() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.F5);
        Cell[] expectedWay = {Cell.F5};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move south-west.
     */
    @Test
    public void whenBlackKingMovesFromE5toF4thenItMovesSouthWest() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.F4);
        Cell[] expectedWay = {Cell.F4};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move west.
     */
    @Test
    public void whenBlackKingMovesFromE5toE4thenItMovesWest() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.E4);
        Cell[] expectedWay = {Cell.E4};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Move north-west.
     */
    @Test
    public void whenBlackKingMovesFromE5toD4thenItMovesNorthWest() {
        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), Cell.D4);
        Cell[] expectedWay = {Cell.D4};
        assertThat(actualWay, is(expectedWay));
    }

    /**
     * Black king impossible move drops exception.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenBlackKingMakesImpossibleMoveFromE5ThenThereIsImpossibleMoveException() {
        Cell[] correctPositionsFromE5 = {Cell.D5, Cell.D6, Cell.E6, Cell.F6, Cell.F5, Cell.F4, Cell.E4, Cell.D4};
        Cell randomDestinationPosition = Cell.D5;
        boolean isCorrectPosition = true;

        while (isCorrectPosition) {
            if (Arrays.asList(correctPositionsFromE5).contains(randomDestinationPosition)) {
                randomDestinationPosition = Cell.values()[new Random().nextInt(Cell.values().length)];
                continue;
            }
            isCorrectPosition = false;
        }

        KingBlack king = new KingBlack(Cell.E5);
        Cell[] actualWay = king.way(king.position(), randomDestinationPosition);
    }
}

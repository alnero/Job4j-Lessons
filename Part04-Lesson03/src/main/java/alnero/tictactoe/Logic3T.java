package alnero.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * TicTacToe game logic.
 */
public class Logic3T {
    /** Table of the game - 2D array. */
    private final Figure3T[][] table;

    /**
     * Constructor for logic class.
     * @param table table of the game
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Check for winner. It can be X or O depending on supplied predicate.
     * @param predicate condition to be checked on rectangle
     * @return is there a winner in the game - true/false
     */
    public boolean checkWinner(Predicate<Figure3T> predicate) {
        // check horizontal lines
        for (int row = 0; row < this.table.length; row++) {
            if (Arrays.stream(this.table[row]).filter(rect -> predicate.test(rect)).count() == 3) {
                return true;
            }
        }
        // check vertical lines
        for (int column = 0; column < this.table.length; column++) {
            int col = column;
            if (Arrays.stream(this.table).map(o -> o[col]).filter(rect -> predicate.test(rect)).count() == 3) {
                return true;
            }
        }
        // check main diagonal
        Figure3T[] mainDiagonal = new Figure3T[3];
        IntStream.range(0, this.table.length).forEach(
                i -> {
                    mainDiagonal[i] = this.table[i][i];
                });
        if (Arrays.stream(mainDiagonal).filter(rect -> predicate.test(rect)).count() == 3) {
            return true;
        }
        // check reverse diagonal
        Figure3T[] reverseDiagonal = new Figure3T[3];
        IntStream.range(0, this.table.length).forEach(
                i -> {
                    reverseDiagonal[i] = this.table[i][this.table.length - 1 - i];
                });
        if (Arrays.stream(reverseDiagonal).filter(rect -> predicate.test(rect)).count() == 3) {
            return true;
        }
        return false;
    }

    /**
     * Check that X is winner.
     * @return is X winner
     */
    public boolean isWinnerX() {
       return checkWinner(Figure3T::hasMarkX);
    }

    /**
     * Check that O winner.
     * @return is O winner
     */
    public boolean isWinnerO() {
       return checkWinner(Figure3T::hasMarkO);
    }

    /**
     * Check that game has empty rectangles to put X or O.
     * @return is there empty rectangles on the game table
     */
    public boolean hasGap() {
        boolean result = Arrays.stream(this.table).flatMap(arr -> Arrays.stream(arr)).anyMatch(rect -> !rect.hasMarkX() && !rect.hasMarkO());
        return result;
    }
}

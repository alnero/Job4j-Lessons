package alnero.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * Main figure of the TicTacToe game.
 */
public class Figure3T extends Rectangle {
    /** X marker of rectangle. */
    private boolean markX = false;
    /** O marker of rectangle. */
    private boolean markO = false;

    /**
     * Common constructor of rectangle figure.
     */
    public Figure3T() {
    }

    /**
     * Constructor of rectangle figure with defined X and O markers.
     * @param markX X marker
     * @param markO O marker
     */
    public Figure3T(boolean markX, boolean markO) {
        this.markX = markX;
        this.markO = markO;
    }

    /**
     * Put mark inside rectangle. Can be X or O depending on supplied argument.
     * @param markX true or false puts respectively X or O marker inside rectangle
     */
    public void take(boolean markX) {
            this.markX = markX;
            this.markO = !markX;
    }

    /**
     * Is there X marker inside rectangle figure.
     * @return has rectangle X marker
     */
    public boolean hasMarkX() {
        return this.markX;
    }

    /**
     * Is there O marker inside rectangle figure.
     * @return has rectangle O maker
     */
    public boolean hasMarkO() {
        return this.markO;
    }
}

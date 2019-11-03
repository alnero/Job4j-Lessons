package alnero.tictactoe;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test TicTacToe game.
 */
@Ignore
public class Logic3TTest {
    /**
     * Check that there is no winner on empty field.
     */
    @Test
    public void whenFieldIsEmptyThenNoWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(false));
        assertThat(login.isWinnerX(), is(false));
    }

    /**
     * Check that X is a winner on first horizontal line.
     */
    @Test
    public void whenAllXonFirstHorizontalLineThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(true, false), new Figure3T(true, false), new Figure3T(true, false)},
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that X is a winner on second horizontal line.
     */
    @Test
    public void whenAllXonSecondHorizontalLineThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(true, false), new Figure3T(true, false), new Figure3T(true, false)},
                {new Figure3T(), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that X is a winner on third horizontal line.
     */
    @Test
    public void whenAllXonThirdHorizontalLineThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(true, false), new Figure3T(true, false), new Figure3T(true, false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that X is a winner on first vertical line.
     */
    @Test
    public void whenAllXonFirstVerticalLineThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(true, false), new Figure3T(), new Figure3T()},
                {new Figure3T(true, false), new Figure3T(), new Figure3T()},
                {new Figure3T(true, false), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that X is a winner on second vertical line.
     */
    @Test
    public void whenAllXonSecondVerticalLineThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(true, false), new Figure3T()},
                {new Figure3T(), new Figure3T(true, false), new Figure3T()},
                {new Figure3T(), new Figure3T(true, false), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that X is a winner on third vertical line.
     */
    @Test
    public void whenAllXonThirdVerticalLineThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(true, false)},
                {new Figure3T(), new Figure3T(), new Figure3T(true, false)},
                {new Figure3T(), new Figure3T(), new Figure3T(true, false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }


    /**
     * Check that X is a winner on main diagonal.
     */
    @Test
    public void whenAllXonMainDiagonalThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(true, false), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(true, false), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T(true, false)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that X is a winner on reverse diagonal.
     */
    @Test
    public void whenAllXonReverseDiagonalThenXisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(true, false)},
                {new Figure3T(), new Figure3T(true, false), new Figure3T()},
                {new Figure3T(true, false), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerX(), is(true));
    }

    /**
     * Check that O is a winner on first horizontal line.
     */
    @Test
    public void whenAllOonFirstHorizontalLineThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(false, true), new Figure3T(false, true), new Figure3T(false, true)},
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that O is a winner on second horizontal line.
     */
    @Test
    public void whenAllOonSecondHorizontalLineThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(false, true), new Figure3T(false, true), new Figure3T(false, true)},
                {new Figure3T(), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that O is a winner on third horizontal line.
     */
    @Test
    public void whenAllOonThirdHorizontalLineThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T()},
                {new Figure3T(false, true), new Figure3T(false, true), new Figure3T(false, true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that O is a winner on first vertical line.
     */
    @Test
    public void whenAllOonFirstVerticalLineThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(false, true), new Figure3T(), new Figure3T()},
                {new Figure3T(false, true), new Figure3T(), new Figure3T()},
                {new Figure3T(false, true), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that O is a winner on second vertical line.
     */
    @Test
    public void whenAllOonSecondVerticalLineThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(false, true), new Figure3T()},
                {new Figure3T(), new Figure3T(false, true), new Figure3T()},
                {new Figure3T(), new Figure3T(false, true), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that O is a winner on third vertical line.
     */
    @Test
    public void whenAllOonThirdVerticalLineThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(false, true)},
                {new Figure3T(), new Figure3T(), new Figure3T(false, true)},
                {new Figure3T(), new Figure3T(), new Figure3T(false, true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }


    /**
     * Check that O is a winner on main diagonal.
     */
    @Test
    public void whenAllOonMainDiagonalThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(false, true), new Figure3T(), new Figure3T()},
                {new Figure3T(), new Figure3T(false, true), new Figure3T()},
                {new Figure3T(), new Figure3T(), new Figure3T(false, true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that O is a winner on reverse diagonal.
     */
    @Test
    public void whenAllOonReverseDiagonalThenOisWinner() {
        Figure3T[][] table = {
                {new Figure3T(), new Figure3T(), new Figure3T(false, true)},
                {new Figure3T(), new Figure3T(false, true), new Figure3T()},
                {new Figure3T(false, true), new Figure3T(), new Figure3T()},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.isWinnerO(), is(true));
    }

    /**
     * Check that game field has empty rectangles.
     */
    @Test
    public void whenFieldHasEmptyRectanglesThenGameIsOn() {
        Figure3T[][] table = {
                {new Figure3T(true, false), new Figure3T(true, false), new Figure3T(false, true)},
                {new Figure3T(false, true), new Figure3T(), new Figure3T(true, false)},
                {new Figure3T(true, false), new Figure3T(false, true), new Figure3T(false, true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.hasGap(), is(true));
    }

    /**
     * Check that game field does not have empty rectangles.
     */
    @Test
    public void whenFieldDoesNotHaveEmptyRectanglesThenGameToBeRestarted() {
        Figure3T[][] table = {
                {new Figure3T(true, false), new Figure3T(true, false), new Figure3T(false, true)},
                {new Figure3T(false, true), new Figure3T(true, false), new Figure3T(true, false)},
                {new Figure3T(true, false), new Figure3T(false, true), new Figure3T(false, true)},
        };
        Logic3T login = new Logic3T(table);
        assertThat(login.hasGap(), is(false));
    }
}

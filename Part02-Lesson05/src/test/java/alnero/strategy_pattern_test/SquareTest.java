package alnero.strategy_pattern_test;

import alnero.strategyPattern.Square;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing draw method in Square class.
 */
public class SquareTest {

    /**
     * Test drawing of square.
     */
    @Test
    public void whenDrawSquareThenCorrectShapeIsDrawn() {
        Square square = new Square();
        assertThat(square.draw(),
                is(new StringBuilder()
                        .append("+++++++\n")
                        .append("+     +\n")
                        .append("+     +\n")
                        .append("+++++++\n")
                        .toString()));
    }
}

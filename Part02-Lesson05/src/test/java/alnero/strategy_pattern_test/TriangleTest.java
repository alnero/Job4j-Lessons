package alnero.strategy_pattern_test;

import alnero.strategyPattern.Triangle;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing draw method in Triangle class.
 */
public class TriangleTest {

    /**
     * Test drawing of triangle.
     */
    @Test
    public void whenDrawTriangleThenCorrectShapeIsDrawn() {
        Triangle triangle = new Triangle();
        assertThat(triangle.draw(),
                is(new StringBuilder()
                        .append("   +\n")
                        .append("  + +\n")
                        .append(" +   +\n")
                        .append("+++++++\n")
                        .toString()));
    }

}

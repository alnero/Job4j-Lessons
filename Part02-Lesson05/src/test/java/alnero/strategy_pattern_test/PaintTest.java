package alnero.strategy_pattern_test;

import alnero.strategyPattern.Paint;
import alnero.strategyPattern.Square;

import alnero.strategyPattern.Triangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing draw method in Paint class.
 */
public class PaintTest {
    /**
     * Saving default console output.
     */
    private final PrintStream originalSystemOut = System.out;

    /**
     * Byte array, to redirect output to it.
     */
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    /**
     * Method name is self explanatory.
     */
    @Before
    public void redirectDefaultOutputToByteArray() {
        System.setOut(new PrintStream(outputContent));
    }

    /**
     * Method name is self explanatory.
     */
    @After
    public void restoreDefaultOutputToConsole() {
        System.setOut(originalSystemOut);
    }

    /**
     * Test drawing of square via Paint class.
     */
    @Test
    public void whenDrawSquareViaPaintClassThenCorrectShapeIsDrawn() {
        new Paint().draw(new Square());
        assertThat(
                outputContent.toString(),
                is(
                        new StringBuilder()
                                .append("+++++++\n")
                                .append("+     +\n")
                                .append("+     +\n")
                                .append("+++++++\n")
                                .append(System.lineSeparator()) // System.out.println() adds line separator
                                .toString()
                )
        );
    }

    /**
     * Test drawing of triangle via Paint class.
     */
    @Test
    public void whenDrawTriangleViaPaintClassThenCorrectShapeIsDrawn() {
        new Paint().draw(new Triangle());
        assertThat(
                outputContent.toString(),
                is(
                        new StringBuilder()
                                .append("   +\n")
                                .append("  + +\n")
                                .append(" +   +\n")
                                .append("+++++++\n")
                                .append(System.lineSeparator()) // System.out.println() adds line separator
                                .toString()
                )
        );
    }

}

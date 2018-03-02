package alnero.strategy_pattern_test;

import alnero.strategy_pattern.Paint;
import alnero.strategy_pattern.Square;

import alnero.strategy_pattern.Triangle;
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
     * Test drawing of square via Paint class.
     */
    @Test
    public void whenDrawSquareViaPaintClassThenCorrectShapeIsDrawn() {
        // redirect default console output to byte array
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        new Paint().draw(new Square());
        assertThat(
                new String(outputContent.toByteArray()),
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

        // restore default output to console
        System.setOut(originalSystemOut);
    }

    /**
     * Test drawing of triangle via Paint class.
     */
    @Test
    public void whenDrawTriangleViaPaintClassThenCorrectShapeIsDrawn() {
        // redirect default console output to byte array
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));

        new Paint().draw(new Triangle());
        assertThat(
                new String(outputContent.toByteArray()),
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

        // restore default output to console
        System.setOut(originalSystemOut);
    }

}

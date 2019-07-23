package alnero.functionRange;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test calculation of functions in specified range.
 */
public class FunctionRangeTest {
    /**
     * Calculate in range [5, 8) for linear function y = 2 * x + 1.
     */
    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionRange functionRange = new FunctionRange();
        List<Double> result = functionRange.calculateFunctionInRange(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    /**
     * Calculate in range [2, 5) for quadratic function y = x * x.
     */
    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        FunctionRange functionRange = new FunctionRange();
        List<Double> result = functionRange.calculateFunctionInRange(2, 5, x -> x * x);
        List<Double> expected = Arrays.asList(4D, 9D, 16D);
        assertThat(result, is(expected));
    }

    /**
     * Calculate in range [2, 5) for logarithm function y = log(x).
     */
    @Test
    public void whenLogFunctionThenLogResults() {
        FunctionRange functionRange = new FunctionRange();
        List<Double> result = functionRange.calculateFunctionInRange(2, 5, x -> (double) Math.round(Math.log(x) * 1000) / 1000);
        List<Double> expected = Arrays.asList(0.693D, 1.099D, 1.386D);
        assertThat(result, is(expected));
    }
}

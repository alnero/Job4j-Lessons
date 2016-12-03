package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

/**
 * Testing class Factorial.
 */
public class FactorialTest {
    /**
     * Common Factorial object to use in all tests.
     */
    private Factorial factorialCalculator;

    /**
     * Create common Factorial object.
     */
    @Before
    public void createFactorialObjectForTests() {
        this.factorialCalculator = new Factorial();
    }

    /**
     * 3! = 1 * 2 * 3 = 6.
     */
    @Test
    public void whenCalculateFactorialOfThreeThenReturnSix() {
        int result = this.factorialCalculator.getFactorialOf(3);
        Assert.assertThat(result, is(6));
    }

    /**
     * 5! = 1 * 2 * 3 * 4 * 5 = 120.
     */
    @Test
    public void whenCalculateFactorialOfFiveThenReturnOneHundredAndTwenty() {
        int result = this.factorialCalculator.getFactorialOf(5);
        Assert.assertThat(result, is(120));
    }

    /**
     * !0 = 1.
     */
    @Test
    public void whenCalculateFactorialOfZeroThenReturnOne() {
        int result = this.factorialCalculator.getFactorialOf(0);
        Assert.assertThat(result, is(1));

    }

    /**
     * Factorial of negative number is not defined, method throws an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenCalculateFactorialOfNegativeIntegerThenAlwaysReturnZero() {
        // generates integer in range [-10, -1]
        Random random = new Random();
        int num = random.nextInt(10) - 10;

        this.factorialCalculator.getFactorialOf(num);
    }

    /**
     * !10 = 3 628 800, see https://en.wikipedia.org/wiki/Factorial.
     */
    @Test
    public void whenCalculateFactorialOfTenThenReturnThreeMillionSixHundredAndTwentyEightThousandEightHundred() {
        int result = this.factorialCalculator.getFactorialOf(10);
        Assert.assertThat(result, is(3628800));
    }

    /**
     * Factorial is always positive.
     */
    @Test
    public void whenCalculateFactorialForIntegerInRangeFromZeroToNineThenReturnedResultIsAlwaysPositive() {
        // generates integer in range [0, 9]
        Random random = new Random();
        int num = random.nextInt(10);

        int result = this.factorialCalculator.getFactorialOf(num);
        Assert.assertThat(result, greaterThanOrEqualTo(1));
    }

}

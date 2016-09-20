package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;

/**
 * Class for testing public methods in Calculator.
 */
public class CalculatorTest {
    /**
     * Variable to store the Calculator object for the tests.
     */
    private Calculator calculator;

    /**
     * All tests use one object of Calculator class.
     */
    @Before
    public void createCalculatorObjectForTests() {
        calculator = new Calculator();
    }

    /**
     * Check that zero result is returned when no operation performed.
     */
    @Test
    public void whenNoOperationThenResultIsZero() {
        Assert.assertThat(calculator.getResult(), is(0.0));
    }

    /**
     * Check addition of integers.
     */
    @Test
    public void whenAddOnePlusOneThenResultIsTwo() {
        calculator.add(1, 1);
        Assert.assertThat(calculator.getResult(), is(2.0));
    }

    /**
     * Check addition of floating point numbers.
     */
    @Test
    public void whenAddDecimalOnePlusDecimalTwoThenResultIsDecimalThree() {
        calculator.add(0.1, 0.2);
        Assert.assertThat(calculator.getResult(), is(0.3));
    }

    /**
     * Check subtraction of integers.
     */
    @Test
    public void whenSubtractTwoMinusOneThenResultIsOne() {
        calculator.subtract(2, 1);
        Assert.assertThat(calculator.getResult(), is(1.0));
    }

    /**
     * Check subtraction of floating point numbers.
     */
    @Test
    public void whenSubtractDecimalTwoMinusDecimalOneThenResultIsDecimalOne() {
        calculator.subtract(0.2, 0.1);
        Assert.assertThat(calculator.getResult(), is(0.1));
    }

    /**
     * Check multiplication of integers.
     */
    @Test
    public void whenMultiplyTwoByTwoThenResultIsFour() {
        calculator.multiply(2, 2);
        Assert.assertThat(calculator.getResult(), is(4.0));
    }

    /**
     * Check multiplication of floating point numbers.
     */
    @Test
    public void whenMultiplyDecimalOneByDecimalOneThenResultIsDecimalZeroOne() {
        calculator.multiply(0.1, 0.1);
        Assert.assertThat(calculator.getResult(), is(0.01));
    }

    /**
     * Check division of integers when result is floating point number.
     */
    @Test
    public void whenDivideFiveByTwoThenResultIsTwoDecimalFive() {
        calculator.divide(5, 2);
        Assert.assertThat(calculator.getResult(), is(2.5));
    }

    /**
     * Check that IllegalArgumentException is thrown when divide any number by zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenDivideByZeroThenResultIsIllegalArgumentException() {
        double randomValue = new Random().nextDouble();
        calculator.divide(randomValue, 0);
    }

}

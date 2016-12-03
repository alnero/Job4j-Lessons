package alnero;

import alnero.output.MockOutput;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Testing class Square and its public methods.
 */
public class SquareTest {
    /**
     * Test calculations for coefficients forming equation y = x^2.
     */
    @Test
    public void whenFirstArgIsOneAndRestAreZeroesAndSupplyTwoThenResultIsFour() {
        Square squareEquation = new Square(1.0f, 0.0f, 0.0f);
        float result = squareEquation.calculate(2.0f);
        Assert.assertThat(result, is(4.0f));
    }

    /**
     * Test calculations for coefficients forming equation y = x^2 + x.
     */
    @Test
    public void whenFirstArgIsOneSecondArgIsOneAndSupplyTwoThenResultIsSix() {
        Square squareEquation = new Square(1.0f, 1.0f, 0.0f);
        float result = squareEquation.calculate(2.0f);
        Assert.assertThat(result, is(6.0f));
    }

    /**
     * Test calculations for coefficients forming equation y = x^2 + x + 5.
     */
    @Test
    public void whenFirstArgIsOneSecondArgIsOneThirdArgIsFiveAndSupplyTwoThenResultIsEleven() {
        Square squareEquation = new Square(1.0f, 1.0f, 5.0f);
        float result = squareEquation.calculate(2.0f);
        Assert.assertThat(result, is(11.0f));
    }

    /**
     * Test calculations for coefficients forming equation y = 2 * x^2.
     */
    @Test
    public void whenFirstArgIsTwoAndRestAreZeroesAndSupplyTwoThenResultIsEight() {
        Square squareEquation = new Square(2.0f, 0.0f, 0.0f);
        float result = squareEquation.calculate(2.0f);
        Assert.assertThat(result, is(8.0f));
    }

    /**
     * Test calculations for coefficients forming arbitrary equation y = 3 * x^2 + 4 * x + 5.
     */
    @Test
    public void whenFirstArgIsThreeSecondArgIsFourThirdArgIsFiveAndSupplyOneThenResultIsTwelve() {
        Square squareEquation = new Square(3.0f, 4.0f, 5.0f);
        float result = squareEquation.calculate(1.0f);
        Assert.assertThat(result, is(12.0f));
    }

    /**
     * Test calculations for coefficients forming equation y = x^2 - 2 * x - 3.
     * Supply x=-1 and x=3 both results should be zero, as this is answers for quadratic equation x^2 - 2 * x - 3 = 0
     */
    @Test
    public void whenFirstArgIsOneSecondArgIsMinusTwoThirdArgIsMinusThreeAndSupplyMinusOneAndSupplyThreeThenResultIsZeroInBothCases() {
        Square squareEquation = new Square(1.0f, -2.0f, -3.0f);
        float resultForMinusOne = squareEquation.calculate(-1.0f);
        float resultForThree = squareEquation.calculate(3.0f);
        Assert.assertThat(resultForMinusOne, is(0.0f));
        Assert.assertThat(resultForThree, is(0.0f));
    }

    /**
     * Test calculations for coefficients forming equation y = 5 * x^2 + 3 * x + 7.
     * For any supplied x answer will never be zero, cause this quadratic equation 5 * x^2 + 3 * x + 7 = 0 has no answers
     */
    @Test
    public void whenFirstArgIsFiveSecondArgIsThreeThirdArgIsSevenAndSupplyRandomFloatThenResultIsNeverEqualZero() {
        Random random = new Random();

        Square squareEquation = new Square(5.0f, 3.0f, 7.0f);
        float result = squareEquation.calculate(random.nextFloat());
        Assert.assertThat(result, not(0.0f));
    }

    // For the following tests the MockOutput object is used to check the printouts

    /**
     * Show values from x1=0.0 to x2=1.0 and step 1.0 for simple function y = x^2.
     */
    @Test
    public void whenShowFromZeroToOneWithStepOneForXSquaredThenPrintZeroOne() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(1.0f, 0.0f, 0.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(0.0f, 1.0f, 1.0f);
        Assert.assertThat(mockOutput.getOutput(), is("0.0  1.0"));
    }

    /**
     * Show values from x1=0.0 to x2=5.0 and step 1.0 for simple function y = x^2.
     */
    @Test
    public void whenShowFromZeroToFiveWithStepOneForXSquaredThenPrintZeroOneFourNineSixteenTwentyFive() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(1.0f, 0.0f, 0.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(0.0f, 5.0f, 1.0f);
        Assert.assertThat(mockOutput.getOutput(), is("0.0  1.0  4.0  9.0  16.0  25.0"));
    }

    /**
     * Show values from x1=0.0 to x2=5.0 and step 2.0 for simple function y = x^2.
     * Last calculation for x2=5.0 should be also included.
     */
    @Test
    public void whenShowFromZeroToFiveWithStepTwoForXSquaredThenPrintZeroFourSixteenTwentyFive() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(1.0f, 0.0f, 0.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(0.0f, 5.0f, 2.0f);
        Assert.assertThat(mockOutput.getOutput(), is("0.0  4.0  16.0  25.0"));
    }

    /**
     * Show values from x1=-5.0 to x2=5.0 and step 5.0 for function y = 2 * x^2 + 1.
     */
    @Test
    public void whenShowFromMinusFiveToFiveWithStepFiveForTwoXSquaredPlusOneThenPrintFiftyOneOneFiftyOne() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(2.0f, 0.0f, 1.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(-5.0f, 5.0f, 5.0f);
        Assert.assertThat(mockOutput.getOutput(), is("51.0  1.0  51.0"));
    }

    /**
     * Show values from x1=-3.0 to x2=-1.5 and step 0.5 for function y = x^2 + x + 1.
     * Results truncated up to one decimal digit.
     */
    @Test
    public void whenShowFromMinusThreeToMinusOneAndHalfWithStepDecimalFiveForXSquaredPlusXPlusOneThenPrintSevenFourDecimalEightThreeOneDecimalEight() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(1.0f, 1.0f, 1.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(-3.0f, -1.5f, 0.5f);
        Assert.assertThat(mockOutput.getOutput(), is("7.0  4.8  3.0  1.8"));
    }

    /**
     * Show values from x1=5.0 to x2=2.0 and step 2.0 for function y = 2 * x^2 + 2 * x + 3.
     * Results should be for section [2.0, 5.0] with step 2.0
     */
    @Test
    public void whenShowFromFiveToTwoWithStepTwoForTwoXSquaredPlusTwoXPlusThreeThenPrintFifteenFortyThreeSixtyThree() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(2.0f, 2.0f, 3.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(5.0f, 2.0f, 2.0f);
        Assert.assertThat(mockOutput.getOutput(), is("15.0  43.0  63.0"));
    }

    /**
     * Show values from x1=-1.0 to x2=-3.0 and step -1.0 for function y = x^2 + 10 * x.
     * Results should be for section [-3.0, -1.0] with step 1.0
     */
    @Test
    public void whenShowFromMinusOneToMinusThreeWithStepMinusOneForXSquaredPlusTenXThenPrintMinusTwentyOneMinusSixteenMinusNine() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(1.0f, 10.0f, 0.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(-1.0f, -3.0f, -1.0f);
        Assert.assertThat(mockOutput.getOutput(), is("-21.0  -16.0  -9.0"));
    }

    /**
     * Show values from x1=0.0 to x2=0.0 and step 10.0 for function y = x^2 + 10 * x + 1.
     * Only one value should be printed.
     */
    @Test
    public void whenShowFromZeroToZeroWithStepTenForXSquaredPlusTenXPlusOneThenPrintOne() {
        MockOutput mockOutput = new MockOutput();

        Square squareEquation = new Square(1.0f, 10.0f, 1.0f);
        squareEquation.setOutput(mockOutput);
        squareEquation.show(0.0f, 0.0f, 10.0f);
        Assert.assertThat(mockOutput.getOutput(), is("1.0"));
    }

}

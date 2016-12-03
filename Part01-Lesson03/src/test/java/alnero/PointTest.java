package alnero;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Testing class Point and its public methods.
 */
public class PointTest {

    /**
     * Test creating of simple point object and getting its coordinates.
     */
    @Test
    public void whenCreatePointZeroZeroThenReturnCoordinatesZeroZero() {
        Point point = new Point(0.0, 0.0);
        double x = point.getX();
        double y = point.getY();
        Assert.assertThat(x, is(0.0));
        Assert.assertThat(y, is(0.0));
    }

    /**
     * Test distance between center of axes and point on x-axe.
     */
    @Test
    public void whenCalculateDistanceBetweenZeroZeroAndFiveZeroThenReturnFive() {
        Point point1 = new Point(0.0, 0.0);
        Point point2 = new Point(5.0, 0.0);
        double distanceBetweenPoints = point1.distanceTo(point2);
        Assert.assertThat(distanceBetweenPoints, is(5.0));
    }

    /**
     * Test distance between two point on x-axe.
     */
    @Test
    public void whenCalculateDistanceBetweenMinusFiveZeroAndFiveZeroThenReturnTen() {
        Point point1 = new Point(-5.0, 0.0);
        Point point2 = new Point(5.0, 0.0);
        double distanceBetweenPoints = point1.distanceTo(point2);
        Assert.assertThat(distanceBetweenPoints, is(10.0));
    }

    /**
     * Test distance between two point on y-axe.
     */
    @Test
    public void whenCalculateDistanceBetweenZeroSevenAndZeroMinusFiveThenReturnTwelve() {
        Point point1 = new Point(0.0, 7.0);
        Point point2 = new Point(0.0, -5.0);
        double distanceBetweenPoints = point1.distanceTo(point2);
        Assert.assertThat(distanceBetweenPoints, is(12.0));
    }

    /**
     * Test distance between equal points.
     */
    @Test
    public void whenCalculateDistanceBetweenEqualPointsThenReturnZero() {
        Point point1 = new Point(-15, 42);
        Point point2 = new Point(-15, 42);
        double distanceBetweenPoints = point1.distanceTo(point2);
        Assert.assertThat(distanceBetweenPoints, is(0.0));
    }

    /**
     * Test that distance between two random points is always grater or equals zero.
     */
    @Test
    public void whenCalculateDistanceBetweenTwoRandomPointsThenResultIsAlwaysPositive() {
        Random random = new Random();
        Point point1 = new Point(random.nextDouble(), random.nextDouble());
        Point point2 = new Point(random.nextDouble(), random.nextDouble());
        double distanceBetweenPoints = point1.distanceTo(point2);
        Assert.assertThat(distanceBetweenPoints, greaterThanOrEqualTo(0.0));
    }
}

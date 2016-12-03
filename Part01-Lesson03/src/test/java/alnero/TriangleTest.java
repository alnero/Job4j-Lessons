package alnero;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

/**
 * Testing class Triangle and its public methods.
 */
public class TriangleTest {
    /**
     * Test that triangle object is created using three different points.
     */
    @Test
    public void whenCreatingTriangleThenItIsCreatedSuccessfully() {
        Point a = new Point(0, 3);
        Point b = new Point(4, 0);
        Point c = new Point(0, 0);
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertThat(triangle.getClass().getSimpleName(), is("Triangle"));
    }

    /**
     * Calculate area of the simple triangle.
     */
    @Test
    public void whenCalculateAreaOfTriangleZeroZeroZeroThreeFourZeroThenResultIsSix() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertThat(triangle.area(), is(6.0));
    }

    /**
     * Check that for random three points triangle area is always positive.
     */
    @Test
    public void whenCalculateAreaOfRandomPointsTriangleThenResultIsAlwaysPositive() {
        Random random = new Random();
        Point a = new Point(random.nextDouble(), random.nextDouble());
        Point b = new Point(random.nextDouble(), random.nextDouble());
        Point c = new Point(random.nextDouble(), random.nextDouble());
        Triangle triangle = new Triangle(a, b, c);
        Assert.assertThat(triangle.area(), greaterThanOrEqualTo(0.0));
    }

    /**
     * Test that when create a triangle with all points on the same vertical line an area is zero.
     */
    @Test
    public void whenCreateTriangleWithAllPointsOnOneVerticalLineThenAreaIsZero() {
        Random random = new Random();
        double fixedYCoordinate = random.nextDouble();

        Point a = new Point(random.nextDouble(), fixedYCoordinate);
        Point b = new Point(random.nextDouble(), fixedYCoordinate);
        Point c = new Point(random.nextDouble(), fixedYCoordinate);

        Triangle triangle = new Triangle(a, b, c);
        Assert.assertThat(triangle.area(), is(0.0));
    }

    /**
     * Test that when create a triangle with all points on the same line an area is zero.
     * Private class Line is used for generating random points on the same line.
     */
    @Test
    public void whenCreateTriangleWithAllPointsOnTheSameLineThenAreaIsZero() {
        Random random = new Random();
        Line line = new Line(random.nextDouble(), random.nextDouble());

        Point a = line.getRandomPoint();
        Point b = line.getRandomPoint();
        Point c = line.getRandomPoint();

        Triangle triangle = new Triangle(a, b, c);
        Assert.assertThat(triangle.area(), is(0.0));
    }

    /**
     * Test that triangle object returns the list of points with which it was created.
     */
    @Test
    public void whenCreateTriangleWithThreePointsThenTriangleReturnsListWithThatPoints() {
        Random random = new Random();
        Point a = new Point(random.nextDouble(), random.nextDouble());
        Point b = new Point(random.nextDouble(), random.nextDouble());
        Point c = new Point(random.nextDouble(), random.nextDouble());
        Triangle triangle = new Triangle(a, b, c);

        List<Point> points = triangle.getPoints();
        Assert.assertThat(points.get(0), is(a));
        Assert.assertThat(points.get(1), is(b));
        Assert.assertThat(points.get(2), is(c));
    }

    /**
     * Class for generating points located on one line.
     * Line is described by following formula y = k*x + b.
     */
    private class Line {
        /**
         * Parameters describing the line y = k*x + b.
         * k - slope or gradient of the line. */
        private double k;
         /** b - y-intercept of the line. */
        private double b;

        /**
         * Line can be created only with coefficients.
         * @param k slope or gradient
         * @param b y-intercept
         */
        Line(double k, double b) {
            this.k = k;
            this.b = b;
        }

        /**
         * Returns random point on the line.
         * Coordinates of the point are calculated according to line formula and coefficients.
         * @return random object of class Point
         */
        Point getRandomPoint() {
            double x = new Random().nextDouble();
            double y = this.k * x + this.b;

            return new Point(x, y);
        }
    }
}
package alnero;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Testing class TriangleMeter and its public method to get the longest side of triangle.
 */
public class TriangleMeterTest {
    /**
     * Test for the longest side in triangle 3, 4, 5.
     */
    @Test
    public void whenCalculateLongestSideOfTriangleThreeFourFiveThenResultIsFive() {
        Point a = new Point(0.0, 0.0);
        Point b = new Point(0.0, 4.0);
        Point c = new Point(3.0, 0.0);

        double distanceBetweenPointsBandC = b.distanceTo(c);
        Assert.assertThat(distanceBetweenPointsBandC, is(5.0));

        Triangle triangle = new Triangle(a, b, c);
        TriangleMeter triangleMeter = new TriangleMeter(triangle);
        double maxSide = triangleMeter.maxSide();
        Assert.assertThat(maxSide, is(5.0));
    }

    /**
     * Test for the longest side in triangle, which is on y-axis.
     */
    @Test
    public void whenLongestSideOfTriangleOnYAxisThenThisSideIsReturned() {
        // longest side is on y-axis between points (0,0) and (0, 10)
        Point a = new Point(0.0, 0.0);
        Point b = new Point(0.0, 10.0);
        Point c = new Point(1.0, 5.0);
        Triangle triangle = new Triangle(a, b, c);
        TriangleMeter triangleMeter = new TriangleMeter(triangle);
        double maxSide = triangleMeter.maxSide();
        Assert.assertThat(maxSide, is(10.0));
    }
}

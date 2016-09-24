package alnero;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Testing class Max and its public method to get the longest side of triangle.
 */
public class MaxTest {
    /**
     * Test for the longest side in triangle 3, 4, 5.
     */
    @Test
    public void whenCalculateLongestSideOfTriangleThreeFourFiveThenResultIsFive() {
        Point a = new Point(0.0, 0.0);
        Point b = new Point(0.0, 4.0);
        Point c = new Point(3.0, 0.0);

        Triangle triangle = new Triangle(a, b, c);
        List<Point> points = triangle.getPoints();
        double sideAB = points.get(0).distanceTo(points.get(1));
        double sideBC = points.get(1).distanceTo(points.get(2));
        double sideCA = points.get(2).distanceTo(points.get(0));

        Max triangleMaxMeter = new Max(triangle);
        double maxSide = triangleMaxMeter.max(sideAB, sideBC, sideCA);

        Assert.assertThat(maxSide, is(5.0));
    }

    /**
     * Test for the triangle longest side which is on y-axis.
     */
    @Test
    public void whenLongestSideOfTriangleOnYAxisThenThisSideIsReturned() {
        // longest side is on y-axis between points (0,0) and (0, 10)
        Point a = new Point(0.0, 0.0);
        Point b = new Point(0.0, 10.0);
        Point c = new Point(1.0, 5.0);

        Triangle triangle = new Triangle(a, b, c);
        List<Point> points = triangle.getPoints();
        double sideAB = points.get(0).distanceTo(points.get(1));
        double sideBC = points.get(1).distanceTo(points.get(2));
        double sideCA = points.get(2).distanceTo(points.get(0));

        Max triangleMeter = new Max(triangle);
        double maxSide = triangleMeter.max(sideAB, sideBC, sideBC);

        Assert.assertThat(maxSide, is(10.0));
    }
}

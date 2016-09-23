package alnero;

import java.util.List;

/**
 * Class with single method to get the longest side of the triangle.
 */
public class TriangleMeter {
    /**
     * Triangle for which calculations are performed
     */
    private Triangle triangle;

    /**
     * Object of the class is always created for one particular triangle.
     * @param triangle calculations will be performed on this triangle
     */
    public TriangleMeter(Triangle triangle) {
        this.triangle = triangle;
    }


    /**
     * Calculates the longest side in the triangle using the List of points returned by the triangle.
     * @return longest side of the triangle
     */
    public double maxSide() {
        List<Point> points = this.triangle.getPoints();

        double maxSide = 0;

        int numOfPoints = points.size();
        for (int i = 0; i < numOfPoints; i++) {
            Point beginPoint = points.get(i);
            Point endPoint;

            // if we reach last point, we calculate distance to first point in the list
            if (i + 1 == numOfPoints) {
                endPoint = points.get(0);
            } else {
                endPoint = points.get(i + 1);
            }

            double length = beginPoint.distanceTo(endPoint);
            if(length >= maxSide) {
                maxSide = length;
            }
        }
        return maxSide;
    }

}

package alnero;

import java.util.List;

/**
 * Uniform class to calculate measures of the Shapes.
 * The used geometrical shape should implement interface Shape.
 */
public class ShapeMeter {
    /**
     * Shape for which calculations are performed.
     */
    private Shape shape;

    /**
     * The object of ShapeMeter is created for one particular geometrical shape
     * @param shape calculations will be performed on this shape
     */
    public ShapeMeter(Shape shape) {
        this.shape = shape;
    }

    /**
     * Calculates the longest side in shape using the List of points returned by it.
     * @return longest side of the shape
     */
    public double maxSide() {
        List<Point> points = this.shape.getPoints();

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
            if (length >= maxSide) {
                maxSide = length;
            }
        }
        return maxSide;
    }
}

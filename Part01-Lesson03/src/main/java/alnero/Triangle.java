package alnero;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating 2D triangle objects using three points.
 */
public class Triangle {
    /**
     * Precision of calculations, number of decimal digits in calculation results.
     */
    private static final int NUMBER_OF_DECIMALS = 8;
    /**
     * A - point of the triangle.
     */
    private Point a;
    /**
     * B - point of the triangle.
     */
    private Point b;
    /**
     * C - point of the triangle.
     */
    private Point c;

    /**
     * Triangle can be created using three point objects.
     * If all points are on the same line the returned area of triangle will be zero.
     * @param a point a
     * @param b point b
     * @param c point c
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Calculating area using formula for three points coordinates http://www.mathopenref.com/coordtrianglearea.html
     * Calculated area is always taken by absolute value (can't be negative, but can be zero).
     * Result is rounded to defined number of decimals.
     * @return area of triangle
     */
    public double area() {
        double area = 0.5 * (
                (this.a.getX() - this.c.getX()) * (this.b.getY() - this.c.getY())
              - (this.b.getX() - this.c.getX()) * (this.a.getY() - this.c.getY())
        );
        return Math.abs(roundToDecimalPlaces(area, NUMBER_OF_DECIMALS));
    }

    /**
     * Return List of Point objects with which the triangle was created.
     * @return List filled with three triangle points
     */
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        points.add(this.a);
        points.add(this.b);
        points.add(this.c);
        return points;
    }

    /**
     * Performs rounding of the result of calculations.
     * @param value number which is to be rounded
     * @param numOfDecimal number of digits after the decimal
     * @return result rounded to the specified number of decimal places
     */
    private double roundToDecimalPlaces(double value, int numOfDecimal) {
        double multiplier = Math.pow(10, numOfDecimal);
        return Math.round(value * multiplier) / multiplier;
    }

}

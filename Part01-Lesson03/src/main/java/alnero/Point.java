package alnero;

/**
 * Class representing points on 2D surface. Point can calculate distance to other point.
 */
public class Point {
    /**
     * Variables hold the coordinates of the point.
     */
    private double x;
    private double y;

    /**
     * Point can be created only with coordinates.
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return x coordinate of the point object.
     * @return x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return y coordinate of the point object.
     * @return y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * Calculate distance to other point
     * @param point point to which the distance should be measured
     * @return distance between current and supplied point
     */
    public double distanceTo(Point point) {
        double partOverX = Math.pow(point.getX() - this.getX(), 2);
        double partOverY = Math.pow(point.getY() - this.getY(), 2);
        return Math.sqrt(partOverX + partOverY);
    }
}

package alnero;

import java.util.List;

/**
 * Interface for the methods all shapes should implement
 */
public interface Shape {
    /**
     * Every shape should return the list of its points.
     * @return list of all points of the shape
     */
    public List<Point> getPoints();

}

package alnero;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class with single method to get the longest side of the triangle.
 */
public class Max {
    /**
     * Triangle for which calculations are performed
     */
    private Triangle triangle;

    /**
     * Object of the class is always created for one particular triangle.
     * @param triangle calculations will be performed on this triangle
     */
    public Max(Triangle triangle) {
        this.triangle = triangle;
    }


    /**
     * Find the longest side using three sides of the triangle.
     * @return longest side of the triangle
     */
    public double max(double sideAB, double sideBC, double sideCA) {
        double maxSide = 0;
        double[] sides = {sideAB, sideBC, sideCA};

        for (double side : sides) {
            if (side > maxSide) {
                maxSide = side;
            }
        }

        return maxSide;
    }

    /**
     * Example of variable arguments method to find the longest side in other shapes.
     * @param sides variable number of sides of the shape
     * @return longest side of the shape
     */
    public double max(Double... sides) {
        List listOfSides = Arrays.asList(sides);
        return (Double) Collections.max(listOfSides);
    }

}

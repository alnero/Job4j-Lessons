package alnero.intMatrix;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to convert 2D integer matrix to integer list using streams.
 */
public class IntMatrix {
    /**
     * Convert 2D integer matrix to integer list.
     * @param intMatrix 2D integer matrix array
     * @return boxed integer list
     */
    public List<Integer> convertTwoDimensionalIntMatrixToIntList(Integer[][] intMatrix) {
        List<Integer> result = Arrays.stream(intMatrix).flatMap(Arrays::stream).collect(Collectors.toList());
        return result;
    }
}

package alnero.collectionsFramework.ConvertArrayToList;

import java.util.ArrayList;
import java.util.List;

/**
 * Converting 2-dimensional array to a list.
 */
public class ConvertArrayToList {
    /**
     * Convert 2-dimensional array to a list.
     * @param array array to convert
     * @return list filled with data from array cells
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int cell : row) {
                list.add(cell);
            }
        }
        return list;
    }

    /**
     * Convert list of arrays to list with arrays contents.
     * @param listOfArrays list with arrays (different length possible)
     * @return common list with arrays contents
     */
    public List<Integer> convert(List<int[]> listOfArrays) {
        List<Integer> list = new ArrayList<>();
        for (int[] array : listOfArrays) {
            for (int cell : array) {
                list.add(cell);
            }
        }
        return list;
    }
}
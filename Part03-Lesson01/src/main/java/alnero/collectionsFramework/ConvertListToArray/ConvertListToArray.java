package alnero.collectionsFramework.ConvertListToArray;

import java.util.List;

/**
 * Converting list to a 2-dimensional array.
 */
public class ConvertListToArray {
    /**
     * Convert list to 2-dimensional array with required number of rows.
     * Number of cells in every row is the same, if not enough list elements fill cells with zeros.
     * @param list list to convert
     * @param rows number of rows in resulting array
     * @return 2-dimensional array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cells];
        int row = 0;
        int cell = 0;
        for (Integer listValue : list) {
            array[row][cell] = listValue;
            cell++;
            if (cell == cells) {
                row++;
                cell = 0;
            }
        }
        return array;
    }
}

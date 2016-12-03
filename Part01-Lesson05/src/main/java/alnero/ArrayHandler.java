package alnero;

/**
 * Class to handle two-dimensional square arrays:
 *   - rotate
 *   - transpose
 *   - reverse rows/columns
 * Class DOES NOT return new arrays, it changes arguments supplied to methods and returns them.
 */
public class ArrayHandler {

    /**
     * Transposing array in place - rows become columns.
     * Example:
     * [1, 1, 1]        [1, 2, 3]
     * [2, 2, 2]   ->   [1, 2, 3]
     * [3, 3, 3]        [1, 2, 3]
     * @param values 2D square array to be transposed
     * @return supplied array of values but transposed or initial array if it is not square array
     */
    public int[][] transposeArray(int[][] values) {
        if (!isSquareArray(values)) {
            return values;
        }

        int rows = values.length;
        int columns = values[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < columns; j++) {
                int temp = values[i][j];
                values[i][j] = values[j][i];
                values[j][i] = temp;
            }
        }

        return values;
    }

    /**
     * Reversing array rows in place.
     * Example:
     * [0, 1, 2]        [2, 1, 0]
     * [0, 1, 2]   ->   [2, 1, 0]
     * [0, 1, 2]        [2, 1, 0]
     * @param values 2D square array rows of which to be reversed
     * @return supplied array of values but with reversed rows or initial array if it is not square array
     */
    public int[][] reverseRows(int[][] values) {
        if (!isSquareArray(values)) {
            return values;
        }

        int rows = values.length;
        int columns = values[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns / 2; j++) {
                int temp = values[i][j];
                values[i][j] = values[i][columns - 1 - j];
                values[i][columns - 1 - j] = temp;
            }
        }

        return values;
    }

    /**
     * Reversing array columns in place.
     * Example:
     * [0, 0, 0]        [2, 2, 2]
     * [1, 1, 1]   ->   [1, 1, 1]
     * [2, 2, 2]        [0, 0, 0]
     * @param values 2D square array columns of which to be reversed
     * @return supplied array of values but with reversed columns or initial array if it is not square array
     */
    public int[][] reverseColumns(int[][] values) {
        if (!isSquareArray(values)) {
            return values;
        }

        int rows = values.length;
        int columns = values[0].length;

        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < columns; j++) {
                int temp = values[i][j];
                values[i][j] = values[rows - 1 - i][j];
                values[rows - 1 - i][j] = temp;
            }
        }

        return values;
    }

    /**
     * Turning 2D square array 90 degrees clockwise:
     * [0, 1, 2]        [0, 0, 0]
     * [0, 1, 2]   ->   [1, 1, 1]
     * [0, 1, 2]        [2, 2, 2]
     * To rotate array 90 degrees clockwise first we need to transpose it, then reverse rows.
     * @param values array to be turned 90 degrees clockwise
     * @return supplied array of values but turned 90 degrees clockwise or initial array if it is not square array
     */
    public int[][] rotateArray90DegreesClockwise(int[][] values) {
        if (!isSquareArray(values)) {
            return values;
        }

        return this.reverseRows(this.transposeArray(values));
    }

    /**
     * Turning 2D square array 90 degrees anti-clockwise:
     * [0, 1, 2]        [2, 2, 2]
     * [0, 1, 2]   ->   [1, 1, 1]
     * [0, 1, 2]        [0, 0, 0]
     * To rotate array 90 degrees anti-clockwise first we need to transpose it, then reverse columns.
     * @param values array to be turned 90 degrees anti-clockwise
     * @return supplied array of values but turned 90 degrees anti-clockwise or initial array if it is not square array
     */
    public int[][] rotateArray90DegreesAntiClockwise(int[][] values) {
        if (!isSquareArray(values)) {
            return values;
        }

        return this.reverseColumns(this.transposeArray(values));
    }

    /**
     * Check that 2D array is a square array, rows == columns.
     * @param values array to be checked
     * @return boolean true if array is squared, false if not
     */
    private boolean isSquareArray(int[][] values) {
        return values.length == values[0].length;
    }
}

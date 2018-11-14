package alnero.collectionsFramework.ConvertListToArray;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test conversion of list to 2-dimensional array.
 */
public class ConvertListToArrayTest {
    /**
     * List with 7 elements to 3 rows array -> 3x3 array.
     */
    @Test
    public void whenConvert7ElementsListTo3RowsArrayThen3by3ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 10 elements to 5 rows array -> 5x2 array.
     */
    @Test
    public void whenConvert10ElementsListTo5RowsArrayThen5by2ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                5
        );
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 10}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 10 elements to 2 rows array -> 2x5 array.
     */
    @Test
    public void whenConvert10ElementsListTo2RowsArrayThen2by5ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 13 elements to 4 rows array -> 4x4 array.
     */
    @Test
    public void whenConvert13ElementsListTo4RowsArrayThen4by4ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13),
                4
        );
        int[][] expect = {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 0,  0,  0}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 13 elements to 1 row array -> 1x13 array.
     */
    @Test
    public void whenConvert13ElementsListTo1RowArrayThen1by13ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13),
                1
        );
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 3 elements to 3 row array -> 3x1 array.
     */
    @Test
    public void whenConvert3ElementsListTo3RowsArrayThen3by1ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3),
                3
        );
        int[][] expect = {
                {1},
                {2},
                {3}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 3 elements to 6 row array -> 6x1 array.
     */
    @Test
    public void whenConvert3ElementsListTo6RowsArrayThen6by1ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1, 2, 3),
                6
        );
        int[][] expect = {
                {1},
                {2},
                {3},
                {0},
                {0},
                {0}
        };
        assertThat(result, is(expect));
    }

    /**
     * List with 1 element to 2 row array -> 2x1 array.
     */
    @Test
    public void whenConvert1ElementListTo2RowsArrayThen2by1ArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                Arrays.asList(1),
                2
        );
        int[][] expect = {
                {1},
                {0},
        };
        assertThat(result, is(expect));
    }

    /**
     * List with NO element to 2 row array -> 2 rows empty array.
     */
    @Test
    public void whenConvertListWithNoElementTo2RowsArrayThen2RowsEmptyArrayReturned() {
        ConvertListToArray listConverter = new ConvertListToArray();
        int[][] result = listConverter.toArray(
                new ArrayList<>(),
                2
        );
        int[][] expect = {
                {},
                {},
        };
        assertThat(result, is(expect));
    }
}
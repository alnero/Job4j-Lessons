package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Testing class RotatingArray and its methods for handling square two-dimensional arrays:
 *   - rotate clockwise and anticlockwise
 *   - transpose
 *   - reverse rows
 *   - reverse columns
 */
public class ArrayHandlerTest {
    private ArrayHandler arrayHandler;

    @Before
    public void createCommonArrayHandlerObjectForTests() {
        arrayHandler = new ArrayHandler();
    }

    /**
     * Test that simple 2D array is transposed properly:
     * [1, 1, 1]        [1, 2, 3]
     * [2, 2, 2]   ->   [1, 2, 3]
     * [3, 3, 3]        [1, 2, 3]
     */
    @Test
    public void whenTransposeThreeByThreeArrayThenReturnArrayWhereRowsAndColumnsAreSwitchedPlaces() {
        int[][] initialArray = new int[][]{
            {1, 1, 1},
            {2, 2, 2},
            {3, 3, 3}
        };

        int[][] checkArray = new int[][]{
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
        };

        int[][] transposedArray = this.arrayHandler.transposeArray(initialArray);
        Assert.assertThat(transposedArray, is(checkArray));
    }

    /**
     * Array transposed twice is the initial array.
     */
    @Test
    public void whenTransposeFiveByFiveArrayTwiceThenReturnInitialArray() {
        int[][] initialArray = new int[][]{
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        };

        int[][] checkArray = new int[][]{
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        };

        int[][] firstTranspose = this.arrayHandler.transposeArray(initialArray);
        int[][] secondTranspose = this.arrayHandler.transposeArray(firstTranspose);
        Assert.assertThat(secondTranspose, is(checkArray));
    }

    /**
     * Test reverse of rows in square 2D array:
     * [0, 1, 2]        [2, 1, 0]
     * [0, 1, 2]   ->   [2, 1, 0]
     * [0, 1, 2]        [2, 1, 0]
     */
    @Test
    public void whenReverseRowsInThreeByThreeArrayThenReturnArrayWhereElementsInRowsAreReversed() {
        int[][] initialArray = new int[][]{
                {0, 1, 2},
                {0, 1, 2},
                {0, 1, 2}
        };

        int[][] checkArray = new int[][]{
                {2, 1, 0},
                {2, 1, 0},
                {2, 1, 0}
        };

        int[][] reversedRowsArray = this.arrayHandler.reverseRows(initialArray);
        Assert.assertThat(reversedRowsArray, is(checkArray));
    }

    /**
     * Test reverse of rows in six by six 2D array.
     */
    @Test
    public void whenReverseRowsInSixBySixArrayThenReturnArrayWhereElementsInRowsAreReversed() {
        int[][] initialArray = new int[][]{
                {0, 1, 2, 3, 4, 5},
                {0, 1, 2, 3, 4, 5},
                {0, 1, 2, 3, 4, 5},
                {0, 1, 2, 3, 4, 5},
                {0, 1, 2, 3, 4, 5},
                {0, 1, 2, 3, 4, 5}
        };

        int[][] checkArray = new int[][]{
                {5, 4, 3, 2, 1, 0},
                {5, 4, 3, 2, 1, 0},
                {5, 4, 3, 2, 1, 0},
                {5, 4, 3, 2, 1, 0},
                {5, 4, 3, 2, 1, 0},
                {5, 4, 3, 2, 1, 0}
        };

        int[][] reversedRowsArray = this.arrayHandler.reverseRows(initialArray);
        Assert.assertThat(reversedRowsArray, is(checkArray));
    }

    /**
     * Array in which rows are reversed twice is the initial array.
     */
    @Test
    public void whenReverseRowsInFourByFourArrayTwiceThenReturnInitialArray() {
        int[][] initialArray = new int[][]{
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3}
        };

        int[][] checkArray = new int[][]{
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3}
        };

        int[][] firstReverseOfRows = this.arrayHandler.reverseRows(initialArray);
        int[][] secondReverseOfRows = this.arrayHandler.reverseRows(firstReverseOfRows);
        Assert.assertThat(secondReverseOfRows, is(checkArray));
    }

    /**
     * Test that simple 2D array is turned 90 clockwise correctly:
     * [1, 1, 1]        [3, 2, 1]
     * [2, 2, 2]   ->   [3, 2, 1]
     * [3, 3, 3]        [3, 2, 1]
     */
    @Test
    public void whenRotateThreeByThreeArray90DegreesClockwiseThenReturnArrayRotated90DegreesClockwise() {
        int[][] initialArray = new int[][]{
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        };

        int[][] checkArray = new int[][]{
                {3, 2, 1},
                {3, 2, 1},
                {3, 2, 1}
        };

        int[][] rotatedArray = this.arrayHandler.rotateArray90DegreesClockwise(initialArray);
        Assert.assertThat(rotatedArray, is(checkArray));
    }

    /**
     * Test rotation of ten by ten array 90 degrees clockwise.
     */
    @Test
    public void whenRotateTenByTenArray90DegreesClockwiseThenReturnArrayRotated90DegreesClockwise() {
        int[][] initialArray = new int[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
        };

        int[][] checkArray = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                {7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                {8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                {9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
        };

        int[][] rotatedArray = this.arrayHandler.rotateArray90DegreesClockwise(initialArray);
        Assert.assertThat(rotatedArray, is(checkArray));
    }

    /**
     * 2D square array rotated 90 degrees clockwise four times is the initial array.
     */
    @Test
    public void whenRotateFourByFourArray90DegreesClockwiseFourTimesThenReturnInitialArray() {
        int[][] initialArray = new int[][]{
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {4, 4, 4, 4}
        };

        int[][] checkArray = new int[][]{
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {4, 4, 4, 4}
        };

        // rotations #1 -> #4
        int[][] firstRotation = this.arrayHandler.rotateArray90DegreesClockwise(initialArray);
        int[][] secondRotation = this.arrayHandler.rotateArray90DegreesClockwise(firstRotation);
        int[][] thirdRotation = this.arrayHandler.rotateArray90DegreesClockwise(secondRotation);
        int[][] fourthRotation = this.arrayHandler.rotateArray90DegreesClockwise(thirdRotation);
        Assert.assertThat(fourthRotation, is(checkArray));
    }

    /**
     * Test reverse of columns in square 2D array:
     * [0, 0, 0]        [2, 2, 2]
     * [1, 1, 1]   ->   [1, 1, 1]
     * [2, 2, 2]        [0, 0, 0]
     */
    @Test
    public void whenReverseColumnsInThreeByThreeArrayThenReturnArrayWhereElementsInColumnsAreReversed() {
        int[][] initialArray = new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {2, 2, 2}
        };

        int[][] checkArray = new int[][]{
                {2, 2, 2},
                {1, 1, 1},
                {0, 0, 0}
        };

        int[][] reversedColumnsArray = this.arrayHandler.reverseColumns(initialArray);
        Assert.assertThat(reversedColumnsArray, is(checkArray));
    }

    /**
     * Array in which columns are reversed twice is the initial array.
     */
    @Test
    public void whenReverseColumnsInFiveByFiveArrayTwiceThenReturnInitialArray() {
        int[][] initialArray = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4}
        };

        int[][] checkArray = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4}
        };

        int[][] firstReverseOfColumns = this.arrayHandler.reverseColumns(initialArray);
        int[][] secondReverseOfColumns = this.arrayHandler.reverseColumns(firstReverseOfColumns);
        Assert.assertThat(secondReverseOfColumns, is(checkArray));
    }

    /**
     * Test that simple 2D array is turned 90 anti-clockwise correctly:
     * [1, 1, 1]        [1, 2, 3]
     * [2, 2, 2]   ->   [1, 2, 3]
     * [3, 3, 3]        [1, 2, 3]
     */
    @Test
    public void whenRotateThreeByThreeArray90degreesAntiClockwiseThenReturnArrayRotated90DegreesAntiClockwise() {
        int[][] initialArray = new int[][]{
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        };

        int[][] checkArray = new int[][]{
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3}
        };

        int[][] rotationAntiClockwise = this.arrayHandler.rotateArray90DegreesAntiClockwise(initialArray);
        Assert.assertThat(rotationAntiClockwise, is(checkArray));
    }

    /**
     * 2D square array rotated 90 degrees clockwise and after rotated 90 degrees anti-clockwise is the initial array.
     */
    @Test
    public void whenRotateArray90degreesClockwiseAndAfterAntiClockwiseThenReturnInitialArray() {
        int[][] initialArray = new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5, 5},
                {6, 6, 6, 6, 6, 6, 6},
                {7, 7, 7, 7, 7, 7, 7}
        };

        int[][] checkArray = new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5, 5, 5},
                {6, 6, 6, 6, 6, 6, 6},
                {7, 7, 7, 7, 7, 7, 7}
        };

        int[][] rotationClockwise = this.arrayHandler.rotateArray90DegreesClockwise(initialArray);
        int[][] rotationAntiClockwise = this.arrayHandler.rotateArray90DegreesAntiClockwise(rotationClockwise);
        Assert.assertThat(rotationAntiClockwise, is(checkArray));
    }

    /**
     * Test that empty array does not cause any exception
     */
    @Test
    public void whenRotateEmptyArray90degreesClockwiseAndAfterAntiClockwiseThenReturnEmptyArray() {
        int[][] initialArray = new int[][]{
                {}
        };

        this.arrayHandler.reverseRows(initialArray);
        this.arrayHandler.reverseColumns(initialArray);
        this.arrayHandler.rotateArray90DegreesClockwise(initialArray);
        this.arrayHandler.rotateArray90DegreesAntiClockwise(initialArray);
    }

    /**
     * Test that NOT square array is returned unchanged by rotate methods.
     */
    @Test
    public void whenSupplyToRotateMethodsNotSquareArrayThenReturnUnchangedArray() {
        int[][] initialArray = new int[][]{
                {1},
                {1, 2},
                {1, 2, 3},
                {1, 2, 3, 4}
        };

        int[][] checkArray = new int[][]{
                {1},
                {1, 2},
                {1, 2, 3},
                {1, 2, 3, 4}
        };

        int[][] rotationClockwise = this.arrayHandler.rotateArray90DegreesClockwise(initialArray);
        int[][] rotationAntiClockwise = this.arrayHandler.rotateArray90DegreesAntiClockwise(initialArray);
        Assert.assertThat(rotationClockwise, is(checkArray));
        Assert.assertThat(rotationAntiClockwise, is(checkArray));
    }

    /**
     * Test that NOT square array is returned unchanged by reverse methods.
     */
    @Test
    public void whenSupplyToReverseMethodsNotSquareArrayThenReturnUnchangedArray() {
        int[][] initialArray = new int[][]{
                {1},
                {1, 2},
                {1, 2, 3},
                {1, 2, 3, 4}
        };

        int[][] checkArray = new int[][]{
                {1},
                {1, 2},
                {1, 2, 3},
                {1, 2, 3, 4}
        };

        int[][] reversedRowsArray = this.arrayHandler.reverseRows(initialArray);
        int[][] reversedColumnsArray = this.arrayHandler.reverseColumns(initialArray);
        Assert.assertThat(reversedRowsArray, is(checkArray));
        Assert.assertThat(reversedColumnsArray, is(checkArray));
    }
}

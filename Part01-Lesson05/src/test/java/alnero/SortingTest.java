package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.core.Is.is;

/**
 * Testing class Sorting and its method bubbleSort()
 */
public class SortingTest {
    /**
     * Common objects to use in tests
     */
    private Sorting sorting;
    private Random random;

    /**
     * Initializing common objects for tests
     */
    @Before
    public void createCommonSortingAndRandomObjectsForTests() {
        sorting = new Sorting();
        random = new Random();
    }

    /**
     * Simple array sorting [3,2,1] -> [1,2,3]
     */
    @Test
    public void whenBubbleSortArrayThreeTwoOneThenReturnArrayOneTwoThree() {
        int[] sortedArray = this.sorting.bubbleSort(new int[]{3, 2, 1});
        Assert.assertThat(sortedArray, is(new int[]{1, 2, 3}));
    }

    /**
     * Sorting array with duplicate values [9,9,9,1,2,3] -> [1,2,3,9,9,9]
     */
    @Test
    public void whenBubbleSortArrayNineNineNineOneTwoThreeThenReturnArrayOneTwoThreeNineNineNine() {
        int[] sortedArray = this.sorting.bubbleSort(new int[]{9, 9, 9, 1, 2, 3});
        Assert.assertThat(sortedArray, is(new int[]{1, 2, 3, 9, 9, 9}));
    }

    /**
     * Sorting array of negative numbers [-5,-10,-3,-6] -> [-10,-6,-5,-3]
     */
    @Test
    public void whenBubbleSortArrayMinusFiveMinusTenMinusThreeMinusSixThenReturnArrayMinusTenMinusSixMinusFiveMinusThree() {
        int[] sortedArray = this.sorting.bubbleSort(new int[]{-5, -10, -3, -6});
        Assert.assertThat(sortedArray, is(new int[]{-10, -6, -5, -3}));
    }

    /**
     * Sorting array with the same value in the middle [2,1,0,-1,-2] -> [-2,-1,0,1,2]
     */
    @Test
    public void whenBubbleSortArrayTwoOneZeroMinusOneMinusTwoThenReturnArrayMinusTwoMinusOneZeroOneTwo() {
        int[] sortedArray = this.sorting.bubbleSort(new int[]{2, 1, 0, -1, -2});
        Assert.assertThat(sortedArray, is(new int[]{-2, -1, 0, 1, 2}));
    }

    /**
     * Sorting array with one random integer, the same array should be returned [randomInt] -> [sameRandomInt]
     */
    @Test
    public void whenBubbleSortArrayWithOneRandomValueThenReturnArrayWithThisValue() {
        int randomInteger = this.random.nextInt();

        int[] array = new int[]{randomInteger};

        int[] sortedArray = this.sorting.bubbleSort(array);
        Assert.assertThat(sortedArray, is(new int[]{randomInteger}));
    }

    /**
     * Sorting empty array [] -> []
     */
    @Test
    public void whenBubbleSortEmptyArrayThenReturnEmptyArray() {
        int[] sortedArray = this.sorting.bubbleSort(new int[]{});
        Assert.assertThat(sortedArray, is(new int[]{}));
    }

    /**
     * Sorting same array with bubble sort and standard java array sort should return same results.
     */
    @Test
    public void whenSortRandomArrayOfTenIntegersWithBubbleSortAndStandardJavaArraySortThenBothArraysAreTheSame() {
        int[] arrayForBubbleSort = new int[10];
        int[] arrayForStandardSort = new int[10];

        for(int i = 0; i < 10; i++) {
            int randomInt = this.random.nextInt();
            arrayForBubbleSort[i] = randomInt;
            arrayForStandardSort[i] = randomInt;
        }

        Arrays.sort(arrayForStandardSort);
        this.sorting.bubbleSort(arrayForBubbleSort);

        Assert.assertThat(arrayForStandardSort, is(arrayForBubbleSort));
    }
}

package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.core.Is.is;

/**
 * Class for testing method for merge sorting of two pre-sorted integer arrays.
 */
public class MergeSortTest {
    /**
     * Common MergeSort object for testing.
     */
    private MergeSort mergeSort;

    /**
     * Create common MergeSort object for testing.
     */
    @Before
    public void createCommonMergeSortObjectForTests() {
        mergeSort = new MergeSort();
    }

    /**
     * Merge sort of two simple 'one integer' arrays.
     * {1}, {2} => {1, 2}
     */
    @Test
    public void whenMergeSortTwoArraysContainingOneIntegerThenSortedArrayOfTwoIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{1}, new int[]{2});
        Assert.assertThat(result, is(new int[]{1, 2}));
    }

    /**
     * Merge sort of two integer arrays, each containing five integers.
     * {1, 3, 5, 7, 9}, {2, 4, 6, 8, 10} => {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
     */
    @Test
    public void whenMergeSortTwoArraysEachContainingFiveIntegersThenSortedArrayOfTenIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10});
        Assert.assertThat(result, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    /**
     * Merge sort of two integer arrays, when first is shorter than second.
     * {1, 3}, {2, 4, 5, 6, 7} => {1, 2, 3, 4, 5, 6, 7}
     */
    @Test
    public void whenMergeSortTwoArraysAndFirstIsShorterThanSecondThenSortedArrayOfIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{1, 3}, new int[]{2, 4, 5, 6, 7});
        Assert.assertThat(result, is(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    /**
     * Merge sort of two integer arrays, when second is shorter than first one.
     * {1, 3, 5, 6, 7}, {2, 4} => {1, 2, 3, 4, 5, 6, 7}
     */
    @Test
    public void whenMergeSortTwoArraysAndSecondIsShorterThanFirstThenSortedArrayOfIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{1, 3, 5, 6, 7}, new int[]{2, 4});
        Assert.assertThat(result, is(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    /**
     * Merge sort of two integer arrays of different length containing negative numbers.
     * {-80, -13, -9, 21, 33}, {-68, -53, -25, 0, 17, 23, 39, 46, 95}
     * => {-80, -68, -53, -25, -13, -9, 0, 17, 21, 23, 33, 39, 46, 95}
     */
    @Test
    public void whenMergeSortTwoArraysOfDifferentLengthContainingNegativeNumbersThenSortedArrayOfIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{-80, -13, -9, 21, 33}, new int[]{-68, -53, -25, 0, 17, 23, 39, 46, 95});
        Assert.assertThat(result, is(new int[]{-80, -68, -53, -25, -13, -9, 0, 17, 21, 23, 33, 39, 46, 95}));
    }

    /**
     * Merge sort of two integer arrays when one is empty.
     * {1, 2, 3}, {} => {1, 2, 3}
     */
    @Test
    public void whenMergeSortTwoArraysAndSecondIsEmptyThenFirstArrayOfIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{1, 2, 3}, new int[]{});
        Assert.assertThat(result, is(new int[]{1, 2, 3}));
    }

    /**
     * Merge sort of two empty integer arrays.
     * {}, {} => {}
     */
    @Test
    public void whenMergeSortTwoEmptyIntegerArraysThenEmptyArrayOfIntegersReturned() {
        int[] result = this.mergeSort.merge(new int[]{}, new int[]{});
        Assert.assertThat(result, is(new int[]{}));
    }

    /**
     * Merge sort of two random length random integer arrays with negative and positive numbers.
     * {-100...100}, {-100...100} => {-100...100}
     */
    @Test
    public void whenMergeSortTwoRandomArraysOfDifferentLengthWithNegativeAndPositiveNumbersThenSortedArrayOfIntegersReturned() {
        Random random = new Random();
        int leftLength = random.nextInt(100);
        int[] left = random.ints(leftLength, -100, 101).toArray();
        Arrays.sort(left);
        int rightLength = random.nextInt(100);
        int[] right = random.ints(rightLength, -100, 101).toArray();
        Arrays.sort(right);
        int[] check = new int[leftLength + rightLength];
        System.arraycopy(left, 0, check, 0, leftLength);
        System.arraycopy(right, 0, check, leftLength, rightLength);
        Arrays.sort(check);
        int[] result = this.mergeSort.merge(left, right);
        Assert.assertThat(result, is(check));
    }
}

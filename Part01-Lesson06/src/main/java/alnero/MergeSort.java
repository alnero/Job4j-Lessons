package alnero;

/**
 * Class for merging of two pre-sorted integer arrays.
 */
public class MergeSort {
    /**
     * Method for merging of two pre-sorted integer arrays.
     * 1. Add integers from arrays to the result array comparing them one by one.
     * 2. If any integers remain in left array copy them to the result array.
     * 3. If any integers remain in right array copy them to the result array.
     * @param left first array
     * @param right second array
     * @return resulting merge sorted array
     */
    public int[] merge(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int[] result = new int[leftLength + rightLength];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        if (i < leftLength) {
            System.arraycopy(left, i, result, k, leftLength - i);
        }
        if (j < rightLength) {
            System.arraycopy(right, j, result, k, rightLength - j);
        }

        return result;
    }
}

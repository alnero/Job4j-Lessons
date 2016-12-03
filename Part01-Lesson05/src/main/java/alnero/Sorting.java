package alnero;

/**
 * Class for sorting arrays using bubble sort.
 */
public class Sorting {

    /**
     * Method for sorting arrays in place using bubble sort algorithm.
     * @param values array of int values to be sorted
     * @return the supplied array but sorted ascending
     */
    public int[] bubbleSort(int[] values) {
        int valuesLength = values.length;

        for (int i = 0; i < valuesLength - 1; i++) {
            for (int j = 0; j < valuesLength - i - 1; j++) {
                if (values[j] > values[j + 1]) {
                    int temp = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = temp;
                }
            }
        }

        return values;
    }
}

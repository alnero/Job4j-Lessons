package alnero;

import java.util.Arrays;

/**
 * Class to remove duplicates from String arrays.
 */
public class Duplicates {
    /**
     * Method to remove duplicates from String array. First array is sorted, then all non duplicate values are moved in the
     * beginning of array and copied in the new array of proper length, which is returned. Order of elements is not preserved.
     * Example:
     * [a, a, a, d, d, c, b, b, b] -> [a, a, a, b, b, b, c, d, d] -> [a, b, c, d, b, b, c, d, d] -> [a, b, c, d]
     * @param strings array of Strings with duplicates
     * @return new array containing no duplicates comparing to supplied one
     */
    public String[] removeDuplicates(String[] strings) {
        Arrays.sort(strings);

        int j = 0;
        for (int i = 0; i < strings.length - 1; i++) {
            if (!strings[i].equals(strings[i + 1])) {
                strings[j] = strings[i];
                j++;
            }
            if (i + 1 == strings.length - 1) {
                strings[j] = strings[i + 1];
                j++;
            }
        }

        if (strings.length == 1) {
            j++;
        }

        String[] noDuplicatesArray = new String[j];
        System.arraycopy(strings, 0, noDuplicatesArray, 0, j);

        return noDuplicatesArray;
    }
}

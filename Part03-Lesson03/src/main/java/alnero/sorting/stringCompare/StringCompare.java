package alnero.sorting.stringCompare;

import java.util.Comparator;

/**
 * Comparing two strings in lexicographical order char by char.
 */
public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        char[] leftChars = left.toCharArray();
        char[] rightChars = right.toCharArray();
        int maxNumOfChars = Math.max(leftChars.length, rightChars.length);
        int result = 0;
        for (int i = 0; i < maxNumOfChars; i++) {
            if (i >= leftChars.length) {
                result = -1;
                break;
            }
            if (i >= rightChars.length) {
                result = 1;
                break;
            }
            result = Character.compare(leftChars[i], rightChars[i]);
            if (result != 0) {
                break;
            }
        }
        return result;
    }
}

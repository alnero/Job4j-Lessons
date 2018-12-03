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
        int minNumOfChars = Math.min(leftChars.length, rightChars.length);
        int result;
        for (int i = 0; i < minNumOfChars; i++) {
            result = Character.compare(leftChars[i], rightChars[i]);
            if (result != 0) {
                return result;
            }
        }
        return new Integer(left.length()).compareTo(right.length());
    }
}

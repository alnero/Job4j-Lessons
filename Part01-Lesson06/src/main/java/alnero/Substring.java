package alnero;

/**
 * Class helping to find out is the string part of another string.
 */
public class Substring {
    /**
     * Method to define is string a part of another string.
     * Method checks that first char of substring is contained in string.
     * If substring is longer than part of string from first matching char, than it is not part of the string e.g. "abbb" does not contain "bbbb".
     * New string is generated from original string chars array and compared to substring.
     * @param origin String in which method searches for the matching substring
     * @param sub Substring which is searched
     * @return If string contains substring - true, if not - false
     */
    public boolean contains(String origin, String sub) {
        int originLength = origin.length();
        int subLength = sub.length();

        char[] originChars = origin.toCharArray();
        char subFirstChar = sub.charAt(0);

        for (int i = 0; i < originLength; i++) {
            if (originChars[i] == subFirstChar) {
                if (originLength - i < subLength) {
                    return false;
                }
                if (sub.equals(new String(originChars, i, subLength))) {
                    return true;
                }
            }
        }

        return false;
    }
}
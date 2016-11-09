package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Testing removing of duplicates from String array
 */
public class DuplicatesTest {
    private Duplicates duplicatesHandler;

    @Before
    public void createCommonHandlerForRemovingDuplicatesFromStringArray(){
        duplicatesHandler = new Duplicates();
    }

    /**
     * Test on array containing the same string two times.
     */
    @Test
    public void whenRemoveDuplicateFromTwoStringsArrayThenReturnArrayWithOneString() {
        String[] arrOfString = new String[]{"a", "a"};
        String[] noDuplicatesArray = new String[]{"a"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfString);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on array containing the same string three times
     */
    @Test
    public void whenRemoveDuplicateFromThreeStringsArrayThenReturnArrayWithOneString() {
        String[] arrOfString = new String[]{"a", "a", "a"};
        String[] noDuplicatesArray = new String[]{"a"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfString);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on array containing the same string seven times
     */
    @Test
    public void whenRemoveDuplicateFromSevenStringsArrayThenReturnArrayWithOneString() {
        String[] arrOfString = new String[]{"a", "a", "a", "a", "a", "a", "a"};
        String[] noDuplicatesArray = new String[]{"a"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfString);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on array containing two different strings with duplicates.
     */
    @Test
    public void whenRemoveDuplicatesFromArrayContainingDuplicatesOfTwoDifferentStringsThenReturnArrayWithTwoStrings() {
        String[] arrOfString = new String[]{"a", "a", "a", "z", "z", "z"};
        String[] noDuplicatesArray = new String[]{"a", "z"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfString);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on array containing five different strings with duplicates.
     */
    @Test
    public void whenRemoveDuplicatesFromArrayContainingDuplicatesOfFiveDifferentStringsThenReturnArrayWithFiveStrings() {
        String[] arrOfString = new String[]{"a", "a", "a", "b", "b", "c", "d", "d", "d", "d", "e", "e"};
        String[] noDuplicatesArray = new String[]{"a", "b", "c", "d", "e"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfString);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on array containing six different strings with duplicates in random (not alphabetical) order.
     */
    @Test
    public void whenRemoveDuplicatesFromArrayContainingDuplicatesOfSixStringsInRandomOrderThenReturnArrayWithSixStrings() {
        String[] arrOfString = new String[]{"f", "a", "f", "a", "c", "d", "e", "e", "e", "c", "e", "b"};
        String[] noDuplicatesArray = new String[]{"a", "b", "c", "d", "e", "f"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfString);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on array containing duplicate words.
     */
    @Test
    public void whenRemoveDuplicatesFromArrayOfWordsThenReturnArrayWithNoDuplicateWords() {
        String[] arrOfWords = new String[]{"sun", "sun", "sun", "rain", "snow", "rain", "snow", "clouds", "clouds"};
        String[] noDuplicatesArray = new String[]{"clouds", "rain", "snow", "sun"};

        String[] resultArray = duplicatesHandler.removeDuplicates(arrOfWords);
        Assert.assertThat(resultArray, is(noDuplicatesArray));
    }

    /**
     * Test on empty array.
     */
    @Test
    public void whenRemoveDuplicatesFromEmptyArrayThenReturnEmptyArray() {
        String[] emptyArrOfString = new String[]{};

        String[] resultArray = duplicatesHandler.removeDuplicates(emptyArrOfString);
        Assert.assertThat(resultArray, is(new String[]{}));
    }
}
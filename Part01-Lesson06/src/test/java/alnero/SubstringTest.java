package alnero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.hamcrest.core.Is.is;

/**
 * Testing methods of the class Substring.
 */
public class SubstringTest {
    /**
     * Common Substring object for testing.
     */
    private Substring substringFinder;

    /**
     * Creating common Substring object for testing.
     */
    @Before
    public void createCommonSubstringFinderObjectForTests() {
        substringFinder = new Substring();
    }

    /**
     * Test that when two chars string contains one char string then result is true.
     */
    @Test
    public void whenTwoCharsStringContainsOneCharStringThenResultIsTrue() {
        boolean result = this.substringFinder.contains("ab", "b");
        Assert.assertThat(result, is(true));
    }

    /**
     * Test that when two chars string does not contain one char string then result is false.
     */
    @Test
    public void whenTwoCharsStringDoesNotContainOneCharStringThenResultIsFalse() {
        boolean result = this.substringFinder.contains("aa", "b");
        Assert.assertThat(result, is(false));
    }

    /**
     * Test that when several chars string contains several chars string then result is true.
     */
    @Test
    public void whenSeveralCharsStringContainsSeveralCharsStringThenResultIsTrue() {
        boolean result = this.substringFinder.contains("ababbb", "abab");
        Assert.assertThat(result, is(true));
    }

    /**
     * Test that when searched string is longer then the part of string then result is false.
     */
    @Test
    public void whenSearchedStringIsLongerThanActualSubstringThenResultIsFalse() {
        boolean result = this.substringFinder.contains("aaabbb", "bbbb");
        Assert.assertThat(result, is(false));
    }

    /**
     * Test that when searched string is shorter then the part of string then result is true.
     */
    @Test
    public void whenSearchedStringIsShorterThanActualSubstringThenResultIsTrue() {
        boolean result = this.substringFinder.contains("aaabbb", "bb");
        Assert.assertThat(result, is(true));
    }

    /**
     * Test that when searched string is longer then original string then result is false.
     */
    @Test
    public void whenSearchedSubstringIsLongerThanStringThenResultIsFalse() {
        boolean result = this.substringFinder.contains("aabb", "bbbbbb");
        Assert.assertThat(result, is(false));
    }

    /**
     * Test that generated UUID string contains its random part.
     * Test inspired by http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
     */
    @Test
    public void whenSearchForRandomPartOfUUIDStringThenResultIsAlwaysTrue() {
        String uuid = UUID.randomUUID().toString(); // e.g. a989bcef-e040-4fd3-9b9f-bebb0ed83075
        String randomUUIDPart = uuid.split("-")[new Random().nextInt(5)];

        boolean result = this.substringFinder.contains(uuid, randomUUIDPart);
        Assert.assertThat(result, is(true));
    }
}
package alnero.sorting.stringCompare;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test comparison of strings char by char.
 */
public class StringCompareTest {
    /**
     * Equal strings -> zero.
     */
    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    /**
     * Left string with number smaller -> negative.
     */
    @Test
    public void whenLeftStringWithNumberSmallerThenResultShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov1",
                "Ivanov2"
        );
        assertThat(rst, lessThan(0));
    }

    /**
     * Left string shorter -> negative.
     */
    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }

    /**
     * Left string longer -> positive.
     */
    @Test
    public void whenLeftLongerThanRightResultShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanova",
                "Ivanov"
        );
        assertThat(rst, greaterThan(0));
    }

    /**
     * Left string greater -> positive.
     */
    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    /**
     * Left string second char greater -> positive.
     */
    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    /**
     * Left string second char smaller -> negative.
     */
    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}

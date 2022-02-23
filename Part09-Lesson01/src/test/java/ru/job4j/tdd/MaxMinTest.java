package ru.job4j.tdd;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MaxMinTest {
    @Test
    public void whenFindMaxUsingComparatorFromListThenShouldReturnMaxElem() {
        MaxMin maxFinder = new MaxMin();
        Integer expected = maxFinder.max(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                Comparator.naturalOrder());
        assertThat(expected, is(10));
    }

    @Test
    public void whenFindMinxUsingComparatorFromListThenShouldReturnMinElem() {
        MaxMin minFinder = new MaxMin();
        Integer expected = minFinder.min(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                Comparator.naturalOrder());
        assertThat(expected, is(1));
    }

    @Test
    public void whenListHaveNullsThenShouldReturnMinAndMaxElem() {
        MaxMin finder = new MaxMin();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(null);
        integerList.add(1);
        integerList.add(null);
        integerList.add(10);
        integerList.add(null);
        Integer minExpected = finder.min(integerList, Comparator.naturalOrder());
        Integer maxExpected = finder.max(integerList, Comparator.naturalOrder());
        assertThat(minExpected, is(1));
        assertThat(maxExpected, is(10));
    }
}

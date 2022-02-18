package ru.job4j.tdd;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findByComparatorPredicate(value, (a, b) -> Comparator.nullsFirst(comparator).compare(a, b) > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findByComparatorPredicate(value, (a, b) -> Comparator.nullsFirst(comparator).compare(a, b) < 0);
    }

    private <T> T findByComparatorPredicate(List<T> value, BiPredicate<T, T> predicate) {
        T searchElement = null;
        for (T elem : value) {
            if (searchElement == null || predicate.test(elem, searchElement)) {
                searchElement = elem;
            }
        }
        return searchElement;
    }
}


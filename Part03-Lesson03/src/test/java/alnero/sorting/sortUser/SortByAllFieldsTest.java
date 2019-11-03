package alnero.sorting.sortUser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Sorting users according to name and age.
 */
public class SortByAllFieldsTest {
    /**
     * Different name and different age -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersWithDifNameAndAgeThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Anton", 5),
                new User("Fred", 8),
                new User("Daria", 6),
                new User("Boris", 7)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortByAllFields(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Anton", 5),
                new User("Boris", 7),
                new User("Daria", 6),
                new User("Fred", 8)
        ));
        assertThat(resultList, is(expected));
    }

    /**
     * Different name and same age -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersWithDifNameAndEqualAgeThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Anton", 5),
                new User("Fred", 5),
                new User("Daria", 5),
                new User("Boris", 5)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortByAllFields(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Anton", 5),
                new User("Boris", 5),
                new User("Daria", 5),
                new User("Fred", 5)
        ));
        assertThat(resultList, is(expected));
    }

    /**
     * Same name and different age -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersWithEqualNameAndDifAgeThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Anna", 5),
                new User("Anna", 7),
                new User("Anna", 6),
                new User("Anna", 4)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortByAllFields(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Anna", 4),
                new User("Anna", 5),
                new User("Anna", 6),
                new User("Anna", 7)
        ));
        assertThat(resultList, is(expected));
    }

    /**
     * Two pairs of equal name and different age -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersWithPairsOfEqualNameAndDifAgeThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Fred", 15),
                new User("Anna", 5),
                new User("Fred", 10),
                new User("Anna", 8)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortByAllFields(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Anna", 5),
                new User("Anna", 8),
                new User("Fred", 10),
                new User("Fred", 15)
        ));
        assertThat(resultList, is(expected));
    }

    /**
     * Different name and two pairs of equal age -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersWithPairsOfEqualAgeAndDifNameThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Fred", 10),
                new User("Anna", 5),
                new User("Eva", 10),
                new User("Greg", 5)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortByAllFields(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Anna", 5),
                new User("Eva", 10),
                new User("Fred", 10),
                new User("Greg", 5)
        ));
        assertThat(resultList, is(expected));
    }
}

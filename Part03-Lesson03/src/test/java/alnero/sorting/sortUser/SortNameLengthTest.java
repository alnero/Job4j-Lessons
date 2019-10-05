package alnero.sorting.sortUser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test sorting of users according to the name length.
 */
public class SortNameLengthTest {
    /**
     * Three different name lengths -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersWithDifLengthNamesThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Anton", 5),
                new User("Alexander", 9),
                new User("Anna", 4)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortNameLength(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Anna", 4),
                new User("Anton", 5),
                new User("Alexander", 9)
        ));
        assertThat(resultList, is(expected));
    }

    /**
     * Three names, two name lengths are same -> sorted list of users.
     */
    @Test
    public void whenSortListOfUsersAndTwoNamesAreEqualThenListWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Alex", 4),
                new User("Anton", 5),
                new User("Anna", 4)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortNameLength(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Alex", 4),
                new User("Anna", 4),
                new User("Anton", 5)
        ));
        assertThat(resultList, is(expected));
    }

    /**
     * Three names have same name lengths -> same list of users.
     */
    @Test
    public void whenSortListOfUsersAndThreeNamesAreEqualThenSameListOfUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(List.of(
                new User("Alex", 4),
                new User("Dana", 4),
                new User("Anna", 4)
        ));
        SortUser sorter = new SortUser();
        List<User> resultList = sorter.sortNameLength(inputList);
        List<User> expected = new ArrayList<>();
        expected.addAll(List.of(
                new User("Alex", 4),
                new User("Dana", 4),
                new User("Anna", 4)
        ));
        assertThat(resultList, is(expected));
    }
}

package alnero.sorting.sortUser;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

/**
 * Test sorting of users.
 */
public class SortUserTest {
    /**
     * Random order of users in list -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListInRandomOrderThenSetWithSortedUsersReturned() {
        List<User> inputList = List.of(
           new User("Betta", 2),
           new User("Alpha", 1),
           new User("Gamma", 3)
        );
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        Set<User> expected = Set.of(
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        );
        assertThat(resultSet, is(expected));
    }

    /**
     * Ascending order of users in list -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListInAscendingOrderThenSetWithSortedUsersReturned() {
        List<User> inputList = List.of(
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        );
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        Set<User> expected = Set.of(
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        );
        assertThat(resultSet, is(expected));
    }

    /**
     * Descending order of users in list -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListInDescendingOrderThenSetWithSortedUsersReturned() {
        List<User> inputList = List.of(
                new User("Gamma", 3),
                new User("Betta", 2),
                new User("Alpha", 1)
        );
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        Set<User> expected = Set.of(
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        );
        assertThat(resultSet, is(expected));
    }

    /**
     * Two of three users in list have same age -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListButTwoHaveSameAgeThenSetWithSortedUsersReturned() {
        List<User> inputList = List.of(
                new User("Gamma", 3),
                new User("Betta1", 2),
                new User("Betta2", 2)
        );
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        Set<User> expected = Set.of(
                new User("Betta1", 2),
                new User("Betta2", 2),
                new User("Gamma", 3)
        );
        assertThat(resultSet, is(expected));
    }
}

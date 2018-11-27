package alnero.sorting.sortUser;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<User> inputList = new ArrayList<>();
        inputList.addAll(Arrays.asList(
           new User("Betta", 2),
           new User("Alpha", 1),
           new User("Gamma", 3)
        ));
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        User[] expected = new User[] {
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        };
        assertThat(resultSet.toArray(), is(expected));
    }

    /**
     * Ascending order of users in list -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListInAscendingOrderThenSetWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(Arrays.asList(
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        ));
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        User[] expected = new User[] {
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        };
        assertThat(resultSet.toArray(), is(expected));
    }

    /**
     * Descending order of users in list -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListInDescendingOrderThenSetWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(Arrays.asList(
                new User("Gamma", 3),
                new User("Betta", 2),
                new User("Alpha", 1)
        ));
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        User[] expected = new User[] {
                new User("Alpha", 1),
                new User("Betta", 2),
                new User("Gamma", 3)
        };
        assertThat(resultSet.toArray(), is(expected));
    }

    /**
     * Two of three users in list have same age -> ascending order of users in set.
     */
    @Test
    public void whenThreeUsersInListButTwoHaveSameAgeThenSetWithSortedUsersReturned() {
        List<User> inputList = new ArrayList<>();
        inputList.addAll(Arrays.asList(
                new User("Gamma", 3),
                new User("Betta1", 2),
                new User("Betta2", 2)
        ));
        SortUser sorter = new SortUser();
        Set<User> resultSet = sorter.sort(inputList);
        User[] expected = new User[] {
                new User("Betta1", 2),
                new User("Betta2", 2),
                new User("Gamma", 3)
        };
        assertThat(resultSet.toArray(), is(expected));
    }
}

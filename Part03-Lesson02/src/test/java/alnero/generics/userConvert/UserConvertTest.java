package alnero.generics.userConvert;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

/**
 * Test conversion list of users to map of users.
 */
public class UserConvertTest {
    /**
     * Reset user IDs before every test.
     */
    @Before
    public void resetUserIDs() {
        User.resetIdCounter();
    }

    /**
     * One user in list -> one user in map.
     */
    @Test
    public void whenConvertListOfOneUserToMapThenCorrectMapReturned() {
        List<User> input = List.of(new User("name1", "city1"));
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> result = userConvert.process(input);
        assertThat(result.get(1).getName(), is("name1"));
    }

    /**
     * Three different users in list -> three users in map.
     */
    @Test
    public void whenConvertListOfThreeDifferentUserToMapThenCorrectMapReturned() {
        List<User> input = List.of(
           new User("name1", "city1"),
           new User("name2", "city2"),
           new User("name3", "city3")
        );
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> result = userConvert.process(input);
        assertThat(result.get(2).getName(), is("name2"));
    }

    /**
     * Two same users in list -> one user in map.
     */
    @Test
    public void whenConvertListOfTwoSameUsersToMapThenMapWithOneUserReturned() {
        User sameUser1 = new User("name1", "city1");
        User.resetIdCounter();
        User sameUser2 = new User("name1", "city1");
        List<User> input = List.of(sameUser1, sameUser2);
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> result = userConvert.process(input);
        assertThat(result.size(), is(1));
    }
}

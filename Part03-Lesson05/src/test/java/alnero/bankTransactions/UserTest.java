package alnero.bankTransactions;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test user equality and hash codes in bank transactions.
 */
public class UserTest {
    /**
     * Testing user equality.
     */
    @Test
    public void whenTwoUsersWithSameNameAndPassportThenTheyAreEqual() {
        User userA = new User("Alan", "1234567890");
        User userB = new User("Alan", "1234567890");
        assertThat(userA.equals(userB), is(true));
    }

    /**
     * When users are equal their hash codes equal.
     */
    @Test
    public void whenTwoUsersAreEqualThenTheyHaveEqualHashCode() {
        User userA = new User("Alan", "1234567890");
        User userB = new User("Alan", "1234567890");
        assertThat(userA.hashCode() == userB.hashCode(), is(true));
    }

    /**
     * Testing inequality of users.
     */
    @Test
    public void whenTwoUsersHaveDifferentNamesThenTheyAreNotEqual() {
        User userA = new User("Alan", "1234567890");
        User userB = new User("Bob", "1234567890");
        assertThat(userA.equals(userB), is(false));
    }

    /**
     * When users are not equal their hash codes are not equal.
     */
    @Test
    public void whenTwoUsersAreNotEqualThenTheyHaveDifferentHashCodes() {
        User userA = new User("Alan", "1234567890");
        User userB = new User("Bob", "1234567890");
        assertThat(userA.hashCode() == userB.hashCode(), is(false));
    }
}

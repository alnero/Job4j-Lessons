package alnero.genericStore;

import alnero.genericStore.model.User;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Test of user store methods.
 */
public class UserStoreTest {
    /**
     * Common user store for testing.
     */
    private UserStore userStore;

    /**
     * Initialize common user storage.
     */
    @Before
    public void setUp() {
        this.userStore = new UserStore(5);
    }

    /**
     * Test that when save user the same user returned from storage.
     */
    @Test
    public void whenAddUserToStoreThenTheSameUserReturned() {
        User testUser = new User("test_id");
        this.userStore.add(testUser);
        User resultUser = this.userStore.findById("test_id");
        assertThat(resultUser, is(testUser));
    }

    /**
     * Null can't be added to storage.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenAddNullToStoreThenExceptionThrown() {
        this.userStore.add(null);
    }

    /**
     * Impossible to add more users than storage capacity.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddMoreThanStorageCapacityThenExceptionThrown() {
        User testUser1 = new User("test_id1");
        User testUser2 = new User("test_id2");
        User testUser3 = new User("test_id3");
        User testUser4 = new User("test_id4");
        User testUser5 = new User("test_id5");
        this.userStore.add(testUser1);
        this.userStore.add(testUser2);
        this.userStore.add(testUser3);
        this.userStore.add(testUser4);
        this.userStore.add(testUser5);

        User testUser6 = new User("test_id6");
        this.userStore.add(testUser6);
    }

    /**
     * Test that replacement of user in store works correct.
     */
    @Test
    public void whenReplaceUserThenCorrectUserReturned() {
        User testUser = new User("test_id");
        User replaceUser = new User("replace_id");
        this.userStore.add(testUser);
        boolean operationResult = this.userStore.replace("test_id", replaceUser);
        User resultUser = this.userStore.findById("replace_id");
        assertThat(operationResult, is(true));
        assertThat(resultUser, is(replaceUser));
        assertThat(resultUser.getId(), is("replace_id"));
    }

    /**
     * When replace user in empty store then result of operation is false.
     */
    @Test
    public void whenReplaceUserInEmptyStoreThenFalseReturned() {
        User replaceUser = new User("test_id");
        boolean operationResult = this.userStore.replace("test_id", replaceUser);
        assertThat(operationResult, is(false));
    }

    /**
     * When replace wrong user in full storage then result of operation is false.
     */
    @Test
    public void whenReplaceWrongUserInFullStoreThenFalseReturned() {
        User testUser1 = new User("test_id1");
        User testUser2 = new User("test_id2");
        User testUser3 = new User("test_id3");
        User testUser4 = new User("test_id4");
        User testUser5 = new User("test_id5");
        this.userStore.add(testUser1);
        this.userStore.add(testUser2);
        this.userStore.add(testUser3);
        this.userStore.add(testUser4);
        this.userStore.add(testUser5);
        User wrongUser = new User("wrong_id");
        boolean operationResult = this.userStore.replace("wrong_id", wrongUser);
        assertThat(operationResult, is(false));
    }

    /**
     * Using of null as user id does not affect replace operation.
     */
    @Test
    public void whenUseNullAsIdToReplaceUserThenFalseIsReturned() {
        User testUser = new User("test_id");
        User replaceUser = new User("replace_id");
        this.userStore.add(testUser);
        boolean operationResult = this.userStore.replace(null, replaceUser);
        assertThat(operationResult, is(false));
    }

    /**
     * User in store can't be replaced by null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenUseNullAsUserToReplaceUserThenExceptionIsThrown() {
        User testUser = new User("test_id");
        this.userStore.add(testUser);
        this.userStore.replace("test_id", null);
    }

    /**
     * Both null arguments can't be used in replace method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenUseNullAsIdAndAsUserToReplaceUserThenExceptionIsThrown() {
        User testUser = new User("test_id");
        this.userStore.add(testUser);
        this.userStore.replace(null, null);
    }

    /**
     * Test that delete operation works correct.
     */
    @Test
    public void whenDeleteUserFromStoreThenNoSuchUserFound() {
        User testUser = new User("test_id");
        this.userStore.add(testUser);
        boolean operationResult = this.userStore.delete("test_id");
        User resultUser = this.userStore.findById("test_id");
        assertThat(operationResult, is(true));
        assertNull(resultUser);
    }

    /**
     * When delete from empty storage then result of operation is false.
     */
    @Test
    public void whenDeleteUserFromEmptyStoreThenFalseReturned() {
        boolean operationResult = this.userStore.delete("test_id");
        assertThat(operationResult, is(false));
    }

    /**
     * Using of null as user id does not affect delete operation.
     */
    @Test
    public void whenDeleteUsingNullAsIdThenFalseIsReturned() {
        boolean operationResult = this.userStore.delete(null);
        assertThat(operationResult, is(false));
    }

    /**
     * Deleted user can't be found.
     */
    @Test
    public void whenTryToFindDeletedUserThenNullReturned() {
        User testUser1 = new User("test_id1");
        User testUser2 = new User("test_id2");
        this.userStore.add(testUser1);
        this.userStore.add(testUser2);
        this.userStore.delete("test_id1");
        User resultUser = this.userStore.findById("test_id1");
        assertNull(resultUser);
    }

    /**
     * User in empty store can't be found.
     */
    @Test
    public void whenTryToFindFromEmptyStoreThenNullReturned() {
        User resultUser = this.userStore.findById("test_id");
        assertNull(resultUser);
    }

    /**
     * Using of null as user id does not affect find operation.
     */
    @Test
    public void whenTryToFindUsingNullAsIdThenNullReturned() {
        User resultUser = this.userStore.findById(null);
        assertNull(resultUser);
    }
}

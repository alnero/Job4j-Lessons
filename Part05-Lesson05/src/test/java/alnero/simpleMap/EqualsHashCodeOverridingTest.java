package alnero.simpleMap;

import java.util.HashMap;
import java.util.Calendar;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *  Test HashMap when overriding / not overriding equals() and hashCode().
 */
public class EqualsHashCodeOverridingTest {
    /**
     * If equals() and hashCode() are not overridden then class Object default methods are used.
     * According to default implementation User objects are not equal and have different hash codes.
     * HashMap to store and find entities first of all calculates hash codes of the keys
     * to find equal elements and to assign them places in internal storage.
     * When hash codes are different HashMap automatically treats entities as not equal
     * thus we have two "logically equal" entities stored in HashMap.
     */
    @Test
    public void whenEqualsAndHashCodeNotOverriddenThenTwoLogicallyEqualUsersAreStoredInMap() {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(2001, 1, 1);
        UserEqualsAndHashCodeNotOverridden cloneOne = new UserEqualsAndHashCodeNotOverridden("Clone", 1, dateOfBirth);
        UserEqualsAndHashCodeNotOverridden cloneTwo = new UserEqualsAndHashCodeNotOverridden("Clone", 1, dateOfBirth);
        HashMap<UserEqualsAndHashCodeNotOverridden, Object> storageMap = new HashMap<>();
        storageMap.put(cloneOne, new Object());
        storageMap.put(cloneTwo, new Object());

        System.out.println(storageMap);
        assertThat(storageMap.size(), is(2));
    }

    /**
     * If only hashCode() is overridden then class Object default method equals() is used.
     * According to default implementation of equals() User objects are not equal but have the same hash codes.
     * HashMap to store and find entities first of all calculates hash codes of the keys
     * to find equal elements and to assign them places in internal storage.
     * When hash codes are the same HashMap puts entities in one storage chain but after that
     * to check the equality of entities in that chain HashMap call equals() method and if it returns false
     * HashMap treats entities as not equal thus we have two "logically equal" entities stored in HashMap.
     */
    @Test
    public void whenOnlyHashCodeOverriddenThenTwoLogicallyEqualUsersAreStoredInMap() {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(2001, 1, 1);
        UserOnlyHashCodeOverridden cloneOne = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        UserOnlyHashCodeOverridden cloneTwo = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        HashMap<UserOnlyHashCodeOverridden, Object> storageMap = new HashMap<>();
        storageMap.put(cloneOne, new Object());
        storageMap.put(cloneTwo, new Object());

        System.out.println(storageMap);
        assertThat(storageMap.size(), is(2));
    }
}

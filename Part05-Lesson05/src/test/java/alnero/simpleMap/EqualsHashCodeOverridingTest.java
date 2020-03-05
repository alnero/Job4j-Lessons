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
}

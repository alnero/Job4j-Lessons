package alnero.simpleMap;

import alnero.simpleMap.user.UserOnlyHashCodeOverridden;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Testing simple hash map.
 */
public class SimpleHashMapTest {
    /** Common simple hash map object for testing. */
    private SimpleHashMap<Integer, String> hashMap;

    /**
     * Initialize common simple hash map object before every test.
     */
    @Before
    public void beforeTest() {
        this.hashMap = new SimpleHashMap<>();
    }

    /**
     * Newly created, empty simple hash map has a zero size.
     */
    @Test
    public void whenCreateNewSimpleHashMapThenSizeIsZero() {
        int size = hashMap.getSize();
        assertThat(size, is(0));
    }

    /**
     * Newly created, empty simple hash map returns null when trying to get values.
     */
    @Test
    public void whenCreateNewSimpleHashMapThenGetReturnsNull() {
        String value = hashMap.get(1);
        assertNull(value);
    }

    /**
     * Newly created, empty simple hash map returns false when trying to delete entry.
     */
    @Test
    public void whenCreateNewSimpleHashMapThenDeleteReturnsFalse() {
        boolean deleted = hashMap.delete(1);
        int size = hashMap.getSize();
        assertThat(size, is(0));
        assertThat(deleted, is(false));
    }

    /**
     * Inserting one entry changes size to one and correct value returned when trying to get its value.
     */
    @Test
    public void whenInsertOneEntryThenSizeIsOneAndCorrectValueReturned() {
        boolean insert1 = hashMap.insert(1, "one");
        int size = hashMap.getSize();
        String value = hashMap.get(1);
        assertThat(insert1, is(true));
        assertThat(size, is(1));
        assertThat(value, is("one"));
    }

    /**
     * Inserting two entries with equal keys changes size to one and value inserted last returned.
     */
    @Test
    public void whenInsertTwoEntriesWithEqualKeysThenSizeIsOneAndLatestEntrySaved() {
        boolean insert1 = hashMap.insert(1, "one");
        boolean insert2 = hashMap.insert(1, "oneClone");
        int size = hashMap.getSize();
        String value = hashMap.get(1);
        assertThat(insert1, is(true));
        assertThat(insert2, is(true));
        assertThat(size, is(1));
        assertThat(value, is("oneClone"));
    }

    /**
     * Inserting two entries with colliding but not equal keys saves only first entry.
     */
    @Test
    public void whenInsertTwoEntriesWithCollidingButNotEqualKeysThenSizeIsOneAndFirstEntrySaved() {
        SimpleHashMap<UserOnlyHashCodeOverridden, String> userStringHashMap =  new SimpleHashMap<>();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(2001, 1, 1);
        UserOnlyHashCodeOverridden cloneOne = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        UserOnlyHashCodeOverridden cloneTwo = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        boolean insert1 = userStringHashMap.insert(cloneOne, "cloneOne");
        boolean insert2 = userStringHashMap.insert(cloneTwo, "cloneTwo");
        int size = userStringHashMap.getSize();
        String value1 = userStringHashMap.get(cloneOne);
        String value2 = userStringHashMap.get(cloneTwo);
        assertThat(cloneOne.hashCode() == cloneTwo.hashCode(), is(true));
        assertThat(cloneOne.equals(cloneTwo), is(false));
        assertThat(insert1, is(true));
        assertThat(insert2, is(false));
        assertThat(size, is(1));
        assertThat(value1, is("cloneOne"));
        assertNull(value2);
    }

    /**
     * Deleting entry using colliding but not equal key returns false and entry not deleted.
     */
    @Test
    public void whenDeleteOneEntryWithCollidingButNotEqualKeyThenSizeIsOneAndEntryNotDeleted() {
        SimpleHashMap<UserOnlyHashCodeOverridden, String> userStringHashMap =  new SimpleHashMap<>();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(2001, 1, 1);
        UserOnlyHashCodeOverridden cloneOne = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        UserOnlyHashCodeOverridden cloneTwo = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        boolean insert = userStringHashMap.insert(cloneOne, "cloneOne");
        boolean delete = userStringHashMap.delete(cloneTwo);
        int size = userStringHashMap.getSize();
        String value = userStringHashMap.get(cloneOne);
        assertThat(cloneOne.hashCode() == cloneTwo.hashCode(), is(true));
        assertThat(cloneOne.equals(cloneTwo), is(false));
        assertThat(insert, is(true));
        assertThat(delete, is(false));
        assertThat(size, is(1));
        assertThat(value, is("cloneOne"));
    }

    /**
     * Getting entry using colliding but not equal key returns null.
     */
    @Test
    public void whenGetEntryWithCollidingButNotEqualKeyThenNullReturned() {
        SimpleHashMap<UserOnlyHashCodeOverridden, String> userStringHashMap =  new SimpleHashMap<>();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(2001, 1, 1);
        UserOnlyHashCodeOverridden cloneOne = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        UserOnlyHashCodeOverridden cloneTwo = new UserOnlyHashCodeOverridden("Clone", 1, dateOfBirth);
        boolean insert = userStringHashMap.insert(cloneOne, "cloneOne");
        int size = userStringHashMap.getSize();
        String value = userStringHashMap.get(cloneTwo);
        assertThat(cloneOne.hashCode() == cloneTwo.hashCode(), is(true));
        assertThat(cloneOne.equals(cloneTwo), is(false));
        assertThat(insert, is(true));
        assertThat(size, is(1));
        assertNull(value);
    }

    /**
     * Inserting ten entries increases internal capacity, changes size to ten and correct values are returned
     * when trying to get them.
     */
    @Test
    public void whenInsertTenEntriesThenInternalCapacityIncreaseSizeIsTenCorrectValuesReturned() {
        boolean insert1 = hashMap.insert(1, "one");
        boolean insert2 = hashMap.insert(2, "two");
        boolean insert3 = hashMap.insert(3, "three");
        boolean insert4 = hashMap.insert(4, "four");
        boolean insert5 = hashMap.insert(5, "five");
        boolean insert6 = hashMap.insert(6, "six");
        boolean insert7 = hashMap.insert(7, "seven");
        boolean insert8 = hashMap.insert(8, "eight");
        boolean insert9 = hashMap.insert(9, "nine");
        boolean insert10 = hashMap.insert(10, "ten");
        int size = hashMap.getSize();
        String value1 = hashMap.get(1);
        String value2 = hashMap.get(2);
        String value3 = hashMap.get(3);
        String value4 = hashMap.get(4);
        String value5 = hashMap.get(5);
        String value6 = hashMap.get(6);
        String value7 = hashMap.get(7);
        String value8 = hashMap.get(8);
        String value9 = hashMap.get(9);
        String value10 = hashMap.get(10);
        assertThat(insert1, is(true));
        assertThat(insert2, is(true));
        assertThat(insert3, is(true));
        assertThat(insert4, is(true));
        assertThat(insert5, is(true));
        assertThat(insert6, is(true));
        assertThat(insert7, is(true));
        assertThat(insert8, is(true));
        assertThat(insert9, is(true));
        assertThat(insert10, is(true));
        assertThat(size, is(10));
        assertThat(value1, is("one"));
        assertThat(value2, is("two"));
        assertThat(value3, is("three"));
        assertThat(value4, is("four"));
        assertThat(value5, is("five"));
        assertThat(value6, is("six"));
        assertThat(value7, is("seven"));
        assertThat(value8, is("eight"));
        assertThat(value9, is("nine"));
        assertThat(value10, is("ten"));
    }

    /**
     * Inserting four entries then deleting two of them properly affects size and deleted entries are not found
     * on further delete or get calls.
     */
    @Test
    public void whenInsertFourAndDeleteTwoEntriesThenSizeIsTwoCorrectValuesReturnedAndDeletedNotFound() {
        boolean insert1 = hashMap.insert(1, "one");
        boolean insert2 = hashMap.insert(2, "two");
        boolean insert3 = hashMap.insert(3, "three");
        boolean insert4 = hashMap.insert(4, "four");
        boolean delete2 = hashMap.delete(2);
        boolean delete3 = hashMap.delete(3);
        int size = hashMap.getSize();
        String value1 = hashMap.get(1);
        String value2 = hashMap.get(2);
        String value3 = hashMap.get(3);
        String value4 = hashMap.get(4);
        assertThat(insert1, is(true));
        assertThat(insert2, is(true));
        assertThat(insert3, is(true));
        assertThat(insert4, is(true));
        assertThat(delete2, is(true));
        assertThat(delete3, is(true));
        assertThat(size, is(2));
        assertThat(value1, is("one"));
        assertNull(value2);
        assertNull(value3);
        assertThat(value4, is("four"));
    }

    /**
     * Inserting and deleting two entries properly affects size and deleted entries are not found
     * on further delete or get calls.
     */
    @Test
    public void whenInsertAndDeleteThenRepeatedDeleteReturnsFalse() {
        boolean insert1 = hashMap.insert(1, "one");
        boolean insert2 = hashMap.insert(2, "two");
        boolean delete1 = hashMap.delete(1);
        boolean delete2 = hashMap.delete(2);
        boolean repeatDelete1 = hashMap.delete(1);
        boolean repeatDelete2 = hashMap.delete(2);
        int size = hashMap.getSize();
        String value1 = hashMap.get(1);
        String value2 = hashMap.get(2);
        assertThat(insert1, is(true));
        assertThat(insert2, is(true));
        assertThat(delete1, is(true));
        assertThat(delete2, is(true));
        assertThat(repeatDelete1, is(false));
        assertThat(repeatDelete2, is(false));
        assertThat(size, is(0));
        assertNull(value1);
        assertNull(value2);
    }

    /**
     * Inserting one entry using null as a key.
     */
    @Test
    public void whenInsertAndGetUsingNullAsKeyThenCorrectValueReturned() {
        boolean insert = hashMap.insert(null, "null");
        int size = hashMap.getSize();
        String value = hashMap.get(null);
        assertThat(insert, is(true));
        assertThat(size, is(1));
        assertThat(value, is("null"));
    }

    /**
     * Inserting three entries using null as a key properly affects size and last value returned.
     */
    @Test
    public void whenInsertThreeEntriesUsingNullAsKeyThenLatestValueReturnedAndInsertsReturnTrue() {
        boolean insert1 = hashMap.insert(null, "firstNull");
        boolean insert2 = hashMap.insert(null, "secondNull");
        boolean insert3 = hashMap.insert(null, "thirdNull");
        int size = hashMap.getSize();
        String value = hashMap.get(null);
        assertThat(insert1, is(true));
        assertThat(insert2, is(true));
        assertThat(insert3, is(true));
        assertThat(size, is(1));
        assertThat(value, is("thirdNull"));
    }

    /**
     * When inserting three entries with the same hash code only last one saved.
     */
    @Test
    public void whenInsertEntriesWithSameHashCodeThenOnlyOneSaved() {
        boolean insert1 = hashMap.insert(1, "firstOne");
        boolean insert2 = hashMap.insert(1, "secondOne");
        boolean insert3 = hashMap.insert(1, "thirdOne");
        int size = hashMap.getSize();
        String value = hashMap.get(1);
        assertThat(insert1, is(true));
        assertThat(insert2, is(true));
        assertThat(insert3, is(true));
        assertThat(size, is(1));
        assertThat(value, is("thirdOne"));
    }

    /**
     * Sequential insert and delete of equal entries with the same hash code saves only last entry.
     */
    @Test
    public void whenInsertAndDeleteEntriesWithSameHashCodeThenOnlyLastOneSaved() {
        boolean insert1 = hashMap.insert(1, "firstOne");
        boolean delete1 = hashMap.delete(1);
        boolean insert2 = hashMap.insert(1, "secondOne");
        boolean delete2 = hashMap.delete(1);
        boolean insert3 = hashMap.insert(1, "thirdOne");
        boolean delete3 = hashMap.delete(1);
        boolean insert4 = hashMap.insert(1, "fourthOne");
        int size = hashMap.getSize();
        String value = hashMap.get(1);
        assertThat(insert1, is(true));
        assertThat(delete1, is(true));
        assertThat(insert2, is(true));
        assertThat(delete2, is(true));
        assertThat(insert3, is(true));
        assertThat(delete3, is(true));
        assertThat(insert4, is(true));
        assertThat(size, is(1));
        assertThat(value, is("fourthOne"));
    }

    /**
     * Inserting and deleting one entry using null as a key properly affects size and no values returned.
     */
    @Test
    public void whenInsertAndDeleteUsingNullAsKeyThenSizeIsZeroAndCorrectValuesReturned() {
        boolean insert = hashMap.insert(null, "null");
        boolean delete = hashMap.delete(null);
        int size = hashMap.getSize();
        String value = hashMap.get(null);
        assertThat(insert, is(true));
        assertThat(delete, is(true));
        assertThat(size, is(0));
        assertNull(value);
    }

    /**
     * Iterator methods return correct values.
     */
    @Test
    public void whenGetIteratorThenItReturnsCorrectValues() {
        boolean insert1 = hashMap.insert(1, "one");
        boolean insert2 = hashMap.insert(2, "two");
        boolean insert3 = hashMap.insert(3, "three");
        boolean insert4 = hashMap.insert(4, "four");
        boolean insert5 = hashMap.insert(5, "five");
        Iterator<Entry<Integer, String>> it = hashMap.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(1, "one")));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(2, "two")));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(3, "three")));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(4, "four")));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(5, "five")));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * When no more elements iterator throws exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoMoreElementsThenIteratorThrowsException() {
        boolean insert1 = hashMap.insert(1, "one");
        boolean insert2 = hashMap.insert(2, "two");
        boolean insert3 = hashMap.insert(3, "three");
        Iterator<Entry<Integer, String>> it = hashMap.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(1, "one")));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(2, "two")));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(new Entry<>(3, "three")));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Iterator hasNext() throws exception when list is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenMapModifiedThenIteratorHasNextThrowsException() {
        boolean insert1 = hashMap.insert(1, "one");
        Iterator<Entry<Integer, String>> it = hashMap.iterator();
        assertThat(it.hasNext(), is(true));
        hashMap.insert(2, "two");
        it.hasNext();
    }

    /**
     * Iterator next() throws exception when list is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenMapModifiedThenIteratorNextThrowsException() {
        boolean insert1 = hashMap.insert(1, "one");
        Iterator<Entry<Integer, String>> it = hashMap.iterator();
        assertThat(it.hasNext(), is(true));
        hashMap.insert(2, "two");
        it.next();
    }
}

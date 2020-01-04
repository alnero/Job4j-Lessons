package alnero.simpleGenericArray;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test simple generic array with String class.
 */
public class SimpleGenericArrayStringTest {
    /**
     * Common storage for testing.
     */
    private SimpleGenericArray<String> stringStorage;

    /**
     * Initialize common storage.
     */
    @Before
    public void setUp() {
        this.stringStorage = new SimpleGenericArray<>(String.class, 10);
    }

    /**
     * Test that set generic class is returned.
     */
    @Test
    public void whenAddGenericStringShouldReturnClassString() {
        this.stringStorage.add("test string");
        assertThat(stringStorage.get(0).getClass().getName(), is("java.lang.String"));
    }

    /**
     * Test that added string is properly returned.
     */
    @Test
    public void whenAddStringShouldReturnAddedString() {
        this.stringStorage.add("test string");
        assertThat(stringStorage.get(0), is("test string"));
    }

    /**
     * When add to position more than size of storage exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddStringsMoreThanSizeOfStorageShouldThrowIndexOutOfBounds() {
        SimpleGenericArray<String> smallStringStorage = new SimpleGenericArray<>(String.class, 1);
        smallStringStorage.add("test string");
        smallStringStorage.add("boom string");
    }

    /**
     * Test that adding is done sequentially.
     */
    @Test
    public void whenAddThreeStringsShouldAddToFirstEmptyPosition() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        assertThat(this.stringStorage.get(0), is("test string 0"));
        assertThat(this.stringStorage.get(1), is("test string 1"));
        assertThat(this.stringStorage.get(2), is("test string 2"));
    }

    /**
     * When get from wrong position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOneStringAndGetFromPositionTwoShouldThrowIndexOutOfBounds() {
        this.stringStorage.add("test string");
        this.stringStorage.get(2);
    }

    /**
     * When get from negative position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOneStringAndGetFromNegativePositionShouldThrowIndexOutOfBounds() {
        this.stringStorage.add("test string");
        this.stringStorage.get(-1);
    }

    /**
     * When get from empty array exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromEmptyStorageShouldThrowIndexOutOfBounds() {
        this.stringStorage.get(0);
    }

    /**
     * Test that element in storage is re-placed correctly.
     */
    @Test
    public void whenSetStringShouldChangeStringInStorage() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.set(1, "test string AAA");
        assertThat(this.stringStorage.get(1), is("test string AAA"));
    }

    /**
     * When re-place in wrong position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetStringToWrongPositionShouldThrowIndexOurOfBounds() {
        this.stringStorage.add("test string 0");
        this.stringStorage.set(1, "test string AAA");
    }

    /**
     * When re-place in negative position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetStringToNegativePositionShouldThrowIndexOurOfBounds() {
        this.stringStorage.add("test string 0");
        this.stringStorage.set(-1, "test string AAA");
    }

    /**
     * When re-place in empty storage exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetToEmptyStorageShouldThrowIndexOurOfBounds() {
        this.stringStorage.set(0, "test string AAA");
    }

    /**
     * Test that when remove one string two strings left.
     */
    @Test
    public void whenRemoveOneStringShouldReduceNumberOfStringsLeftInStorage() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.remove(1);
        assertThat(this.stringStorage.get(0), is("test string 0"));
        assertThat(this.stringStorage.get(1), is("test string 2"));
    }

    /**
     * Test that when remove three strings of ten then seven strings left.
     */
    @Test
    public void whenRemoveEveryThirdStringThenReduceNumberOfStringsToSeven() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.add("test string 3");
        this.stringStorage.add("test string 4");
        this.stringStorage.add("test string 5");
        this.stringStorage.add("test string 6");
        this.stringStorage.add("test string 7");
        this.stringStorage.add("test string 8");
        this.stringStorage.add("test string 9");
        this.stringStorage.remove(2);
        this.stringStorage.remove(4);
        this.stringStorage.remove(6);
        assertThat(this.stringStorage.get(0), is("test string 0"));
        assertThat(this.stringStorage.get(1), is("test string 1"));
        assertThat(this.stringStorage.get(2), is("test string 3"));
        assertThat(this.stringStorage.get(3), is("test string 4"));
        assertThat(this.stringStorage.get(4), is("test string 6"));
        assertThat(this.stringStorage.get(5), is("test string 7"));
        assertThat(this.stringStorage.get(6), is("test string 9"));
    }

    /**
     * Test that when remove nine strings of ten only one string left.
     */
    @Test
    public void whenRemoveNineStringsOutOfTenShouldReduceNumberOfStringsToOne() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.add("test string 3");
        this.stringStorage.add("test string 4");
        this.stringStorage.add("test string 5");
        this.stringStorage.add("test string 6");
        this.stringStorage.add("test string 7");
        this.stringStorage.add("test string 8");
        this.stringStorage.add("test string 9");
        this.stringStorage.remove(9);
        this.stringStorage.remove(8);
        this.stringStorage.remove(7);
        this.stringStorage.remove(6);
        this.stringStorage.remove(5);
        this.stringStorage.remove(4);
        this.stringStorage.remove(3);
        this.stringStorage.remove(2);
        this.stringStorage.remove(1);
        assertThat(this.stringStorage.get(0), is("test string 0"));
    }

    /**
     * When add remove strings sequentially size of storage does not change.
     */
    @Test
    public void whenAddRemoveSequentiallyThenSizeOfStorageIsNotAffected() {
        this.stringStorage.add("test string 0");
        this.stringStorage.remove(0);

        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.remove(0);
        this.stringStorage.remove(0);

        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.remove(0);
        this.stringStorage.remove(0);
        this.stringStorage.remove(0);

        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.add("test string 3");
        this.stringStorage.add("test string 4");
        this.stringStorage.add("test string 5");
        this.stringStorage.add("test string 6");
        this.stringStorage.add("test string 7");
        this.stringStorage.add("test string 8");
        this.stringStorage.add("test string 9");
        assertThat(this.stringStorage.get(0), is("test string 0"));
        assertThat(this.stringStorage.get(1), is("test string 1"));
        assertThat(this.stringStorage.get(2), is("test string 2"));
        assertThat(this.stringStorage.get(3), is("test string 3"));
        assertThat(this.stringStorage.get(4), is("test string 4"));
        assertThat(this.stringStorage.get(5), is("test string 5"));
        assertThat(this.stringStorage.get(6), is("test string 6"));
        assertThat(this.stringStorage.get(7), is("test string 7"));
        assertThat(this.stringStorage.get(8), is("test string 8"));
        assertThat(this.stringStorage.get(9), is("test string 9"));
    }

    /**
     * When remove from wrong position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromWrongPositionThenThrowIndexOutOfBound() {
        this.stringStorage.add("test string");
        this.stringStorage.remove(1);
    }

    /**
     * When remove from negative position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromNegativePositionThenThrowIndexOutOfBound() {
        this.stringStorage.add("test string");
        this.stringStorage.remove(-1);
    }

    /**
     * When remove from empty array exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromEmptyStorageThenThrowIndexOutOfBound() {
        this.stringStorage.remove(0);
    }

    /**
     * Check that iterator properly returns hasNext().
     */
    @Test
    public void whenGetIteratorShouldReturnCorrectNumberOfHasNext() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        Iterator<String> it = this.stringStorage.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 0"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 2"));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Check that iterator hasNext() invocation does not influence next() calls.
     */
    @Test
    public void whenGetIteratorThenHasNextDoesNotInfluenceNextMethod() {
        this.stringStorage.add("test string 0");
        Iterator<String> it = this.stringStorage.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 0"));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Iterator for empty storage should return false when call hasNext().
     */
    @Test
    public void whenGetIteratorForEmptyStorageThenHasNextShouldReturnFalse() {
        Iterator<String> it = this.stringStorage.iterator();
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Iterator next() call returns correct elements.
     */
    @Test
    public void whenCallIteratorNextShouldReturnCorrectElement() {
        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        Iterator<String> it = this.stringStorage.iterator();
        assertThat(it.next(), is("test string 0"));
        assertThat(it.next(), is("test string 1"));
        assertThat(it.next(), is("test string 2"));
    }

    /**
     * When call next() more times than there are elements exception is thrown.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCallIteratorNextMoreThenElementsShouldThrowNoSuchElement() {
        this.stringStorage.add("test string 0");
        Iterator<String> it = this.stringStorage.iterator();
        assertThat(it.next(), is("test string 0"));
        assertThat(it.next(), is("test string 1"));
    }

    /**
     * When add remove strings sequentially iterator works correctly.
     */
    @Test
    public void whenAddRemoveSequentiallyIteratorReturnsCorrectValues() {
        Iterator<String> it = this.stringStorage.iterator();

        this.stringStorage.add("test string 0");
        this.stringStorage.remove(0);
        assertThat(it.hasNext(), is(false));


        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.remove(0);
        assertThat(it.hasNext(), is(true));
        this.stringStorage.remove(0);
        assertThat(it.hasNext(), is(false));


        this.stringStorage.add("test string 0");
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        this.stringStorage.remove(0);
        assertThat(it.hasNext(), is(true));
        this.stringStorage.remove(0);
        assertThat(it.hasNext(), is(true));
        this.stringStorage.remove(0);
        assertThat(it.hasNext(), is(false));

        this.stringStorage.add("test string 0");
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 0"));
        assertThat(it.hasNext(), is(false));
        this.stringStorage.add("test string 1");
        this.stringStorage.add("test string 2");
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 1"));
        assertThat(it.next(), is("test string 2"));
        assertThat(it.hasNext(), is(false));
        this.stringStorage.add("test string 3");
        this.stringStorage.add("test string 4");
        this.stringStorage.add("test string 5");
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test string 3"));
        assertThat(it.next(), is("test string 4"));
        assertThat(it.next(), is("test string 5"));
        assertThat(it.hasNext(), is(false));
    }
}

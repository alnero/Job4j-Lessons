package alnero.simpleGenericArray;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test simple generic array with Number class.
 */
public class SimpleGenericArrayNumberTest {
    /**
     * Common storage for testing.
     */
    private SimpleGenericArray<Number> numberStorage;

    /**
     * Initialize common storage.
     */
    @Before
    public void setUp() {
        this.numberStorage = new SimpleGenericArray<>(Number.class, 10);
    }

    /**
     * Test that set generic class is returned.
     */
    @Test
    public void whenAddGenericNumberShouldReturnClassNumber() {
        this.numberStorage.add(1);
        assertThat(numberStorage.get(0).getClass().getSuperclass().getName(), is("java.lang.Number"));
    }
    /**
     * Test that added number is properly returned.
     */
    @Test
    public void whenAddFloatNumberShouldReturnAddedFloatNumber() {
        this.numberStorage.add(3.14f);
        assertThat(numberStorage.get(0), is(3.14f));
    }

    /**
     * When add to position more than size of storage exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddNumbersMoreThanSizeOfStorageShouldThrowIndexOutOfBounds() {
        SimpleGenericArray<Number> smallNumberStorage = new SimpleGenericArray<>(Number.class, 1);
        smallNumberStorage.add(1);
        smallNumberStorage.add(5L);
    }

    /**
     * Test that adding is done sequentially.
     */
    @Test
    public void whenAddThreeNumbersShouldAddToFirstEmptyPosition() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        assertThat(this.numberStorage.get(0), is(0));
        assertThat(this.numberStorage.get(1), is(1));
        assertThat(this.numberStorage.get(2), is(2));
    }

    /**
     * When get from wrong position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOneNumberAndGetFromPositionTwoShouldThrowIndexOutOfBounds() {
        this.numberStorage.add(1);
        this.numberStorage.get(2);
    }

    /**
     * When get from negative position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOneNumberAndGetFromNegativePositionShouldThrowIndexOutOfBounds() {
        this.numberStorage.add(1);
        this.numberStorage.get(-1);
    }

    /**
     * When get from empty array exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromEmptyStorageShouldThrowIndexOutOfBounds() {
        this.numberStorage.get(0);
    }

    /**
     * Test that element in storage is re-placed correctly.
     */
    @Test
    public void whenSetNumberShouldChangeNumberInStorage() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.set(1, 3.14f);
        assertThat(this.numberStorage.get(1), is(3.14f));
    }

    /**
     * When re-place in wrong position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetNumberToWrongPositionShouldThrowIndexOurOfBounds() {
        this.numberStorage.add(1);
        this.numberStorage.set(1, 3.14f);
    }

    /**
     * When re-place in negative position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetNumberToNegativePositionShouldThrowIndexOurOfBounds() {
        this.numberStorage.add(1);
        this.numberStorage.set(-1, 3.14f);
    }

    /**
     * When re-place in empty storage exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetToEmptyStorageShouldThrowIndexOurOfBounds() {
        this.numberStorage.set(0, 3.14f);
    }

    /**
     * Test that when remove one number two numbers left.
     */
    @Test
    public void whenRemoveOneNumberShouldReduceNumberOfElementsLeftInStorage() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.remove(1);
        assertThat(this.numberStorage.get(0), is(0));
        assertThat(this.numberStorage.get(1), is(2));
    }

    /**
     * Test that when remove three numbers of ten then seven numbers left.
     */
    @Test
    public void whenRemoveEveryThirdNumberThenQuantityOfNumberLeftAreSeven() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.add(3);
        this.numberStorage.add(4);
        this.numberStorage.add(5);
        this.numberStorage.add(6);
        this.numberStorage.add(7);
        this.numberStorage.add(8);
        this.numberStorage.add(9);
        this.numberStorage.remove(2);
        this.numberStorage.remove(4);
        this.numberStorage.remove(6);
        assertThat(this.numberStorage.get(0), is(0));
        assertThat(this.numberStorage.get(1), is(1));
        assertThat(this.numberStorage.get(2), is(3));
        assertThat(this.numberStorage.get(3), is(4));
        assertThat(this.numberStorage.get(4), is(6));
        assertThat(this.numberStorage.get(5), is(7));
        assertThat(this.numberStorage.get(6), is(9));
    }

    /**
     * Test that when remove nine numbers of ten only one number left.
     */
    @Test
    public void whenRemoveNineNumbersOutOfTenShouldReduceQuantityOfNumbersToOne() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.add(3);
        this.numberStorage.add(4);
        this.numberStorage.add(5);
        this.numberStorage.add(6);
        this.numberStorage.add(7);
        this.numberStorage.add(8);
        this.numberStorage.add(9);
        this.numberStorage.remove(9);
        this.numberStorage.remove(8);
        this.numberStorage.remove(7);
        this.numberStorage.remove(6);
        this.numberStorage.remove(5);
        this.numberStorage.remove(4);
        this.numberStorage.remove(3);
        this.numberStorage.remove(2);
        this.numberStorage.remove(1);
        assertThat(this.numberStorage.get(0), is(0));
    }

    /**
     * When add remove numbers sequentially size of storage does not change.
     */
    @Test
    public void whenAddRemoveSequentiallyThenSizeOfStorageIsNotAffected() {
        this.numberStorage.add(0);
        this.numberStorage.remove(0);

        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.remove(0);
        this.numberStorage.remove(0);

        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.remove(0);
        this.numberStorage.remove(0);
        this.numberStorage.remove(0);

        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.add(3);
        this.numberStorage.add(4);
        this.numberStorage.add(5);
        this.numberStorage.add(6);
        this.numberStorage.add(7);
        this.numberStorage.add(8);
        this.numberStorage.add(9);
        assertThat(this.numberStorage.get(0), is(0));
        assertThat(this.numberStorage.get(1), is(1));
        assertThat(this.numberStorage.get(2), is(2));
        assertThat(this.numberStorage.get(3), is(3));
        assertThat(this.numberStorage.get(4), is(4));
        assertThat(this.numberStorage.get(5), is(5));
        assertThat(this.numberStorage.get(6), is(6));
        assertThat(this.numberStorage.get(7), is(7));
        assertThat(this.numberStorage.get(8), is(8));
        assertThat(this.numberStorage.get(9), is(9));
    }

    /**
     * When remove from wrong position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromWrongPositionThenThrowIndexOutOfBound() {
        this.numberStorage.add(1);
        this.numberStorage.remove(1);
    }

    /**
     * When remove from negative position exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromNegativePositionThenThrowIndexOutOfBound() {
        this.numberStorage.add(1);
        this.numberStorage.remove(-1);
    }

    /**
     * When remove from empty array exception is thrown.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromEmptyStorageThenThrowIndexOutOfBound() {
        this.numberStorage.remove(0);
    }

    /**
     * Check that iterator properly returns hasNext().
     */
    @Test
    public void whenGetIteratorShouldReturnCorrectNumberOfHasNext() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        Iterator<Number> it = this.numberStorage.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Check that iterator hasNext() invocation does not influence next() calls.
     */
    @Test
    public void whenGetIteratorThenHasNextDoesNotInfluenceNextMethod() {
        this.numberStorage.add(3.14f);
        Iterator<Number> it = this.numberStorage.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3.14f));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Iterator for empty storage should return false when call hasNext().
     */
    @Test
    public void whenGetIteratorForEmptyStorageThenHasNextShouldReturnFalse() {
        Iterator<Number> it = this.numberStorage.iterator();
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Iterator next() call returns correct elements.
     */
    @Test
    public void whenCallIteratorNextShouldReturnCorrectElement() {
        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        Iterator<Number> it = this.numberStorage.iterator();
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    /**
     * When call next() more times than there are elements exception is thrown.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCallIteratorNextMoreThenElementsShouldThrowNoSuchElement() {
        this.numberStorage.add(1);
        Iterator<Number> it = this.numberStorage.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    /**
     * When add remove numbers sequentially iterator works correctly.
     */
    @Test
    public void whenAddRemoveSequentiallyIteratorReturnsCorrectValues() {
        Iterator<Number> it = this.numberStorage.iterator();

        this.numberStorage.add(0);
        this.numberStorage.remove(0);
        assertThat(it.hasNext(), is(false));


        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.remove(0);
        assertThat(it.hasNext(), is(true));
        this.numberStorage.remove(0);
        assertThat(it.hasNext(), is(false));


        this.numberStorage.add(0);
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        this.numberStorage.remove(0);
        assertThat(it.hasNext(), is(true));
        this.numberStorage.remove(0);
        assertThat(it.hasNext(), is(true));
        this.numberStorage.remove(0);
        assertThat(it.hasNext(), is(false));

        this.numberStorage.add(0);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(false));
        this.numberStorage.add(1);
        this.numberStorage.add(2);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
        this.numberStorage.add(3);
        this.numberStorage.add(4);
        this.numberStorage.add(5);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }
}

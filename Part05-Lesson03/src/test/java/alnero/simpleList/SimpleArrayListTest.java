package alnero.simpleList;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Testing of simple array list.
 */
public class SimpleArrayListTest {
    /** Common array list object for testing. */
    private SimpleArrayList<Integer> list;

    /**
     * Initialize common array list object before every test.
     */
    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Test that capacity of array list dynamically changes.
     */
    @Test
    public void whenAddMoreThanSizeOfArrayListThenListDynamicallyEnlarges() {
        assertThat(list.getSize(), is(3));
        list.add(4);
        assertThat(list.getSize(), is(4));
        list.add(5);
        assertThat(list.getSize(), is(5));
        list.add(6);
        assertThat(list.getSize(), is(6));
    }

    /**
     * Correct values returned from list when correct index used.
     */
    @Test
    public void whenGetByCorrectIndexThenListReturnsCorrectValues() {
        assertThat(list.get(0), is(1));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(3));
    }

    /**
     * Null returned when wrong index is used.
     */
    @Test
    public void whenGetByWrongIndexThenListReturnsNull() {
        assertNull(list.get(-3));
        assertNull(list.get(-2));
        assertNull(list.get(-1));

        assertNull(list.get(3));
        assertNull(list.get(4));
        assertNull(list.get(5));
    }

    /**
     * Iterator methods return correct values.
     */
    @Test
    public void whenGetIteratorThenItReturnsCorrectValues() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));

        assertThat(it.hasNext(), is(false));
    }

    /**
     * When no more elements iterator throws exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoMoreElementsThenIteratorThrowsException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Iterator hasNext() throws exception when list is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenListModifiedThenIteratorHasNextThrowsException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        list.add(4);
        it.hasNext();
    }

    /**
     * Iterator next() throws exception when list is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenListModifiedThenIteratorNextThrowsException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        list.add(4);
        it.next();
    }
}

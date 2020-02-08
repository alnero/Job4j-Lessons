package alnero.simpleList;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Testing of simple linked list with only add and get operations.
 */
public class SimpleLinkedListWithoutDeleteTest {
    /** Common linked list object for testing. */
    private SimpleLinkedListWithoutDelete<Integer> list;

    /**
     * Initialize common linked list.
     */
    @Before
    public void before() {
        list = new SimpleLinkedListWithoutDelete<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * When get elements by index then correct elements returned.
     * Elements are returned in reverse sequence.
     */
    @Test
    public void whenGetElementsByIndexThenCorrectElementsReturned() {
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(1));
    }

    /**
     * When add three more elements then added elements returned.
     */
    @Test
    public void whenAddThreeMoreElementsThenCorrectElementsReturned() {
        list.add(4);
        list.add(5);
        list.add(6);
        assertThat(list.get(0), is(6));
        assertThat(list.get(1), is(5));
        assertThat(list.get(2), is(4));
        assertThat(list.get(3), is(3));
        assertThat(list.get(4), is(2));
        assertThat(list.get(5), is(1));
    }

    /**
     * When use negative index then null returned.
     */
    @Test
    public void whenUseNegativeIndexToGetElementThenNullReturned() {
        assertNull(list.get(-1));
    }

    /**
     * When use index larger than size of list then null returned.
     */
    @Test
    public void whenUseIndexMoreThenSizeThenNullReturned() {
        assertNull(list.get(10));
    }

    /**
     * Iterator methods return correct values.
     */
    @Test
    public void whenGetIteratorThenItReturnsCorrectValues() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));

        assertThat(it.hasNext(), is(false));
    }

    /**
     * When no more elements iterator throws exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoMoreElementsThenIteratorThrowsException() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
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
        assertThat(it.next(), is(3));
        list.add(4);
        it.next();
    }
}

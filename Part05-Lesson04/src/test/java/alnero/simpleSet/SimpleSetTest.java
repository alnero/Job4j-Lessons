package alnero.simpleSet;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Testing of simple set.
 */
public class SimpleSetTest {
    /** Common set object for testing. */
    private SimpleSet<Integer> set;

    /**
     * Initialize common set object before every test.
     */
    @Before
    public void beforeTest() {
        set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
    }

    /**
     * Test that set contains added values.
     */
    @Test
    public void whenAddThreeElementsToSetThenCorrectElementsStored() {
        SimpleSet<Integer> threeElementsSet = new SimpleSet<>();
        threeElementsSet.add(1);
        threeElementsSet.add(2);
        threeElementsSet.add(3);
        assertThat(threeElementsSet, contains(1, 2, 3));
    }

    /**
     * Test that set does not contain duplicates.
     */
    @Test
    public void whenAddDuplicateElementsToSetThenOnlyUniqueElementsStored() {
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(3);
        assertThat(set, contains(1, 2, 3));
    }

    /**
     * Iterator methods return correct values.
     */
    @Test
    public void whenGetIteratorThenItReturnsCorrectValues() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), isOneOf(1, 2, 3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), isOneOf(1, 2, 3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), isOneOf(1, 2, 3));

        assertThat(it.hasNext(), is(false));
    }

    /**
     * When no more elements iterator throws exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoMoreElementsThenIteratorThrowsException() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), isOneOf(1, 2, 3));
        assertThat(it.next(), isOneOf(1, 2, 3));
        assertThat(it.next(), isOneOf(1, 2, 3));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Iterator hasNext() throws exception when set is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenSetModifiedThenIteratorHasNextThrowsException() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        set.add(4);
        it.hasNext();
    }

    /**
     * Iterator next() throws exception when set is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenSetModifiedThenIteratorNextThrowsException() {
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), isOneOf(1, 2, 3));
        set.add(4);
        it.next();
    }
}

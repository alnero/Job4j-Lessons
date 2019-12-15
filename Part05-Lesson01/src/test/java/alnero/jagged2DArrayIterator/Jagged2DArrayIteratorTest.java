package alnero.jagged2DArrayIterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Testing of 2D array iterator.
 */
public class Jagged2DArrayIteratorTest {
    /**
     * Common iterator object for testing.
     */
    private Iterator<Integer> it;

    /**
     * Initialize common iterator object for testing.
     */
    @Before
    public void setUp() {
        it = new Jagged2dArrayIterator(new int[][] {{1}, {3, 4}, {7}});
    }

    /**
     * Test that next() method is independent of previous next() calls.
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    /**
     * Test that hasNext() method does not influence next() method calls.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    /**
     * Test that step by step hasNext() and next() calls work as required.
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }
}
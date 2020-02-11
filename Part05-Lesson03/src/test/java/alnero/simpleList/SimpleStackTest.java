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
 * Testing of simple stack.
 */
public class SimpleStackTest {
    /** Common stack object for testing. */
    private SimpleStack<Integer> stack;

    /**
     * Initialize common stack.
     */
    @Before
    public void before() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    /**
     * When poll elements then elements are returned in reverse sequence.
     */
    @Test
    public void whenPollElementsThenCorrectElementsReturned() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    /**
     * When push three more elements then correct elements returned.
     */
    @Test
    public void whenPushThreeMoreElementsThenCorrectElementsReturned() {
        stack.push(4);
        stack.push(5);
        stack.push(6);
        assertThat(stack.poll(), is(6));
        assertThat(stack.poll(), is(5));
        assertThat(stack.poll(), is(4));
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    /**
     * When stack is empty null returned.
     */
    @Test
    public void whenPollFromEmptyStackThenNullReturned() {
        SimpleStack<Integer> emptyStack = new SimpleStack<>();
        assertNull(emptyStack.poll());
    }

    /**
     * Iterator methods return correct values.
     */
    @Test
    public void whenGetIteratorThenItReturnsCorrectValues() {
        Iterator<Integer> it = stack.iterator();
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
        Iterator<Integer> it = stack.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Iterator hasNext() throws exception when stack is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenStackModifiedThenIteratorHasNextThrowsException() {
        Iterator<Integer> it = stack.iterator();
        assertThat(it.hasNext(), is(true));
        stack.push(4);
        it.hasNext();
    }

    /**
     * Iterator next() throws exception when stack is modified.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenStackModifiedThenIteratorNextThrowsException() {
        Iterator<Integer> it = stack.iterator();
        assertThat(it.next(), is(3));
        stack.push(4);
        it.next();
    }
}

package alnero.simpleList;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Testing of simple queue.
 */
public class SimpleQueueTest {
    /** Common queue object for testing. */
    private SimpleQueue<Integer> queue;

    /**
     * Initialize common queue.
     */
    @Before
    public void before() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    /**
     * When poll elements then elements are returned in direct sequence.
     */
    @Test
    public void whenPollElementsThenCorrectElementsReturned() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

    /**
     * When push three more elements then correct elements returned.
     */
    @Test
    public void whenPushThreeMoreElementsThenCorrectElementsReturned() {
        queue.push(4);
        queue.push(5);
        queue.push(6);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
        assertThat(queue.poll(), is(6));
    }

    /**
     * When push poll randomly then correct elements returned.
     */
    @Test
    public void whenPushPollElementsRandomlyThenCorrectElementsReturned() {
        assertThat(queue.poll(), is(1));
        queue.push(4);
        assertThat(queue.poll(), is(2));
        queue.push(5);
        assertThat(queue.poll(), is(3));
        queue.push(6);
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
        queue.push(7);
        assertThat(queue.poll(), is(6));
        queue.push(8);
        queue.push(9);
        assertThat(queue.poll(), is(7));
        assertThat(queue.poll(), is(8));
        assertThat(queue.poll(), is(9));
        queue.push(10);
        assertThat(queue.poll(), is(10));
    }

    /**
     * When queue is empty null returned.
     */
    @Test
    public void whenPollFromEmptyQueueThenNullReturned() {
        SimpleQueue<Integer> emptyQueue = new SimpleQueue<>();
        assertNull(emptyQueue.poll());
    }
}

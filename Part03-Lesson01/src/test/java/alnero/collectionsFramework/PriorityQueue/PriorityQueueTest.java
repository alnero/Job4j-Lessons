package alnero.collectionsFramework.PriorityQueue;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 *
 */
public class PriorityQueueTest {
    /**
     * Get high priority task.
     */
    @Test
    public void whenAddThreeTaskAndGetHighPriorityTaskThenCorrectTaskReturned() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("high"));
    }

    /**
     * Get middle priority task.
     */
    @Test
    public void whenAddThreeTaskAndGetMiddlePriorityTaskThenCorrectTaskReturned() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("middle"));
    }

    /**
     * Get low priority task, add low priority task first.
     */
    @Test
    public void whenAddThreeTaskAndFirstAddLowAndGetLowPriorityTaskThenCorrectTaskReturned() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }

    /**
     * Get low priority task, add middle priority task first.
     */
    @Test
    public void whenAddThreeTaskAndFirstAddMiddleAndGetLowPriorityTaskThenCorrectTaskReturned() {
        var queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        var result = queue.take();
        result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }

    /**
     * Get low priority task, add high priority task first.
     */
    @Test
    public void whenAddThreeTaskAndFirstAddHighAndGetLowPriorityTaskThenCorrectTaskReturned() {
        var queue = new PriorityQueue();
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        var result = queue.take();
        result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }

    /**
     * Add three tasks with same priority.
     */
    @Test
    public void whenAddThreeTasksWithSamePriorityThenLastAddedTaskReturned() {
        var queue = new PriorityQueue();
        queue.put(new Task("middle - first added", 3));
        queue.put(new Task("middle - second added", 3));
        queue.put(new Task("middle - third added", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("middle - third added"));
    }
}

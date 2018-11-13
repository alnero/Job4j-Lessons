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
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("high"));
    }

    /**
     * Get middle priority task.
     */
    @Test
    public void whenAddThreeTaskAndGetMiddlePriorityTaskThenCorrectTaskReturned() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("middle"));
    }

    /**
     * Get low priority task, add low priority task first.
     */
    @Test
    public void whenAddThreeTaskAndFirstAddLowAndGetLowPriorityTaskThenCorrectTaskReturned() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }

    /**
     * Get low priority task, add middle priority task first.
     */
    @Test
    public void whenAddThreeTaskAndFirstAddMiddleAndGetLowPriorityTaskThenCorrectTaskReturned() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        queue.put(new Task("high", 1));
        Task result = queue.take();
        result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }

    /**
     * Get low priority task, add high priority task first.
     */
    @Test
    public void whenAddThreeTaskAndFirstAddHighAndGetLowPriorityTaskThenCorrectTaskReturned() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("high", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("low", 5));
        Task result = queue.take();
        result = queue.take();
        result = queue.take();
        assertThat(result.getDesc(), is("low"));
    }

    /**
     * Add three tasks with same priority.
     */
    @Test
    public void whenAddThreeTasksWithSamePriorityThenLastAddedTaskReturned() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("middle - first added", 3));
        queue.put(new Task("middle - second added", 3));
        queue.put(new Task("middle - third added", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("middle - third added"));
    }
}

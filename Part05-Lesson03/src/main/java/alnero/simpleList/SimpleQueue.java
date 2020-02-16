package alnero.simpleList;

/**
 * Simple queue based on two stacks, First in/First out.
 * @param <T> queue supports generics
 */
public class SimpleQueue<T> {
    /** Input stack. */
    private SimpleStack<T> inputStack = new SimpleStack<>();
    /** Output stack. */
    private SimpleStack<T> outputStack = new SimpleStack<>();

    /**
     * Get element from the queue using output stack.
     * If output stack is empty then first all elements from input stack transferred to output stack.
     * @return element or null if queue is empty
     */
    public T poll() {
        if (outputStack.getSize() == 0) {
            while (inputStack.getSize() > 0) {
                outputStack.push(inputStack.poll());
            }
        }
        return outputStack.poll();
    }

    /**
     * Push element to the queue.
     * @param value element to add to queue
     */
    public void push(T value) {
        inputStack.push(value);
    }
}

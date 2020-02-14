package alnero.simpleList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple stack, Last in/First out.
 * @param <T> stack supports generics
 */
public class SimpleStack<T> implements Iterable<T> {
    /** Stack size. */
    private int size;
    /** Reference to the element on the top of the stack. */
    private Node<T> first;
    /** Modification counter. */
    private int modCount;

    /**
     * Push element to the top of the stack.
     * @param value element to add to stack
     */
    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = first;
        first = newNode;
        size++;
        modCount++;
    }

    /**
     * Get element from the top of the stack.
     * @return element or null if stack is empty
     */
    public T poll() {
        T result = null;
        if (size > 0) {
            result = (T) first.data;
            first = first.next;
            size--;
        }
        return result;
    }
    /**
     * Get size of the stack.
     * @return size of stack
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Node of the stack.
     * @param <T> nodes support generics
     */
    private static class Node<T> {
        /** Data of the node. */
        private T data;
        /** Reference to next node in the stack. */
        private Node<T> next;

        /**
         * Create node with contained data.
         * @param data data to store in the node.
         */
        Node(T data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private Node currentNode = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (currentNode != null) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = (T) currentNode.data;
                currentNode = currentNode.next;
                return result;
            }
        };
    }
}

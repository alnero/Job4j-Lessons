package alnero.simpleList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple linked list, First -> Node.next -> Node.next -> Node.next -> null.
 * @param <E> list support generics
 */
public class SimpleLinkedListWithoutDelete<E> implements Iterable<E> {
    /** List size. */
    private int size;
    /** Reference to first element in the linked list. */
    private Node<E> first;
    /** Modification counter. */
    private int modCount;

    /**
     * Add element to the beginning of the list.
     * @param data element to add
     */
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.first;
        this.first = newNode;
        size++;
        modCount++;
    }

    /**
     * Get element by index starting from the beginning of the list.
     * @param index index of required element
     * @return element or null if list is empty
     */
    public E get(int index) {
        E result = null;
        if (index >= 0 && index <= size) {
            Node<E> node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            result = node.data;
        }
        return result;
    }

    /**
     * Node of the list.
     * @param <E> nodes support generics
     */
    private static class Node<E> {
        /** Data of the node. */
        private E data;
        /** Reference to next node in the list. */
        private Node<E> next;

        /**
         * Create node with contained data.
         * @param data data to store in the node.
         */
        Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = (E) currentNode.data;
                currentNode = currentNode.next;
                return result;
            }
        };
    }
}

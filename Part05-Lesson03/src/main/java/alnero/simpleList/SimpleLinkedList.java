package alnero.simpleList;

/**
 * Simple linked list, First -> Node.next -> Node.next -> Node.next -> null.
 * @param <E> list support generics
 */
public class SimpleLinkedList<E> {
    /** List size. */
    private int size;
    /** Reference to first element in the linked list. */
    private Node<E> first;

    /**
     * Add element to the beginning of the list.
     * @param data element to add
     */
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.first;
        this.first = newNode;
        size++;
    }

    /**
     * Delete first element of the list.
     * @return element or null if list is empty
     */
    public E delete() {
        E result = null;
        if (size > 0) {
            Node<E> firstNode = this.first;
            this.first = this.first.next;
            size--;
            result = firstNode.data;
        }
        return result;
    }

    /**
     * Get element by index starting from the beginning of the list.
     * @param index index of required element
     * @return element or null if list is empty
     */
    public E get(int index) {
        E result = null;
        if (size > 0) {
            Node<E> node = this.first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            result = node.data;
        }
        return result;
    }

    /**
     * Get size of the list.
     * @return size of list
     */
    public int getSize() {
        return this.size;
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
}

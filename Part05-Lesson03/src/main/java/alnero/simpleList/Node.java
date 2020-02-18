package alnero.simpleList;

/**
 * Node class.
 * @param <T> nodes support generics
 */
public class Node<T> {
    /** Value of the node. */
    private T value;
    /** Reference to next node. */
    private Node<T> next;

    /**
     * Create node with contained value.
     * @param value value to store in the node
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Check that nodes have cycle starting from first node.
     * @param first first node
     * @return are nodes cycled or not
     */
    public boolean hasCycle(Node first) {
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * Set reference to next node.
     * @param nextNode nest node
     */
    public void setNext(Node nextNode) {
        this.next = nextNode;
    }
}

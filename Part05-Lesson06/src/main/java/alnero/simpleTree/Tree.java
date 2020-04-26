package alnero.simpleTree;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;

class Tree<E> implements SimpleTree<E> {
    /** Root node. */
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Check that nodes do not have more than two child nodes.
     * @return is tree binary or not
     */
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            List<Node<E>> children = el.getChildren();
            if (children.size() > 2) {
                result = false;
                break;
            }
            data.addAll(children);
        }
        return result;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> optionalNode = this.findBy(parent);
        if (optionalNode.isPresent()) {
            Node<E> parentNode = optionalNode.get();
            parentNode.getChildren().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getValue().equals(value)) {
                result = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return result;
    }
}
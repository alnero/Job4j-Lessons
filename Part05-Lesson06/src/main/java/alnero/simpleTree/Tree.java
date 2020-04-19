package alnero.simpleTree;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;

class Tree<E> implements SimpleTree<E> {
    /** Root node. */
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (this.findBy(parent).isPresent()) {
            Node<E> parentNode = this.findBy(parent).get();
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
package alnero.simpleList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple array list.
 * @param <E> list supports generics
 */
public class SimpleArrayList<E> implements Iterable<E> {
    /** Inner storage. */
    private Object[] container;
    /** Inner index. */
    private int containerIndex;
    /** Modification counter. */
    private int modCount;

    /**
     * List is created with initial size.
     * @param initialSize initial size of list, if entered value <= 0 then initial size will be 1
     */
    public SimpleArrayList(int initialSize) {
        if (initialSize <= 0) {
           initialSize = 1;
        }
        this.container = new Object[initialSize];
    }

    /**
     * Add element to the list.
     * Size of the inner storage doubles when necessary.
     * @param value new element to add.
     */
    public void add(E value) {
        if (this.containerIndex == this.container.length) {
            this.extend();
        }
        this.container[this.containerIndex] = value;
        this.containerIndex++;
        this.modCount++;
    }

    /**
     * Double the size of inner storage.
     */
    private void extend() {
        Object[] newContainer = new Object[this.container.length * 2];
        System.arraycopy(this.container, 0, newContainer, 0, this.container.length);
        this.container = newContainer;
    }

    /**
     * Get element by index.
     * @param index index of required element
     * @return element or null in case if index is wrong
     */
    public E get(int index) {
        E result = null;
        if (index >= 0 && index < this.containerIndex) {
            result = (E) this.container[index];
        }
        return result;
    }

    /**
     * Get size of the list.
     * @return size of list
     */
    public int getSize() {
        return this.containerIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (iteratorIndex < containerIndex) {
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
                return (E) container[iteratorIndex++];
            }
        };
    }
}

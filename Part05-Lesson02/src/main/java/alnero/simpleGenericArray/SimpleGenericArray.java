package alnero.simpleGenericArray;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple array with generics support.
 * @param <T> any object
 */
public class SimpleGenericArray<T> implements Iterable<T> {
    /**
     * Main array of generic type.
     */
    private T[] storage;
    /**
     * Last available position in array.
     */
    private int lastAvailablePosition = 0;
    /**
     * Boolean flag that array is empty.
     */
    private boolean isEmpty = true;

    /**
     * Create generic array.
     * @param c generic type class
     * @param size size of array
     */
    public SimpleGenericArray(Class<T> c, int size) {
        this.storage = (T[]) Array.newInstance(c, size);
    }

    /**
     * Add element to array.
     * @param model element to add is of generic type
     */
    public void add(T model) {
        if (lastAvailablePosition >= storage.length) {
            throw new IndexOutOfBoundsException();
        }
        this.storage[lastAvailablePosition++] = model;
        if (this.isEmpty) {
            this.isEmpty = false;
        }
    }

    /**
     * Re-place element in array.
     * @param index position of old element
     * @param model new element
     */
    public void set(int index, T model) {
        checkIndex(index);
        this.storage[index] = model;
    }

    /**
     * Remove element from array, no empty cells in array, all elements shifted left.
     * @param index position of deleted element
     */
    public void remove(int index) {
        this.checkIndex(index);
        this.storage[index] = null;
        System.arraycopy(this.storage, index + 1, this.storage, index, this.lastAvailablePosition - index - 1);
        this.lastAvailablePosition--;
    }

    /**
     * Get element from array.
     * @param index position of element
     * @return element of generic type class
     */
    public T get(int index) {
        this.checkIndex(index);
        return this.storage[index];
    }

    /**
     * Check that index for operations: get, set, remove is in required bounds and that array is not empty.
     * @param index position to check
     */
    private void checkIndex(int index) {
        if (this.isEmpty || index < 0 || index >= lastAvailablePosition) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < lastAvailablePosition) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return storage[index++];
            }
        };
    }
}

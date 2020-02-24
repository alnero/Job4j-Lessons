package alnero.simpleSet;

import alnero.simpleList.SimpleArrayList;

import java.util.Iterator;

/**
 * Simple set.
 * @param <E> Set supports generics.
 */
public class SimpleSet<E> implements Iterable<E> {
    /** Simple array list is used as storage for set. */
    private SimpleArrayList<E> innerStorage;

    /**
     * Initialize set inner storage.
     */
    public SimpleSet() {
        innerStorage = new SimpleArrayList<>(10);
    }

    /**
     * Add values to set. No duplicates allowed.
     * @param value new element to add
     */
    public void add(E value) {
        if (hasDuplicate(value)) {
           return;
        }
        innerStorage.add(value);
    }

    /**
     * Check whether element is already stored in set.
     * @param value element to check
     * @return true or false
     */
    private boolean hasDuplicate(E value) {
        boolean result = false;
        Iterator<E> it = innerStorage.iterator();
        while (it.hasNext()) {
            E element = it.next();
            if (element.equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return innerStorage.iterator();
    }
}

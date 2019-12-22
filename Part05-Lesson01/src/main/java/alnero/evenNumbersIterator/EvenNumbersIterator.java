package alnero.evenNumbersIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Even numbers iterator through array of integers.
 * @param <T> int or Integer
 */
public class EvenNumbersIterator<T> implements Iterator<T> {
    /** Mixed numbers array. */
    private int[] mixedNumbersArray;
    /** Current index. */
    private int index = 0;

    /**
     * Constructor to initialize main array.
     * @param mixedNumbersArray array of even and odd numbers
     */
    public EvenNumbersIterator(int[] mixedNumbersArray) {
        this.mixedNumbersArray = mixedNumbersArray;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < this.mixedNumbersArray.length; i++) {
            if (this.mixedNumbersArray[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = this.mixedNumbersArray[this.index++];
        while (result % 2 != 0) {
            result = this.mixedNumbersArray[this.index++];
        }
        return (T) result;
    }
}

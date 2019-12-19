package alnero.evenNumbersIterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Even numbers iterator through array of integers.
 * @param <T> int or Integer
 */
public class EvenNumbersIterator<T> implements Iterator<T> {
    /** Even numbers array. */
    private int[] evenNumbersArray;
    /** Current index. */
    private int index = 0;

    /**
     * Constructor to initialize main array. Only even numbers will remain after initialisation.
     * @param mixedNumbersArray array of even and odd numbers
     */
    public EvenNumbersIterator(int[] mixedNumbersArray) {
        this.evenNumbersArray = Arrays.stream(mixedNumbersArray).filter(value -> value % 2 == 0).toArray();
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index != this.evenNumbersArray.length) {
            result = true;
        }
        return result;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = this.evenNumbersArray[this.index++];
        return (T) result;
    }
}

package alnero.evenNumbersIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Even numbers iterator through array of integers.
 * @param <T> int or Integer
 */
public class EvenNumbersIterator<T> implements Iterator<T> {
    /** Mixed numbers array. */
    private int[] numbers;
    /** Current index. */
    private int index = 0;

    /**
     * Constructor to initialize main array.
     * @param numbers array of even and odd numbers
     */
    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < this.numbers.length; i++) {
            if (this.numbers[i] % 2 == 0) {
                index = i;
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
        Integer result = this.numbers[this.index++];
        return (T) result;
    }
}

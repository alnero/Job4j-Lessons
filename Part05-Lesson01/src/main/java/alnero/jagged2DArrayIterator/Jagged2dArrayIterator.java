package alnero.jagged2DArrayIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator through 2D array.
 * @param <T> int or Integer
 */
public class Jagged2dArrayIterator<T> implements Iterator<T> {
    /** 2D array. */
    private int[][] jagged2dArray;
    /** Current index of row. */
    private int row = 0;
    /** Current index of column. */
    private int column = 0;

    /**
     * Constructor to initialize main 2D array.
     * @param jagged2dArray 2D array
     */
    public Jagged2dArrayIterator(int[][] jagged2dArray) {
        this.jagged2dArray = jagged2dArray;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.row < this.jagged2dArray.length && this.column < this.jagged2dArray[this.row].length) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Integer value = this.jagged2dArray[row][column++];
        if (column == this.jagged2dArray[this.row].length) {
            column = 0;
            row++;
        }
        return (T) value;
    }
}

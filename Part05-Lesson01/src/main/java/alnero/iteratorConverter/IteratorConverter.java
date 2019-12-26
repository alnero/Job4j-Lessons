package alnero.iteratorConverter;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Convert iterator of iterators to single iterator.
 */
public class IteratorConverter {
    /**
     * Convert iterator of iterators to single iterator.
     * @param globalIterator iterator of iterators
     * @return single iterator (converted iterator of iterators)
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> globalIterator) {
        return new Iterator<Integer>() {
            /**
             * Current iterator.
             */
            private Iterator<Integer> currentIterator = globalIterator.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (currentIterator.hasNext()) {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer result = currentIterator.next();
                if (!currentIterator.hasNext() && globalIterator.hasNext()) {
                    currentIterator = globalIterator.next();
                }
                return result;
            }
        };
    }
}

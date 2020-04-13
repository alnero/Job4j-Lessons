package alnero.simpleMap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple hash map.
 * @param <K> supports generics for keys
 * @param <V> supports generics for values
 */
public class SimpleHashMap<K, V> implements Iterable<Entry<K, V>> {
    /** Initial number of buckets in internal storage. */
    private static final int NUM_OF_BUCKETS = 8;
    /** Internal storage of entries. */
    private Entry<K, V>[] buckets;
    /** Size of hash map. */
    private int size;

    /**
     * Default constructor.
     */
    public SimpleHashMap() {
        this.buckets = new Entry[this.NUM_OF_BUCKETS];
    }

    /**
     * Constructor with required capacity.
     * @param capacity user specified capacity
     */
    public SimpleHashMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    /**
     * Adding entry to hash map.
     * If no space, storage expands.
     * If bucket is already occupied check keys equality and if true replace entry.
     * Fol null keys only first bucket.
     * @param key key of entry
     * @param value value of entry
     * @return result of insert operation
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (size == buckets.length) {
            this.doubleCapacity();
        }
        int indexOfBucket = key != null ? getBucketForKey(key) : 0;
        if (buckets[indexOfBucket] == null) {
            buckets[indexOfBucket] = new Entry<>(key, value);
            size++;
            result = true;
        } else if (key == null || buckets[indexOfBucket].getKey().equals(key)) {
            buckets[indexOfBucket] = new Entry<>(key, value);
            result = true;
        }
        return result;
    }

    /**
     * Get value of entry by key via keys' hashCode and equality check.
     * Fol null keys search only in first bucket.
     * @param key key of entry
     * @return value of entry or null if entry not found
     */
    public V get(K key) {
        V value = null;
        int indexOfBucket = key != null ? getBucketForKey(key) : 0;
        if (buckets[indexOfBucket] != null && (key == null || buckets[indexOfBucket].getKey().equals(key))) {
            value = buckets[indexOfBucket].getValue();
        }
        return value;
    }

    /**
     * Remove entry from hash map by key via keys' hashCode and equality check.
     * If entry not found returns false.
     * Fol null keys deletion only from first bucket.
     * @param key key of entry
     * @return result of delete operation
     */
    public boolean delete(K key) {
        boolean result = false;
        int indexOfBucket = key != null ? getBucketForKey(key) : 0;
        if (buckets[indexOfBucket] != null && (key == null || buckets[indexOfBucket].getKey().equals(key))) {
            buckets[indexOfBucket] = null;
            size--;
            result = true;
        }
        return result;
    }

    /**
     * Define bucket for entry by its key hashCode.
     * @param key key of entry
     * @return internal storage index (entry will be saved in bucket with this index)
     */
    private int getBucketForKey(K key) {
        return key.hashCode() % buckets.length;
    }

    /**
     * Double capacity of internal storage if no more empty buckets.
     */
    private void doubleCapacity() {
        Entry<K, V>[] oldBuckets = this.buckets;
        this.buckets = new Entry[this.buckets.length * 2];
        this.size = 0;
        for (Entry<K, V> entry : oldBuckets) {
            this.insert(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Get size of hash map.
     * @return size of hash map
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int expectedModCount = size;
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (size > 0 && iteratorIndex <= size) {
                    result = true;
                }
                return result;
            }

            @Override
            public Entry<K, V> next() {
                if (expectedModCount != size) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Entry<K, V> entry = null;
                while (entry == null) {
                    entry = buckets[iteratorIndex++];
                }
                return entry;
            }
        };
    }
}

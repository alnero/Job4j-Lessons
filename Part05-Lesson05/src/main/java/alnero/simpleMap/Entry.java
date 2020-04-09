package alnero.simpleMap;

import java.util.Objects;

/**
 * Key-value entry for simple hash map.
 * @param <K> Key supports generics.
 * @param <V> Value supports generics.
 */
public class Entry<K, V> {
    /** Key of entry. */
    private K key;
    /** Value of entry. */
    private V value;

    /**
     * Entry constructor.
     * @param key entry key
     * @param value entry value
     */
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get key of entry.
     * @return key of entry
     */
    public K getKey() {
        return key;
    }

    /**
     * Get value of entry.
     * @return value of entry
     */
    public V getValue() {
        return value;
    }

    /**
     * Set value of entry.
     * @param value new value of entry
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Two entries are equal only when their keys and values are equal.
     * @param o other Entry object
     * @return are entries equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Entry) {
            Entry e = (Entry) o;
            if (Objects.equals(this.key, e.getKey())
                && Objects.equals(this.value, e.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}

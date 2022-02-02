package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    /** Soft reference cache. */
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        SoftReference<V> softRef = cache.get(key);
        return softRef == null ? this.load(key) : softRef.get();
    }

    protected abstract V load(K key);

}

package alnero.simpleMap;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test simple hash map entry.
 */
public class EntryTest {
    /**
     * Test set/get of entry value.
     */
    @Test
    public void whenSetValueThenGetValue() {
        Entry<Integer, String> entry = new Entry<>(1, "zero");
        entry.setValue("one");
        assertThat(entry.getKey(), is(1));
        assertThat(entry.getValue(), is("one"));
    }

    /**
     * Test equality of entries.
     */
    @Test
    public void whenEntitiesAreEqualThenReturnTrue() {
        Entry<Integer, String> entry = new Entry<>(1, "one");
        Entry<Integer, String> cloneEntry = new Entry<>(1, "one");
        assertThat(entry.equals(cloneEntry), is(true));
    }

}

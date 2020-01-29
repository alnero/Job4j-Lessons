package alnero.simpleList;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Testing of simple linked list.
 */
public class SimpleLinkedListTest {
    /** Common linked list object for testing. */
    private SimpleLinkedList<Integer> list;

    /**
     * Initialize common linked list object before every test.
     */
    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * When get element by index 1 then result is value 2.
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    /**
     * When add three elements to the list then size of the list is 3.
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * When delete first element from the list then first element returned and size of the list decreased by one.
     */
    @Test
    public void whenAddThreeElementsAndDeleteThenResultThreeAndSizeTwo() {
        assertThat(list.delete(), is(3));
        assertThat(list.getSize(), is(2));
    }

    /**
     * When delete all elements then correct elements returned, size of the list decreases
     * and null returned when list is empty.
     */
    @Test
    public void whenAddThreeAndDeleteAllThenResultNullAndSizeZero() {
        assertThat(list.delete(), is(3));
        assertThat(list.getSize(), is(2));

        assertThat(list.delete(), is(2));
        assertThat(list.getSize(), is(1));

        assertThat(list.delete(), is(1));
        assertThat(list.getSize(), is(0));

        assertNull(list.delete());
        assertThat(list.getSize(), is(0));

        assertNull(list.delete());
        assertThat(list.getSize(), is(0));
    }

    /**
     * When try to get by index from empty list then null returned.
     */
    @Test
    public void whenAddThreeDeleteAllAndGetByIndexZeroThenResultIsNull() {
        assertThat(list.delete(), is(3));
        assertThat(list.delete(), is(2));
        assertThat(list.delete(), is(1));
        assertNull(list.delete());
        assertThat(list.getSize(), is(0));
        assertNull(list.get(0));

    }
}

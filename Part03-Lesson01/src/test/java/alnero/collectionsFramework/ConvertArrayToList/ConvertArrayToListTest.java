package alnero.collectionsFramework.ConvertArrayToList;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test conversion of 2-dimensional array to a list.
 */
public class ConvertArrayToListTest {
    /**
     * Array 2x2 -> List with 4 elements.
     */
    @Test
    public void when2by2ArrayConvertToListThenListHas4Elements() {
        ConvertArrayToList arrayConverter = new ConvertArrayToList();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = arrayConverter.toList(input);
        assertThat(result, is(expect));
    }

    /**
     * Array 5x5 -> List with 10 elements.
     */
    @Test
    public void when5by5ArrayConvertToListThenListHas10Elements() {
        ConvertArrayToList arrayConverter = new ConvertArrayToList();
        int[][] input = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );
        List<Integer> result = arrayConverter.toList(input);
        assertThat(result, is(expect));
    }

    /**
     * Array 5x2 -> List with 10 elements.
     */
    @Test
    public void when5by2ArrayConvertToListThenListHas10Elements() {
        ConvertArrayToList arrayConverter = new ConvertArrayToList();
        int[][] input = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {9, 10}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );
        List<Integer> result = arrayConverter.toList(input);
        assertThat(result, is(expect));
    }

    /**
     * Array with variable rows, having total 9 elements -> List with 9 elements.
     */
    @Test
    public void whenArrayWithVariableRowsWith9ElementsConvertToListThenListHas9Elements() {
        ConvertArrayToList arrayConverter = new ConvertArrayToList();
        int[][] input = {
                {1},
                {2, 3},
                {4, 5, 6},
                {7, 8},
                {9}

        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        );
        List<Integer> result = arrayConverter.toList(input);
        assertThat(result, is(expect));
    }

    /**
     * Array with no elements -> Empty list.
     */
    @Test
    public void whenArrayWithNoElementsConvertToListThenEmptyListReturned() {
        ConvertArrayToList arrayConverter = new ConvertArrayToList();
        int[][] input = {
                {},
                {},

        };
        List<Integer> expect = new ArrayList<>();
        List<Integer> result = arrayConverter.toList(input);
        assertThat(result, is(expect));
    }
}

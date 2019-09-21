package alnero.intMatrix;

import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Test conversion of 2D integer matrix to boxed integer list.
 */
public class IntMatrixTest {
    /**
     * Test conversion of 2D integer matrix to boxed integer list.
     */
    @Test
    public void whenConvertTwoDimensionalIntMatrixToListThenCorrectIntListReturned() {
        Integer[][] intMatrix = {
                {1,  2,  3,  4,  5},
                {6,  7,  8,  9, 10},
               {11, 12, 13, 14, 15}
        };

        List<Integer> intList = new IntMatrix().convertTwoDimensionalIntMatrixToIntList(intMatrix);
        List<Integer> expectedIntList = IntStream.iterate(1, i -> i + 1).limit(15).boxed().collect(Collectors.toList());
        assertEquals(intList, expectedIntList);
    }
}

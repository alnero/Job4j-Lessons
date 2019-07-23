package alnero.functionRange;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Function range class.
 */
public class FunctionRange {
    /**
     * Calculate function in specified range with step = 1.
     * @param start start of range (including)
     * @param end end of range (excluding)
     * @param func function supplied for calculation
     * @return list of results
     */
    public List<Double> calculateFunctionInRange(int start, int end, Function<Double, Double> func) {
        List<Double> listOfResults = new ArrayList<>();
        for (int index = start; index < end; index++) {
            double result = func.apply((double) index);
            listOfResults.add(result);
        }
        return listOfResults;
    }
}

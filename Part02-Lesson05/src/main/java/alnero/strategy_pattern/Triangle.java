package alnero.strategy_pattern;

/**
 * Class for triangle shape.
 */
public class Triangle implements Shape {

    /**
     * Drawing of triangle shape.
     * @return triangle is String format
     */
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("   +\n");
        sb.append("  + +\n");
        sb.append(" +   +\n");
        sb.append("+++++++\n");
        return sb.toString();
    }
}

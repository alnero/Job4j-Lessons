package alnero.strategyPattern;

/**
 * Class for Square shape.
 */
public class Square implements Shape {

    /**
     * Drawing of square shape.
     * @return square in String format
     */
    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("+++++++\n");
        sb.append("+     +\n");
        sb.append("+     +\n");
        sb.append("+++++++\n");
        return sb.toString();
    }
}


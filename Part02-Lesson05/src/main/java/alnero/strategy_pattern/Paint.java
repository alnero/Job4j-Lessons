package alnero.strategy_pattern;

/**
 * Class for shapes drawing.
 */
public class Paint {

    /**
     * Drawing any shape in String format.
     * @param shape object implementing Shape interface.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    /**
     * Main method to draw shapes.
     * @param args args for main method
     */
    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Square());
        paint.draw(new Triangle());
    }
}

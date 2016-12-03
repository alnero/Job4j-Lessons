package alnero.output;

import java.io.PrintStream;

/**
 * Class for output to system console.
 */
public class ConsoleOutput implements Output {
    /**
     * Reference to PrintStream object.
     */
    private PrintStream out;

    /**
     * Object created with inner reference to PrintStream performing actual output.
     * @param out PrintStream object
     */
    public ConsoleOutput(PrintStream out) {
        this.out = out;
    }

    /**
     * Print of values by PrintStream object.
     * @param value something to be printed
     */
    @Override
    public void println(Object value) {
        this.out.println(value);
    }
}

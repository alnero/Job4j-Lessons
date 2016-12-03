package alnero.output;

/**
 * Abstraction of Output system.
 */
public interface Output {
    /**
     * All objects will support printing of some values.
     * @param value something for output
     */
    void println(Object value);
}


package alnero.output;

/**
 * Mock output object, to collect output to StringBuilder for further use/testing.
 */
public class MockOutput implements Output {
    /**
     * Object collecting output
     */
    private StringBuilder output = new StringBuilder();

    /**
     * Every print of some value adds a line ot StringBuilder object
     * @param value something for output
     */
    @Override
    public void println(Object value) {
        this.output.append(value);
    }

    /**
     * Get all accumulated output from StringBuilder object.
     * @return String of accumulated output data
     */
    public String getOutput() {
        return this.output.toString();
    }
}

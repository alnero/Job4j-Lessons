package alnero;

/**
 * Class for the profession of Engineer.
 */
public class Engineer {
    /**
     * In which field of knowledge an engineer works.
     */
    private String field;

    /**
     * Do research in various fields.
     *
     * @param field some area of research
     */
    public void research(String field) {

    }

    /**
     * Add features to objects.
     *
     * @param thing some object to add feature to
     */
    public void addFeature(Object thing) {

    }

    /**
     * Investigate different problems.
     *
     * @param problem problem that need investigation
     */
    public void investigate(String problem) {

    }

    /**
     * Make drawing of the object.
     *
     * @param thing this object if to be drawn
     * @return new array of Pixels
     */
    public Drawing makeDrawing(Object thing) {
        return new Drawing();
    }

    /**
     * Give tasks and orders to other engineers.
     *
     * @param colleague engineer who is receiving a task
     */
    public void giveTask(Engineer colleague) {

    }
}

package alnero.genericStore.model;

/**
 * Base class for models.
 */
public abstract class Base {
    /** All models have id. */
    private final String id;

    /**
     * Id is always set when model is created.
     * @param id model id
     */
    protected Base(final String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    /**
     * Id getter.
     * @return id
     */
    public String getId() {
        return id;
    }
}
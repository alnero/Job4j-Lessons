package alnero.genericStore;

import alnero.genericStore.model.Base;

/**
 * Stores' interface for all Base models.
 * @param <T> Base class and children
 */
public interface Store<T extends Base> {
    /**
     * Add to store.
     * @param model model
     */
    void add(T model);

    /**
     * Replace model in store using id.
     * @param id id of model to remove
     * @param model model to insert
     * @return result of operation true or false
     */
    boolean replace(String id, T model);

    /**
     * Delete model from store using id.
     * @param id id of model to delete
     * @return result of operation true or false
     */
    boolean delete(String id);

    /**
     * Get model from store using id.
     * @param id search id of model
     * @return model object if found, null if not found
     */
    T findById(String id);
}
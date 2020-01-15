package alnero.genericStore;

import alnero.genericStore.model.Base;
import alnero.simpleGenericArray.SimpleGenericArray;
import java.util.Iterator;

/**
 * Store based on Simple Generic Array with common methods.
 * @param <T> Base class and children
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /** Generic store object. */
    private SimpleGenericArray<T> store;

    /**
     * Store setter.
     * @param store store object
     */
    public void setStore(SimpleGenericArray<T> store) {
        this.store = store;
    }

    /**
     * Converter from id of model to index of model in storage.
     * @param id id of model
     * @return index of model in storage
     */
    public int getIndexById(String id) {
        Iterator<T> it = this.store.iterator();
        int result = -1;
        int index = 0;
        while (it.hasNext()) {
            String elementId = it.next().getId();
            if (elementId.equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public boolean replace(String id, T model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
        int index = this.getIndexById(id);
        if (index != -1) {
            this.store.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public void add(T model) {
        if (model == null) {
            throw new IllegalArgumentException();
        }
        this.store.add(model);
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = this.getIndexById(id);
        if (index != -1) {
            this.store.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        int index = this.getIndexById(id);
        if (index != -1) {
            result = this.store.get(index);
        }
        return result;
    }
}

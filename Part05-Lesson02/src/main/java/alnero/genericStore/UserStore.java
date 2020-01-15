package alnero.genericStore;

import alnero.genericStore.model.User;
import alnero.simpleGenericArray.SimpleGenericArray;

/**
 * User store.
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Size of user store is set on creation and not dynamic value.
     * @param size size of store
     */
    public UserStore(int size) {
        this.setStore(new SimpleGenericArray<>(User.class, size));
    }
}

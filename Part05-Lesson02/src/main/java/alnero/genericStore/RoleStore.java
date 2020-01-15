package alnero.genericStore;

import alnero.genericStore.model.Role;
import alnero.simpleGenericArray.SimpleGenericArray;

/**
 * Role store.
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * Size of user store is set on creation and not dynamic value.
     * @param size size of store
     */
    public RoleStore(int size) {
        this.setStore(new SimpleGenericArray<>(Role.class, size));
    }
}

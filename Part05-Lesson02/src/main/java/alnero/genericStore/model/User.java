package alnero.genericStore.model;

/**
 * User model.
 */
public class User extends Base {
    /**
     * Id is always set when user is created.
     * @param id user id
     */
    public User(String id) {
        super(id);
    }
}

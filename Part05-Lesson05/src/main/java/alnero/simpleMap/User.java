package alnero.simpleMap;

import java.util.Calendar;
import java.util.Objects;

/**
 * User class for Map storage.
 */
public class User {
    /** User name. **/
    private String name;
    /** Number of children. **/
    private int children;
    /** User birth date. **/
    private Calendar birthday;

    /**
     * Simple user constructor.
     * @param name user name
     * @param children number of children
     * @param birthday birth date
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return children == user.children
            && Objects.equals(name, user.name)
            && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
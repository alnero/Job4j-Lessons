package alnero.simpleMap;

import java.util.Calendar;
import java.util.Objects;

/**
 * User class for Map storage.
 */
public class UserOnlyEqualsOverridden {
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
    public UserOnlyEqualsOverridden(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserOnlyEqualsOverridden)) {
            return false;
        }
        UserOnlyEqualsOverridden that = (UserOnlyEqualsOverridden) o;
        return    children == that.children
               && Objects.equals(name, that.name)
               && Objects.equals(birthday, that.birthday);
    }
}
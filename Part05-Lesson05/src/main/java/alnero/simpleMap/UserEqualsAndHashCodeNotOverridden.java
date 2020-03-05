package alnero.simpleMap;

import java.util.Calendar;

/**
 * User class for Map storage.
 */
public class UserEqualsAndHashCodeNotOverridden {
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
    public UserEqualsAndHashCodeNotOverridden(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}

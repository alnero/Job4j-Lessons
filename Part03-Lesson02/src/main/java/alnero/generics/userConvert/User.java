package alnero.generics.userConvert;

/**
 * User for conversion to map.
 */
public class User {
    /** User ID counter. */
    private static int idCounter;
    /** User ID. */
    private int id;
    /** User name. */
    private String name;
    /** User city. */
    private String city;

    /**
     * Main constructor, name and city to initialized. ID sets automatically.
     * @param name User name
     * @param city User city
     */
    public User(String name, String city) {
        this.setId();
        this.name = name;
        this.city = city;
    }

    /**
     * Reset ID counter.
     */
    public static void resetIdCounter() {
        idCounter = 0;
    }

    /**
     * IDs for users are created in sequential order.
     * ID type is int and ID starts from one.
     */
    private void setId() {
        this.id = ++idCounter;
    }

    /**
     * Get user ID.
     * @return User ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get user name.
     * @return User name
     */
    public String getName() {
        return name;
    }

    /**
     * Get user city.
     * @return User city
     */
    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getCity() != null ? getCity().equals(user.getCity()) : user.getCity() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", city='" + city + '\''
                + '}';
    }
}

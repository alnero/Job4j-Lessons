package alnero.sorting.sortUser;

/**
 * User for sorting.
 */
public class User implements Comparable<User> {
    /**
     * User name.
     */
    private String name;
    /**
     * User age.
     */
    private int age;

    /**
     * Main constructor, name and age to be initialized.
     *
     * @param name User name
     * @param age  User age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Get user age.
     *
     * @return User age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Get user name.
     *
     * @return User name
     */
    public String getName() {
        return this.name;
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

        if (getAge() != user.getAge()) {
            return false;
        }
        return getName() != null ? getName().equals(user.getName()) : user.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + ", name='" + this.name + '\''
                + ", age='" + this.age + '\''
                + '}';
    }

    @Override
    public int compareTo(User other) {
        if (other == null) {
            throw new NullPointerException("Other user is null.");
        }

        int i = this.age < other.getAge() ? -1 : (this.age == other.getAge() ? 0 : 1);
        if (i != 0) {
            return i;
        }
        return this.name.compareTo(other.getName());
    }
}
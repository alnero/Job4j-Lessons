package alnero.bankTransactions;

/**
 * Bank user.
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * User passport.
     */
    private String passport;

    /**
     * To create user name and passport are required.
     * @param name user name
     * @param passport user passport
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Get user passport.
     * @return user passport
     */
    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        User otherUser = (User) other;

        if (this.name != null ? !this.name.equals(otherUser.name) : otherUser.name != null) {
            return false;
        }
        return this.passport != null ? this.passport.equals(otherUser.passport) : otherUser.passport == null;
    }

    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.passport != null ? this.passport.hashCode() : 0);
        return result;
    }
}

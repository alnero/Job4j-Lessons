package alnero.collectionsFramework.PhoneBook;

/**
 * Person for Phone Book entry.
 */
public class Person {
    /**
     * Person name.
     */
    private String name;
    /**
     * Person surname.
     */
    private String surname;
    /**
     * Person phone.
     */
    private String phone;
    /**
     * Person address.
     */
    private String address;

    /**
     * Person is initialised with all fields.
     * @param name person name
     * @param surname persons surname
     * @param phone person phone
     * @param address person address
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Get person name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get person surname.
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Get person phone.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get person address.
     * @return address
     */
    public String getAddress() {
        return address;
    }
}

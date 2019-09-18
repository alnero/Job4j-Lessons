package alnero.profiles;

import java.util.Objects;

/**
 * Customer address.
 */
public class Address {
    /**
     * City name.
     */
    private String city;
    /**
     * Street name.
     */
    private String street;
    /**
     * House number.
     */
    private int house;
    /**
     * Apartment number.
     */
    private int apartment;

    /**
     * Create address with all fields: city, street, house number, apartment number.
     * @param city city name
     * @param street street name
     * @param house house number
     * @param apartment apartment number
     */
    public Address(String city, String street, int house, int apartment) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return house == address.house
               && apartment == address.apartment
               && Objects.equals(city, address.city)
               && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, apartment);
    }
}

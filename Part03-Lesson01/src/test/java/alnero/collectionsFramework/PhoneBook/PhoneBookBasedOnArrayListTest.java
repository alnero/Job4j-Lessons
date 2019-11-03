package alnero.collectionsFramework.PhoneBook;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Testing search of persons in Phone Book.
 */
public class PhoneBookBasedOnArrayListTest {
    /**
     * Find by name when Phone Book is empty.
     */
    @Test
    public void whenFindByNameAndPhoneBookIsEmptyThenNothingFound() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        var foundPersons = phoneBook.find("Thomas");
        assertThat(foundPersons.size(), is(0));
    }

    /**
     * Find person by name.
     */
    @Test
    public void whenFindByNameThenCorrectPersonReturned() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        var foundPersons = phoneBook.find("Thomas");
        assertThat(foundPersons.iterator().next().getSurname(), is("Anderson"));
    }

    /**
     * Find person by surname.
     */
    @Test
    public void whenFindBySurnameThenCorrectPersonReturned() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        var foundPersons = phoneBook.find("Anderson");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Find person by phone number.
     */
    @Test
    public void whenFindByPhoneNumberThenCorrectPersonReturned() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        var foundPersons = phoneBook.find("1628749");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Find person by address.
     */
    @Test
    public void whenFindByAddressThenCorrectPersonReturned() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        var foundPersons = phoneBook.find("Capital");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Find person by part of string.
     */
    @Test
    public void whenFindByPartOfStringThenCorrectPersonReturned() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        var foundPersons = phoneBook.find("nde");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Check that only one person found when search by part of string contained in several fields.
     */
    @Test
    public void whenFindByPartOfStringContainedInSeveralFieldsThenOnlyOnePersonReturned() {
        var phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Thomason", "+1628749", "Flomaster City"));
        var foundPersons = phoneBook.find("mas");
        assertThat(foundPersons.size(), is(1));
    }
}

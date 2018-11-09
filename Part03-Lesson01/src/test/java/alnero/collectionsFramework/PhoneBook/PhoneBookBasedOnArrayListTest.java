package alnero.collectionsFramework.PhoneBook;

import java.util.List;

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
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        List<Person> foundPersons = phoneBook.find("Thomas");
        assertThat(foundPersons.size(), is(0));
    }

    /**
     * Find person by name.
     */
    @Test
    public void whenFindByNameThenCorrectPersonReturned() {
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        List<Person> foundPersons = phoneBook.find("Thomas");
        assertThat(foundPersons.iterator().next().getSurname(), is("Anderson"));
    }

    /**
     * Find person by surname.
     */
    @Test
    public void whenFindBySurnameThenCorrectPersonReturned() {
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        List<Person> foundPersons = phoneBook.find("Anderson");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Find person by phone number.
     */
    @Test
    public void whenFindByPhoneNumberThenCorrectPersonReturned() {
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        List<Person> foundPersons = phoneBook.find("1628749");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Find person by address.
     */
    @Test
    public void whenFindByAddressThenCorrectPersonReturned() {
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        List<Person> foundPersons = phoneBook.find("Capital");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Find person by part of string.
     */
    @Test
    public void whenFindByPartOfStringThenCorrectPersonReturned() {
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Anderson", "+1628749", "Capital City"));
        List<Person> foundPersons = phoneBook.find("nde");
        assertThat(foundPersons.iterator().next().getName(), is("Thomas"));
    }

    /**
     * Check that only one person found when search by part of string contained in several fields.
     */
    @Test
    public void whenFindByPartOfStringContainedInSeveralFieldsThenOnlyOnePersonReturned() {
        PhoneBookBasedOnArrayList phoneBook = new PhoneBookBasedOnArrayList();
        phoneBook.add(new Person("Thomas", "Thomason", "+1628749", "Flomaster City"));
        List<Person> foundPersons = phoneBook.find("mas");
        assertThat(foundPersons.size(), is(1));
    }
}

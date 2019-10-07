package alnero.collectionsFramework.PhoneBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Phone Book based on ArrayList.
 */
public class PhoneBookBasedOnArrayList {
    /**
     * List of persons.
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Add person to list.
     * @param person person to add.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Find all persons containing key in any fields.
     * @param key Search key
     * @return List of found persons
     */
    public List<Person> find(String key) {
        var result = (List) new ArrayList<>();
        for (var person : this.persons) {
            if (person.getName().contains(key)
                || person.getSurname().contains(key)
                || person.getPhone().contains(key)
                || person.getAddress().contains(key)
               ) {
                result.add(person);
            }
        }
        return result;
    }
}

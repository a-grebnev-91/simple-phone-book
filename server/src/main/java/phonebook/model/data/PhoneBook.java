package phonebook.model.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<Integer, Person> people;

    public PhoneBook() {
        people = new HashMap<>();
    }

    public PhoneBook(Person[] people) {
        this();
        for (Person person : people) {
            this.people.put(person.getId(), person);
        }
    }

    public void createEntry(Person person) {
        int id = person.getId();
        people.put(id, person);
    }

    public Person get(int id) {
        return people.get(id);
    }

    public Collection<Person> getAll() {
        return people.values();
    }

    //TODO
    public Person getPersonByName(String firstName) {
        throw new RuntimeException("not implement");
    }

    //TODO

    public Person getPersonByNumber(int number) {
        throw new RuntimeException("not implement");
    }
    //TODO

    public Person getPersonBySurname(String lastName) {
        throw new RuntimeException("not implement");
    }

    public boolean removePerson(int id) {
        Person removedPerson = people.remove(id);
        return removedPerson != null;
    }
}

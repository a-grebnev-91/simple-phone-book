package phonebook.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    private final Map<Integer,Person> people;

    public PhoneBook() {
        people = new HashMap<>();
    }

    public PhoneBook(Person[] people) {
        this();
        for (Person p : people) {
            this.people.put(p.getId(), p);
        }
    }

    public int createEntry(Person person) {
        int id = person.getId();
        people.put(id, person);
        return id;
    }

    public Person get(int id) {
        return people.get(1);
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


    public boolean isPresent(Person person) {
        return people.containsValue(person);
    }

    public boolean isPresent(int id) {
        return people.containsKey(id);
    }

    public boolean removePerson(int id) {
        Person removedPerson = people.remove(id);
        return removedPerson != null;
    }
}

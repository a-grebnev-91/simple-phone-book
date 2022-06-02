package phonebook.model.managers;

import phonebook.model.data.Person;

import java.util.Collection;

public interface PhoneBookManager {

    boolean createPerson(Person person);

    Collection<Person> getAll();

    int getId();

    Person getPersonByName(String firstName);

    Person getPersonByNumber(int phoneNumber);

    Person getPersonBySurname(String secondName);

    boolean isPersonExist(Person personToAdd);
}

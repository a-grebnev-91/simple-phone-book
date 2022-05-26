package phonebook.managers;

import phonebook.data.Person;
import phonebook.data.PhoneBook;
import phonebook.util.json.JsonFileLoader;
import phonebook.util.json.JsonFileSaver;

import java.util.Collection;

public class FileBackedManager implements PhoneBookManager {
    private int id;
    private PhoneBook phoneBook;

    public FileBackedManager() {
        phoneBook = new PhoneBook();
        id = 1;
    }

    public FileBackedManager(Person[] people) {
        int id = 1;
        for (Person p : people) {
            if (p.getId() > id) {
                id = p.getId() + 1;
            }
        }
        this.id = id;
        this.phoneBook = new PhoneBook(people);
    }

    public static PhoneBookManager load() {
        return JsonFileLoader.load();
    }

    @Override
    public boolean createPerson(Person person) {
        person.setId(generateId());
        int id = phoneBook.createEntry(person);
        save();
        return id > 0;
    }

    @Override
    public Collection<Person> getAll() {
        return phoneBook.getAll();
    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public Person getPersonByName(String firstName) {
        return phoneBook.getPersonByName(firstName);
    }

    @Override
    public Person getPersonByNumber(int phoneNumber) {
        return phoneBook.getPersonByNumber(phoneNumber);
    }

    @Override
    public Person getPersonBySurname(String secondName) {
        return phoneBook.getPersonBySurname(secondName);
    }

    private void save() {
        JsonFileSaver.save(this);
    }

    private int generateId() {
        return id++;
    }
}

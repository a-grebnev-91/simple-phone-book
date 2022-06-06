package phonebook.model.managers;

import phonebook.model.data.Person;
import phonebook.model.data.PhoneBook;
import phonebook.util.json.JsonFileLoader;
import phonebook.util.json.JsonFileSaver;

import java.util.*;
import java.util.stream.Collectors;

public class CsvFileBackedManager implements PhoneBookManager {
    private int id;
    //todo зависимость
    private final PhoneBook phoneBook;
    private final Map<Long, Person> numbers;

    public CsvFileBackedManager() {
        numbers = new HashMap<>();
        phoneBook = new PhoneBook();
        id = 1;
    }

    public CsvFileBackedManager(Person[] people) {
        numbers = new HashMap<>();
        int id = 1;
        for (Person person : people) {
            if (person.getId() > id) {
                id = person.getId() + 1;
            }
            //todo double check this
            person.getNumbers().forEach(number -> numbers.put(number, person));
        }
        this.id = id;
        this.phoneBook = new PhoneBook(people);
    }

    public static PhoneBookManager load() {
        return JsonFileLoader.load();
    }

    //todo bullshit
    @Override
    public boolean createPerson(Person person) {
        Map<Long, Person> numbersToAdd = new HashMap<>();
        for (long number : person.getNumbers()) {
            numbersToAdd.put(number, person);
            if (numbers.containsKey(number))
                return false;
        }
        person.setId(generateId());
        phoneBook.createEntry(person);
        numbers.putAll(numbersToAdd);
        save();
        return true;
    }

    @Override
    public List<Person> getAll() {
        return phoneBook.getAll().stream().sorted(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList());
    }

    @Override
    public int getId() {
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

//    @Override
//    public boolean isPersonExist(Person personToAdd) {
//        //todo this is bullshit
//        return phoneBook.isPresent(personToAdd);
//    }

    @Override
    public boolean updatePerson(Person personToUpdate) {
        return false;
    }

    private void save() {
        JsonFileSaver.save(this);
    }

    private int generateId() {
        return id++;
    }
}

package phonebook;

import phonebook.data.Person;
import phonebook.managers.FileBackedManager;
import phonebook.managers.PhoneBookManager;
import phonebook.web.PhoneBookServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new PhoneBookServer().start();
    }

    private static void makeSomePeopleToCSV() {
        PhoneBookManager manager = new FileBackedManager();
        manager.createPerson(new Person("Алексей Гребнев", 9219702773L));
        manager.createPerson(new Person("Александр Овечкин", 9319224312L));
        manager.createPerson(new Person("Владимир Вовчкин", 9128321563L));
    }
}

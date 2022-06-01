package phonebook;

import phonebook.model.data.Person;
import phonebook.model.managers.FileBackedManager;
import phonebook.model.managers.PhoneBookManager;
import phonebook.controller.PhoneBookServer;

import java.io.IOException;

public class PhoneBookApplication {

    public static void main(String[] args) throws IOException {
        new PhoneBookServer().start();
//        makeSomePeopleToCSV();
    }

    private static void makeSomePeopleToCSV() {
        PhoneBookManager manager = new FileBackedManager();
        manager.createPerson(new Person("Петров Петр Петрович", "ООО Рога и копыта", 9219212773L));
        manager.createPerson(new Person("Иванов Иван Иванович", "Организация", 9319224312L));
        manager.createPerson(new Person("Рябов Владимир Константинович", "Управление по управлению всеми управлениями", 9128321563L));
        manager.createPerson(new Person("Алексеев Алексей Алексеевич", 9319701573L));
    }
}

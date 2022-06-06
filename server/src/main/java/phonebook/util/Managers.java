package phonebook.util;

import phonebook.model.managers.CsvFileBackedManager;
import phonebook.model.managers.PhoneBookManager;

public class Managers {

    public static PhoneBookManager getDefault() {
        return CsvFileBackedManager.load();
    }
}

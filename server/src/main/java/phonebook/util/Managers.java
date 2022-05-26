package phonebook.util;

import phonebook.managers.FileBackedManager;
import phonebook.managers.PhoneBookManager;

public class Managers {

    public static PhoneBookManager getDefault() {
        return FileBackedManager.load();
    }
}

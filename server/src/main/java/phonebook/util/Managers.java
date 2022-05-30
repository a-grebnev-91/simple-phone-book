package phonebook.util;

import phonebook.model.managers.FileBackedManager;
import phonebook.model.managers.PhoneBookManager;

public class Managers {

    public static PhoneBookManager getDefault() {
        return FileBackedManager.load();
    }
}

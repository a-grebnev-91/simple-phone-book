package phonebook.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import phonebook.model.data.Person;
import phonebook.model.managers.PhoneBookManager;
import phonebook.util.PropertiesLoader;

import java.io.*;
import java.nio.charset.Charset;

public class JsonFileSaver {
    private static final Gson gson;
    private static final String file;
    private static final Charset charset;

    static {
        file = PropertiesLoader.getSaveFilePath();
        charset = PropertiesLoader.getDefaultCharset();
        gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonTypeAdapter()).create();
    }

    public static void save(PhoneBookManager manager) {
        String json = gson.toJson(manager.getAll());

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(json.getBytes(charset));
        } catch (FileNotFoundException e) {
            throw new ManagerSaveException("File not found", e);
        }catch (IOException e) {
            throw new ManagerSaveException("Cannot save manager", e);
        }
    }

    static class ManagerSaveException extends RuntimeException {
        public ManagerSaveException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

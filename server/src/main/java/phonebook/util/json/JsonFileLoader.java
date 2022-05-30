package phonebook.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import phonebook.model.data.Person;
import phonebook.model.managers.FileBackedManager;
import phonebook.model.managers.PhoneBookManager;
import phonebook.util.PropertiesLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class JsonFileLoader {
    private static final Gson gson;
    private static final String file;
    private static final Charset charset;

    static {
        file = PropertiesLoader.getSaveFilePath();
        charset = PropertiesLoader.getDefaultCharset();
        gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonTypeAdapter()).create();
    }

    public static PhoneBookManager load() {
        String json = null;
        try(FileInputStream fis = new FileInputStream(file)) {
            json = new String(fis.readAllBytes(), charset);
        } catch (FileNotFoundException e) {
            throw new FileLoadException("File is not exist", e);
        } catch (IOException e) {
            throw new FileLoadException("Cannot load file", e);
        }

        return new FileBackedManager(gson.fromJson(json, Person[].class));
    }

    static class FileLoadException extends RuntimeException {
        public FileLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}


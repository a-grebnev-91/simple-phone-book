package phonebook.controller.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import phonebook.model.data.Person;
import phonebook.model.managers.PhoneBookManager;
import phonebook.util.PropertiesLoader;
import phonebook.util.json.PersonTypeAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class PhonesHandler implements HttpHandler {

    private final Gson gson;
    private HttpExchange exchange;
    private final PhoneBookManager manager;

    public PhonesHandler(PhoneBookManager manager) {
        this.gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonTypeAdapter()).create();
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        this.exchange = exchange;
        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                handleGetRequest();
                break;
            case "POST":
                handlePostRequest();
                break;
            default:
                sendMethodNotAllowed();
        }
    }

    private void handleGetRequest() throws IOException {
        String json = gson.toJson(manager.getAll());
        byte[] html = json.getBytes(PropertiesLoader.getDefaultCharset());
        exchange.sendResponseHeaders(200, html.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(html);
        }
    }

    private void handlePostRequest() throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), PropertiesLoader.getDefaultCharset());
        body = body.trim();
        Person personToAdd = gson.fromJson(body, Person.class);
        FileInputStream fis;
        if (addPerson(personToAdd)) {
            fis = new FileInputStream(PropertiesLoader.getProperty("/phones/added"));
        } else {
            fis = new FileInputStream(PropertiesLoader.getProperty("/phones/not-added"));
        }
        byte[] html = fis.readAllBytes();
        fis.close();
        exchange.sendResponseHeaders(200, html.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(html);
        }
    }

    private boolean addPerson(Person personToAdd) {
        if (manager.isPersonExist(personToAdd)) {
            return false;
        } else {
            return manager.createPerson(personToAdd);
        }
    }

    private void sendMethodNotAllowed() throws IOException {
        exchange.sendResponseHeaders(405, 0);
        exchange.close();
    }
}


package phonebook.web.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import phonebook.data.Person;
import phonebook.managers.PhoneBookManager;
import phonebook.util.PropertiesLoader;
import phonebook.util.json.PersonTypeAdapter;

import java.io.IOException;
import java.io.OutputStream;

public class PhonesHandler implements HttpHandler {

    private final Gson gson;
    private final PhoneBookManager manager;

    public PhonesHandler(PhoneBookManager manager) {
        this.gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonTypeAdapter()).create();
        this.manager = manager;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            String json = gson.toJson(manager.getAll());
            byte[] html = json.getBytes(PropertiesLoader.getDefaultCharset());
            exchange.sendResponseHeaders(200, html.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(html);
            }
        } else if (exchange.getRequestMethod().equals("POST")) {
            //TODO not implemented

            exchange.sendResponseHeaders(204, 0);
        } else {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();
        }
    }
}

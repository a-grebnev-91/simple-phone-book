package phonebook.web;

import com.sun.net.httpserver.HttpServer;
import phonebook.managers.PhoneBookManager;
import phonebook.util.Managers;
import phonebook.web.handlers.AssetsHandler;
import phonebook.web.handlers.IndexHandler;
import phonebook.web.handlers.PhonesHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class PhoneBookServer {
    private final HttpServer server;
    private final int port;
    private PhoneBookManager manager;

    public PhoneBookServer() throws IOException {
        this(8080);
    }

    public PhoneBookServer(int port) throws IOException {
        this.port = port;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        manager = Managers.getDefault();
        setupServer();
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    //navigation through pages implementing by request header X-Requesting-Page
    private void setupServer() {
        server.createContext("/", new IndexHandler());
        server.createContext("/phones", new PhonesHandler(manager));
        server.createContext("/assets/", new AssetsHandler());
    }
}

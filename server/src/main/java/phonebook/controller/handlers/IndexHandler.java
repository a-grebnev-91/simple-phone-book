package phonebook.controller.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import phonebook.util.PropertiesLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class IndexHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            String pathToPage = getPathByUri(exchange.getRequestURI());
            FileInputStream is = new FileInputStream(pathToPage);
            byte[] html = is.readAllBytes();
            is.close();
            exchange.sendResponseHeaders(200, html.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(html);
            }
        } else {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();
        }
    }

    private String getPathByUri(URI uri) {
        String path = uri.getPath();
        if (path.equals("/"))
            return PropertiesLoader.getIndexHtml();
        return PropertiesLoader.getProperty(path);
    }
}

package phonebook.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesLoader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("server/src/main/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAddHtml() {
        return properties.getProperty("/add");
    }

    public static String getIndexHtml() {
        return properties.getProperty("/");
    }

    public static Charset getDefaultCharset() {
        return Charset.forName(properties.getProperty("defaultCharset"));
    }

    public static String getProperty(String name) {
        return properties.getProperty(name);
    }

    public static String getSaveFilePath() {
        return properties.getProperty("jsonFile");
    }

    public static String getRequestingPageHeader() {
        return properties.getProperty("requestingPageHeader");
    }

    public static String getRoot() {
        return properties.getProperty("root");
    }
}

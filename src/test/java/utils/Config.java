package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("url.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load url.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
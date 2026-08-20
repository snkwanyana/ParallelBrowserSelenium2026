package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

public class ReadFromProperty {

    private static final Path PROPERTIES_DIR = Paths.get(
            System.getProperty("user.dir"),
            "src",
            "test",
            "resources",
            "properties"
    );

    public static Properties loadProperties() {
        String environment = getEnvironment();
        Path propertiesFile = PROPERTIES_DIR.resolve(capitalize(environment) + ".properties");

        if (!Files.exists(propertiesFile)) {
            throw new IllegalArgumentException("No properties file found for testing environment '" + environment + "'. Expected " + propertiesFile);
        }

        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(propertiesFile)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load properties for testing environment '" + environment + "'", e);
        }

        return properties;
    }

    public static String getRequiredProperty(String key) {
        String value = loadProperties().getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required property '" + key + "' for testing environment '" + getEnvironment() + "'");
        }
        return value.trim();
    }

    public static String getEnvironment() {
        String environment = System.getProperty("environment", "dev");
        return environment == null || environment.isBlank() ? "dev" : environment.trim().toLowerCase(Locale.ROOT);
    }

    private static String capitalize(String value) {
        return value.substring(0, 1).toUpperCase(Locale.ROOT) + value.substring(1).toLowerCase(Locale.ROOT);
    }
}

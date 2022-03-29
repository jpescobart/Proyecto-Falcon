package co.avista.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static final Properties prop = new Properties();

    public enum PropertyFiles{
        PREMADE_REQUEST("premadeRequests"),
        CONFIGURATION("configuration");
        private final String value;

        PropertyFiles(String value) {
            this.value = value;
        }

    }

    public PropertiesManager() {
        throw new IllegalStateException("Utility class");
    }



    public static String getProperty(PropertyFiles propertyFile, String property) throws IOException {
        String file=String.format("src/main/resources/properties/%s.properties",propertyFile.value);
        prop.load(new FileInputStream(file));
        return prop.getProperty(property);
    }

}

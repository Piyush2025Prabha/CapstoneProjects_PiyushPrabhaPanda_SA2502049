package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {	
	private static Properties prop;
    static {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
    public static String get(String key) {
        return prop.getProperty(key);
    }
}

package org.example.jms;


import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static Properties properties;

    static {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("org/example/jms/config.properties")) {
            properties = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

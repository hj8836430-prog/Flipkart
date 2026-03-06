package config;    // ← line 1 exactly yahi honi chahiye

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = 
        "src/main/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            ConfigReader.properties = new Properties();
            ConfigReader.properties.load(fis);
        } catch (IOException e) {
            System.err.println("config.properties nahi mili: " + e.getMessage());
            throw new RuntimeException("config.properties nahi mili!", e);
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(
            properties.getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(
            properties.getProperty("explicit.wait", "15"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(
            properties.getProperty("page.load.timeout", "30"));
    }

    public static String getScreenshotPath() {
        return properties.getProperty("screenshot.path", "./screenshots/");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(
            properties.getProperty("headless", "false"));
    }

    public static String getPhoneNumber() {
        return properties.getProperty("phone.number", "9782017696");
    }
    
    
    
}
package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import config.ConfigReader;

public class BaseClass {

    // ye line exactly aisi honi chahiye
    protected static WebDriver driver;

    @BeforeClass
    public void setUp() {
        String browser = ConfigReader.getBrowser();

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (ConfigReader.isHeadless()) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                } else {
                    options.addArguments("--start-maximized");
                }
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-blink-features=AutomationControlled");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.manage().timeouts()
            .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts()
            .pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());
        System.out.println("Browser open hua: " + ConfigReader.getBaseUrl());
    }

    public String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = ConfigReader.getScreenshotPath()
                      + testName + "_"
                      + System.currentTimeMillis() + ".png";
        try {
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            System.err.println("Screenshot error: " + e.getMessage());
        }
        return path;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser band ho gaya");
        }
    }
}

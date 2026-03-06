package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // CONSTRUCTOR
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigReader.getImplicitWait()));
        PageFactory.initElements(driver, this);
    }

    // =========================================
    // 🔍 LOCATORS — @FindBy se elements dhundo
    // =========================================

    // Login Button (Top right) - More robust selector
    @FindBy(xpath = "//a[contains(text(),'Login') or contains(@href,'account')]")
    private WebElement loginButton;

    // Search Box - Multiple selector options
    @FindBy(xpath = "//input[contains(@placeholder,'Search') or contains(@placeholder,'Products')]")
    private WebElement searchBox;

    // Search Button - More flexible
    @FindBy(xpath = "//button[contains(@class,'search') or @type='submit']")
    private WebElement searchButton;

    // Cart Icon - CSS selector fallback
    @FindBy(xpath = "//a[contains(@href,'cart') or contains(text(),'Cart')]")
    private WebElement cartIcon;

    // Flipkart Logo - More flexible
    @FindBy(xpath = "//img[contains(@alt,'Flipkart') or @title='Flipkart'] | //a[contains(@href,'flipkart.com')]//img")
    private WebElement flipkartLogo;

    // Close Login Popup (jo pehle aata hai)
    @FindBy(xpath = "//button[contains(@class,'close') or contains(text(),'✕') or @aria-label='Close']")
    private WebElement closeLoginPopup;

    // =========================================
    // METHODS — Actions
    // =========================================

    // Login Popup band karo
    public void closeLoginPopup() {
        try {
            Thread.sleep(2000); // Wait for popup to load
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
            closeLoginPopup.click();
            System.out.println("Login popup band hua");
        } catch (Exception e) {
            System.out.println("Login popup nahi aaya ya close nahi ho paya: " + e.getMessage());
        }
    }

    // Login button click karo
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        System.out.println("Login button click hua");
    }

    // Search karo
    public void searchProduct(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBox));
            searchBox.clear();
            searchBox.sendKeys(productName);
            
            // Wait for any overlays to disappear and scroll into view
            Thread.sleep(1000);
            
            // Use JavaScript executor to click if normal click fails
            try {
                searchButton.click();
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JavaScript click");
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
            }
            
            System.out.println("Search kiya: " + productName);
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
            throw new RuntimeException("Search failed: " + e.getMessage());
        }
    }

    // Cart click karo
    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
        System.out.println("Cart click hua");
    }

    // Logo visible hai?
    public boolean isLogoVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(flipkartLogo));
            boolean displayed = flipkartLogo.isDisplayed();
            System.out.println("Logo visible hai");
            return displayed;
        } catch (Exception e) {
            System.out.println("Logo element nahi mila: " + e.getMessage());
            return false;
        }
    }

    // Page title return karo
    public String getHomePageTitle() {
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);
        return title;
    }

    // Current URL return karo
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        return url;
    }

    // Login button visible hai?
    public boolean isLoginButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            boolean displayed = loginButton.isDisplayed();
            System.out.println("Login button visible hai");
            return displayed;
        } catch (Exception e) {
            System.out.println("Login button element nahi mila: " + e.getMessage());
            // Fallback: Check if page is loaded properly
            String url = driver.getCurrentUrl();
            if (url != null && url.contains("flipkart")) {
                System.out.println("But page is loaded on: " + url);
            }
            return false;
        }
    } 
    
    
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    // ✅ CONSTRUCTOR
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigReader.getImplicitWait()));
        PageFactory.initElements(driver, this);
    }

    // =========================================
    // 🔍 LOCATORS — @FindBy se elements dhundo
    // =========================================

    // Login Button (Top right)
    @FindBy(xpath = "//a[contains(text(),'Login')]")
    private WebElement loginButton;

    // Search Box
    @FindBy(xpath = "//input[@title='Search for Products, Brands and More']")
    private WebElement searchBox;

    // Search Button
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    // Cart Icon
    @FindBy(xpath = "//span[contains(text(),'Cart')]")
    private WebElement cartIcon;

    // Flipkart Logo
    @FindBy(xpath = "//img[@title='Flipkart']")
    private WebElement flipkartLogo;

    // Close Login Popup (jo pehle aata hai)
    @FindBy(xpath = "//button[contains(text(),'✕')]")
    private WebElement closeLoginPopup;

    // Become Seller
    @FindBy(xpath = "//a[contains(text(),'Become a Seller')]")
    private WebElement becomeSellerLink;

    // =========================================
    // ✅ METHODS — Actions
    // =========================================

    // Login Popup band karo
    public void closeLoginPopup() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup));
            closeLoginPopup.click();
            System.out.println("✅ Login popup band hua");
        } catch (Exception e) {
            System.out.println("ℹ️ Login popup nahi aaya");
        }
    }

    // Login button click karo
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        System.out.println("✅ Login button click hua");
    }

    // Search karo
    public void searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
        System.out.println("✅ Search kiya: " + productName);
    }

    // Cart click karo
    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        cartIcon.click();
        System.out.println("✅ Cart click hua");
    }

    // Logo visible hai?
    public boolean isLogoVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(flipkartLogo));
            return flipkartLogo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Page title return karo
    public String getHomePageTitle() {
        return driver.getTitle();
    }

    // Current URL return karo
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Login button visible hai?
    public boolean isLoginButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return loginButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    } 
    
    
}

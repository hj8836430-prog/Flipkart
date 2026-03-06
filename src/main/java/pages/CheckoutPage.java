package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;

    // CONSTRUCTOR
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigReader.getExplicitWait()));
        initPageElements();
    }

    private void initPageElements() {
        PageFactory.initElements(driver, this);
    }

    // LOCATORS

    // Continue shopping button
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement continueShoppingBtn;

    // Login required message
    @FindBy(xpath = "//div[contains(text(),'Login')]")
    private WebElement loginMsg;

    // Place order button
    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderBtn;

    // Order summary
    @FindBy(xpath = "//div[@class='vCR-VD']/ancestor::div[@class='_3LWZlK']")
    private WebElement orderSummary;

    // Delivery address section
    @FindBy(xpath = "//div[contains(text(),'Delivery')]")
    private WebElement deliverySection;

    // Payment method section
    @FindBy(xpath = "//div[contains(text(),'Payment')]")
    private WebElement paymentSection;

    // METHODS

    // Check if on checkout page
    public boolean isOnCheckoutPage() {
        try {
            String url = driver.getCurrentUrl();
            System.out.println("Current URL: " + url);
            return url.contains("checkout") || url.contains("payment");
        } catch (RuntimeException e) {
            return false;
        }
    }

    // Check if login is required
    public boolean isLoginRequired() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginMsg));
            System.out.println("Login required for checkout");
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Login not required");
            return false;
        }
    }

    // Get order summary details
    public String getOrderSummary() {
        try {
            wait.until(ExpectedConditions.visibilityOf(orderSummary));
            String summary = orderSummary.getText();
            System.out.println("Order Summary: " + summary);
            return summary;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Order summary not found: " + e.getMessage());
            return "Summary not available";
        }
    }

    // Check if delivery section is visible
    public boolean isDeliverySectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(deliverySection));
            System.out.println("Delivery section is visible");
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Delivery section not visible: " + e.getMessage());
            return false;
        }
    }

    // Check if payment section is visible
    public boolean isPaymentSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(paymentSection));
            System.out.println("Payment section is visible");
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Payment section not visible: " + e.getMessage());
            return false;
        }
    }

    // Check if place order button is visible
    public boolean isPlaceOrderVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(placeOrderBtn));
            System.out.println("Place Order button is visible");
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Place Order button not visible: " + e.getMessage());
            return false;
        }
    }

    // Click continue shopping button
    public void clickContinueShopping() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueShoppingBtn));
            continueShoppingBtn.click();
            System.out.println("Continue shopping button clicked");
            Thread.sleep(2000);
        } catch (InterruptedException | RuntimeException e) {
            System.out.println("Continue shopping click failed: " + e.getMessage());
        }
    }

    // Get current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

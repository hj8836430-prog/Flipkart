package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    // CONSTRUCTOR
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigReader.getExplicitWait()));
        initPageElements();
    }

    private void initPageElements() {
        PageFactory.initElements(driver, this);
    }

    // LOCATORS
    
    // First product in search results
    @FindBy(xpath = "//div[@class='KzDlHZ']/ancestor::div[@class='slAVV4']")
    private WebElement firstProduct;

    // Add to Cart button
    @FindBy(xpath = "//button[contains(text(),'Add to Cart') or contains(text(),'Cart')]")
    private WebElement addToCartBtn;

    // Buy Now button
    @FindBy(xpath = "//button[contains(text(),'Buy Now')]")
    private WebElement buyNowBtn;

    // Product title
    @FindBy(xpath = "//span[@class='B_NuCI']")
    private WebElement productTitle;

    // Product price
    @FindBy(xpath = "//div[@class='_30jeq3 _16Jk6d']")
    private WebElement productPrice;

    // Product rating
    @FindBy(xpath = "//div[@class='_3LWZlK']")
    private WebElement productRating;

    // METHODS

    // Click on first product
    public void clickFirstProduct() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
            firstProduct.click();
            System.out.println("First product clicked");
            Thread.sleep(2000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("First product click failed: " + e.getMessage());
            throw new RuntimeException("Failed to click first product: " + e.getMessage());
        }
    }

    // Get product title
    public String getProductTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productTitle));
            String title = productTitle.getText();
            System.out.println("Product Title: " + title);
            return title;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Product title not found: " + e.getMessage());
            return "Product title not available";
        }
    }

    // Get product price
    public String getProductPrice() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productPrice));
            String price = productPrice.getText();
            System.out.println("Product Price: " + price);
            return price;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Product price not found: " + e.getMessage());
            return "Price not available";
        }
    }

    // Get product rating
    public String getProductRating() {
        try {
            wait.until(ExpectedConditions.visibilityOf(productRating));
            String rating = productRating.getText();
            System.out.println("Product Rating: " + rating);
            return rating;
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Product rating not found: " + e.getMessage());
            return "Rating not available";
        }
    }

    // Add product to cart
    public void addToCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
            Thread.sleep(1000);
            
            try {
                addToCartBtn.click();
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Normal click failed, using JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartBtn);
            }
            
            System.out.println("Product added to cart");
            Thread.sleep(2000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Add to cart failed: " + e.getMessage());
            throw new RuntimeException("Failed to add product to cart: " + e.getMessage());
        }
    }

    // Click Buy Now button
    public void clickBuyNow() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(buyNowBtn));
            // Scroll element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buyNowBtn);
            Thread.sleep(1000);
            
            try {
                buyNowBtn.click();
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Normal click failed, using JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buyNowBtn);
            }
            
            System.out.println("Buy Now button clicked");
            Thread.sleep(2000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Buy Now click failed: " + e.getMessage());
            throw new RuntimeException("Failed to click Buy Now: " + e.getMessage());
        }
    }

    // Check if Add to Cart button is visible
    public boolean isAddToCartVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
            System.out.println("Add to Cart button is visible");
            return true;
        } catch (Exception e) {
            System.out.println("Add to Cart button not visible: " + e.getMessage());
            return false;
        }
    }

    // Check if Buy Now button is visible
    public boolean isBuyNowVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(buyNowBtn));
            System.out.println("Buy Now button is visible");
            return true;
        } catch (Exception e) {
            System.out.println("Buy Now button not visible: " + e.getMessage());
            return false;
        }
    }

    // Get current URL
    public String getCurrentUrl() {
        try {
            String url = driver.getCurrentUrl();
            System.out.println("Current URL: " + url);
            return url;
        } catch (Exception e) {
            System.out.println("Failed to get current URL: " + e.getMessage());
            return "";
        }
    }
}

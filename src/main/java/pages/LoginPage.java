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

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // CONSTRUCTOR
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 
            Duration.ofSeconds(ConfigReader.getExplicitWait()));
        initPageElements();
    }

    private void initPageElements() {
        PageFactory.initElements(driver, this);
    }

    // LOCATORS
    
    // Login button header
    @FindBy(xpath = "//a[contains(text(),'Login') or contains(@href,'account')]")
    private WebElement loginButton;

    // Phone/Email input field
    @FindBy(xpath = "//input[@class='r4VxwI' or @type='text']")
    private WebElement phoneEmailInput;

    // Request OTP button
    @FindBy(xpath = "//button[contains(text(),'Request OTP') or contains(text(),'Submit')]")
    private WebElement requestOtpBtn;

    // OTP input field
    @FindBy(xpath = "//input[@type='password' or @placeholder='Enter OTP']")
    private WebElement otpInput;

    // Verify OTP button
    @FindBy(xpath = "//button[contains(text(),'Verify') or contains(text(),'Login')]")
    private WebElement verifyOtpBtn;

    // Continue button
    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement continueBtn;

    // Error message
    @FindBy(xpath = "//span[@class='_1XiUx5']")
    private WebElement errorMessage;

    // Login frame
    @FindBy(xpath = "//iframe[@title='Login']")
    private WebElement loginFrame;

    // METHODS

    // Click login button
    public void clickLoginButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
            Thread.sleep(1000);
            
            try {
                loginButton.click();
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Normal click failed, using JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
            }
            
            System.out.println("Login button clicked");
            Thread.sleep(2000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Login button click failed: " + e.getMessage());
            throw new RuntimeException("Failed to click login button: " + e.getMessage());
        }
    }

    // Enter phone number
    public void enterPhoneNumber(String phoneNumber) {
        try {
            // Check if we need to switch to iframe
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loginFrame));
                System.out.println("Switched to login frame");
            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println("No iframe found, continuing without switch");
            }

            wait.until(ExpectedConditions.visibilityOf(phoneEmailInput));
            phoneEmailInput.clear();
            phoneEmailInput.sendKeys(phoneNumber);
            System.out.println("Phone number entered: " + phoneNumber);
            Thread.sleep(1000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Failed to enter phone number: " + e.getMessage());
            throw new RuntimeException("Failed to enter phone number: " + e.getMessage());
        }
    }

    // Click Request OTP button
    public void clickRequestOtp() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(requestOtpBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", requestOtpBtn);
            Thread.sleep(500);
            
            try {
                requestOtpBtn.click();
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Normal click failed, using JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", requestOtpBtn);
            }
            
            System.out.println("Request OTP button clicked");
            Thread.sleep(2000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Failed to click Request OTP: " + e.getMessage());
            throw new RuntimeException("Failed to click Request OTP: " + e.getMessage());
        }
    }

    // Enter OTP
    public void enterOtp(String otp) {
        try {
            wait.until(ExpectedConditions.visibilityOf(otpInput));
            otpInput.clear();
            otpInput.sendKeys(otp);
            System.out.println("OTP entered: " + otp);
            Thread.sleep(1000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Failed to enter OTP: " + e.getMessage());
            throw new RuntimeException("Failed to enter OTP: " + e.getMessage());
        }
    }

    // Click Verify OTP button
    public void clickVerifyOtp() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(verifyOtpBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyOtpBtn);
            Thread.sleep(500);
            
            try {
                verifyOtpBtn.click();
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Normal click failed, using JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", verifyOtpBtn);
            }
            
            System.out.println("Verify OTP button clicked");
            Thread.sleep(3000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Failed to click Verify OTP: " + e.getMessage());
            throw new RuntimeException("Failed to click Verify OTP: " + e.getMessage());
        }
    }

    // Click Continue button
    public void clickContinue() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueBtn);
            Thread.sleep(500);
            
            try {
                continueBtn.click();
            } catch (org.openqa.selenium.InvalidElementStateException e) {
                System.out.println("Normal click failed, using JS click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
            }
            
            System.out.println("Continue button clicked");
            Thread.sleep(2000);
        } catch (InterruptedException | org.openqa.selenium.TimeoutException e) {
            System.out.println("Continue button not found, skipping: " + e.getMessage());
        }
    }

    // Switch back from iframe
    public void switchBackFromFrame() {
        try {
            driver.switchTo().defaultContent();
            System.out.println("Switched back to main content");
        } catch (RuntimeException e) {
            System.out.println("Failed to switch back from frame: " + e.getMessage());
        }
    }

    // Get error message
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (org.openqa.selenium.TimeoutException e) {
            return "No error message";
        }
    }

    // Check if logged in
    public boolean isLoggedIn() {
        try {
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            
            // Check if login modal is gone
            try {
                wait.until(ExpectedConditions.stalenessOf(loginButton));
                System.out.println("Successfully logged in");
                return true;
            } catch (org.openqa.selenium.TimeoutException e) {
                System.out.println("Still on login page");
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println("Failed to check login status: " + e.getMessage());
            return false;
        }
    }

    // Complete login flow with phone number
    public void loginWithPhone(String phoneNumber) {
        try {
            clickLoginButton();
            Thread.sleep(2000);
            enterPhoneNumber(phoneNumber);
            Thread.sleep(1000);
            clickRequestOtp();
            System.out.println("OTP requested. Waiting for manual OTP entry...");
            System.out.println("Phone Number used: " + phoneNumber);
        } catch (InterruptedException e) {
            System.out.println("Login flow failed: " + e.getMessage());
            throw new RuntimeException("Login flow failed: " + e.getMessage());
        }
    }

    // Complete OTP verification
    public void completeOtpVerification(String otp) {
        try {
            enterOtp(otp);
            clickVerifyOtp();
            clickContinue();
            switchBackFromFrame();
            Thread.sleep(3000);
            
            if (isLoggedIn()) {
                System.out.println("Login completed successfully");
            } else {
                System.out.println("Login status unclear");
            }
        } catch (InterruptedException e) {
            System.out.println("OTP verification failed: " + e.getMessage());
            throw new RuntimeException("OTP verification failed: " + e.getMessage());
        }
    }
}

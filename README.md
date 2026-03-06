# Flipkart Selenium Automation Framework

A robust test automation framework built with Selenium WebDriver and TestNG for automating Flipkart e-commerce website testing.

## Project Overview

This project implements a Page Object Model (POM) based Selenium automation framework to test various functionalities of the Flipkart website. It includes automated test cases for home page verification, search functionality, login button visibility, and URL validation.

## Features

- **Page Object Model (POM)** - Clean and maintainable code structure
- **Selenium WebDriver 4.18.1** - Latest stable version for browser automation
- **TestNG Framework** - Advanced testing framework with parallel execution support
- **WebDriver Manager** - Automatic driver management for cross-platform compatibility
- **Configurable Settings** - Externalized configuration through properties file
- **Cross-browser Support** - Chrome, Firefox, and Edge browsers
- **Headless Mode** - Optional headless browser execution
- **Screenshot Capture** - Automatic screenshots on test failure
- **Flexible Locators** - Resilient XPath selectors with fallback options
- **Error Handling** - Comprehensive exception handling and logging

## Project Structure

```
Flipkart/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   └── BaseClass.java             # Base class with WebDriver setup
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java          # Configuration properties reader
│   │   │   └── pages/
│   │   │       ├── HomePage.java              # Page Object for Home Page
│   │   │       ├── LoginPage.java             # Page Object for Login Page
│   │   │       ├── ProductPage.java           # Page Object for Product Page
│   │   │       └── CheckoutPage.java          # Page Object for Checkout Page
│   │   └── resources/
│   │       └── config.properties              # Configuration file
│   └── test/
│       └── java/
│           └── tests/
│               ├── Hometest.java              # Test cases for home page
│               ├── LoginTest.java             # Test cases for login functionality
│               └── ProductTest.java           # Test cases for product & checkout
├── target/                                    # Build output directory
├── pom.xml                                    # Maven configuration & dependencies
├── testng.xml                                 # TestNG suite configuration
└── README.md                                  # This file
```

## Prerequisites

- **Java 11 or higher** - JDK installation required
- **Maven 3.6+** - Build automation tool
- **Chrome/Firefox/Edge Browser** - For web automation
- **Git** - Version control (optional)

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/hj8836430-prog/Flipkart.git
cd Flipkart
```

### 2. Install Dependencies

Maven will automatically download all dependencies from `pom.xml`:

```bash
mvn clean install
```

## Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=Hometest
```

### Run with TestNG Suite

```bash
mvn clean test -Dsuitexml=testng.xml
```

### Run in Quiet Mode (Minimal Output)

```bash
mvn clean test -q
```

## Test Cases

**Total: 21 Test Cases** ✅

### Hometest.java (4 Test Cases)

1. **verifyTitle()**
   - Verifies that the page title contains "Flipkart"
   - Expected: Page loads with correct title

2. **verifySearch()**
   - Tests product search functionality (searches for "iPhone")
   - Expected: Search results page loads successfully

3. **verifyLogo()**
   - Verifies Flipkart logo visibility and URL
   - Expected: Logo visible and current URL contains "flipkart"

4. **verifyLoginButton()**
   - Tests login button visibility on home page
   - Expected: Login button is clickable and visible

### LoginTest.java (7 Test Cases)

1. **testLoginButtonVisibility()** - Verify login button is accessible
2. **testLoginModalAppears()** - Verify login modal opens on button click
3. **testEnterPhoneAndRequestOtp()** - Enter phone number and request OTP
4. **testLoginWithPhoneNumber()** - Parameterized test with multiple phone numbers
5. **testLoginFlowSteps()** - Test complete login flow steps
6. **testPhoneNumberValidation()** - Validate phone number input
7. **testOtpInput()** - Test OTP input functionality

### ProductTest.java (10 Test Cases)

1. **testSearchProduct()** - Search for a product
2. **testSelectFirstProduct()** - Select first product from search results
3. **testAddToCart()** - Add product to cart
4. **testBuyNow()** - Buy product immediately
5. **testProductDetails()** - Verify product details
6. **testCheckoutFlow()** - Complete checkout process
7. **testPlaceOrder()** - Place order successfully
8. **testPaymentFlow()** - Test payment processing
9. **testOrderConfirmation()** - Verify order confirmation
10. **testContinueShopping()** - Continue shopping after purchase

## Key Classes

### BaseClass.java
- Initializes WebDriver
- Configures browser options (headless, notifications, popups)
- Sets implicit and page load timeouts
- Captures screenshots
- Manages driver cleanup

### ConfigReader.java
- Reads configuration from properties file
- Provides centralized access to all config parameters
- Methods: `getBrowser()`, `getBaseUrl()`, `getPhoneNumber()`, `getExplicitWait()`, etc.

### Page Objects
- **HomePage.java** - Home page locators and methods (closeLoginPopup, searchProduct, verifyLogo)
- **LoginPage.java** - Login page locators and methods (clickLoginButton, enterPhoneNumber, clickRequestOtp)
- **ProductPage.java** - Product page locators and methods (selectProduct, addToCart, buyNow)
- **CheckoutPage.java** - Checkout page locators and methods (placeOrder, continueShop)

## Usage Example

```java
@Test
public void testLogin() {
    HomePage homePage = new HomePage(driver);
    LoginPage loginPage = new LoginPage(driver);
    
    // Navigate to home
    driver.get(ConfigReader.getBaseUrl());
    
    // Close popup if exists
    homePage.closeLoginPopup();
    
    // Click login button
    loginPage.clickLoginButton();
    
    // Enter phone number from config
    loginPage.enterPhoneNumber(ConfigReader.getPhoneNumber());
    
    // Request OTP
    loginPage.clickRequestOtp();
}
```

## Dependencies

The project includes the following Maven dependencies:

```xml
<!-- Selenium WebDriver -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.18.1</version>
</dependency>

<!-- TestNG Framework -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.9.0</version>
</dependency>

<!-- WebDriver Manager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.7.0</version>
</dependency>

<!-- Commons IO -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.15.1</version>
</dependency>
```

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Support

For issues and questions:
- Open an issue on GitHub
- Check existing documentation
- Review test logs in `target/surefire-reports/`

## Changelog

### Version 1.0.0 (March 6, 2026)
- Initial project setup
- Implemented Page Object Model architecture
- Added 4 core test cases
- Configured Maven and TestNG
- Added cross-browser support
- Implemented error handling and logging
- Removed emoji icons for clean code
- Fixed XPath locators for reliability
- Added screenshot capture functionality
- Pushed to GitHub repository

## Author

Created as a Selenium test automation framework for Flipkart e-commerce website.

## Acknowledgments

- Selenium WebDriver documentation
- TestNG framework
- Maven community
- WebDriver Manager for driver management

---

**Last Updated:** March 6, 2026  
**Repository:** https://github.com/hj8836430-prog/Flipkart

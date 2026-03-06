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

### 3. Configure Settings

Edit `src/main/resources/config.properties`:

```properties
browser=chrome                          # Browser choice: chrome, firefox, edge
headless=false                          # Run in headless mode (true/false)
base.url=https://www.flipkart.com      # Target URL
username=your_email@gmail.com           # Flipkart account email
password=your_password                  # Flipkart account password
phone.number=9782017696                 # Mobile number for login
implicit.wait=10                        # Implicit wait in seconds
explicit.wait=15                        # Explicit wait in seconds
page.load.timeout=30                    # Page load timeout in seconds
screenshot.path=./screenshots/          # Screenshot storage path
report.path=./reports/ExtentReport.html # Test report path
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

### 1. verifyTitle()
- **Description:** Verifies that the page title contains "Flipkart"
- **Expected:** Page loads with correct title

### 2. verifySearch()
- **Description:** Tests product search functionality
- **Steps:** Enter "iPhone" in search box and submit
- **Expected:** Search results page loads successfully

### 3. verifyLogo()
- **Description:** Verifies Flipkart logo visibility and URL
- **Expected:** Logo visible and current URL contains "flipkart"

### 4. verifyLoginButton()
- **Description:** Tests login button visibility on home page
- **Expected:** Login button is clickable and visible

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

## Troubleshooting

### 1. WebDriver Not Found
```bash
mvn clean install -U  # Update snapshots
```

### 2. Password/Credentials Issues
- Update `config.properties` with valid credentials
- Ensure special characters are properly escaped

### 3. Element Not Found
- Check XPath locators in Page Object files
- Verify element is visible before interaction
- Increase wait times in config.properties

## Recent Updates (v1.0)

✅ Fixed all compilation errors
✅ Integrated phone number configuration
✅ Updated LoginTest to use ConfigReader.getPhoneNumber()
✅ Created ProductTest and ProductPage classes
✅ Implemented CheckoutPage Page Object
✅ Enhanced error handling in page objects

## Contributing

1. Create a new branch: `git checkout -b feature/your-feature`
2. Commit changes: `git commit -m "Add new feature"`
3. Push to remote: `git push origin feature/your-feature`
4. Create a Pull Request

## Contact

For issues or questions, please create an issue on the GitHub repository.

---

**Last Updated:** March 6, 2026  
**Maintainer:** [Your Name]  
**License:** MIT

- Handles IOException with proper error messaging

### HomePage.java
- Contains all home page locators using `@FindBy` annotation
- Implements Page Object Model pattern
- Methods for interacting with page elements
- Includes fallback mechanisms for element handling

### Hometest.java
- Contains all test methods
- Uses TestNG annotations for test lifecycle
- Implements proper assertions and error handling

## Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Selenium Java | 4.18.1 | Web browser automation |
| TestNG | 7.9.0 | Test framework |
| WebDriver Manager | 5.7.0 | Driver management |
| Commons IO | 2.15.1 | File operations |

## Configuration Details

### Browser Options
- `--headless` - Run browser without GUI
- `--start-maximized` - Start browser in fullscreen
- `--disable-notifications` - Disable browser notifications
- `--disable-popup-blocking` - Disable popup blocker

### Wait Strategies
- **Implicit Wait:** Applied globally to all element searches
- **Explicit Wait:** Applied to specific elements with expected conditions
- **Page Load Timeout:** Maximum time for page to load

## Advanced Usage

### Custom Test Execution Order

Edit `testng.xml` to customize test execution:

```xml
<suite name="Flipkart Test Suite">
    <test name="Home Page Tests">
        <classes>
            <class name="tests.Hometest">
                <methods>
                    <include name="verifyTitle"/>
                    <include name="verifySearch"/>
                </methods>
            </class>
        </classes>
    </test>
</suite>
```

### Parallel Execution

Add to `testng.xml`:

```xml
<suite name="Flipkart Test Suite" parallel="methods" thread-count="4">
```

### Screenshot on Failure

Screenshots are automatically saved to `./screenshots/` directory with timestamp.

## Test Reports

After test execution, reports are generated in:
- **TestNG Reports:** `target/surefire-reports/`
- **HTML Report:** `target/surefire-reports/index.html`
- **Emailable Report:** `target/surefire-reports/emailable-report.html`

## CI/CD Integration

### GitHub Actions Example

Create `.github/workflows/tests.yml`:

```yaml
name: Automated Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
```

## Best Practices

1. **Always start fresh:** Use `mvn clean` before running tests
2. **Keep page objects updated:** Update locators when website changes
3. **Use explicit waits:** Prefer explicit wait over implicit for specific elements
4. **Capture screenshots:** Enable screenshot capture for debugging
5. **Separate data:** Keep test data in config.properties
6. **Error handling:** Use try-catch blocks appropriately
7. **Meaningful assertions:** Use descriptive assertion messages

## Git Commands

### Clone Repository
```bash
git clone https://github.com/hj8836430-prog/Flipkart.git
```

### View Commit History
```bash
git log --oneline
```

### Create Feature Branch
```bash
git checkout -b feature-name
```

### Push Changes
```bash
git add .
git commit -m "Descriptive message"
git push origin main
```

## Maintenance

### Update Dependencies

```bash
mvn dependency:update-snapshots
mvn versions:display-dependency-updates
```

### Clean Build Artifacts

```bash
mvn clean
```

### Run Full Build

```bash
mvn clean install
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

package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;

public class Hometest extends BaseClass {

    HomePage homePage;

    @BeforeClass
    public void pageSetup() {
        // ❌ super.setUp() mat likho — TestNG khud chalata hai
        homePage = new HomePage(driver);   // driver BaseClass se aata hai
        homePage.closeLoginPopup();        // Popup band karo
    }

    // ✅ Test 1 — Title verify karo
    @Test
    public void verifyTitle() {
        String title = homePage.getHomePageTitle();
        System.out.println("Page Title: " + title);
        Assert.assertTrue(title.contains("Flipkart"), 
            "❌ Title mein Flipkart nahi hai!");
    }

    // ✅ Test 2 — Search verify karo
    @Test
    public void verifySearch() {
        homePage.searchProduct("iPhone 15");
        System.out.println("✅ Search complete hua");
    }

    // ✅ Test 3 — Logo visible hai?
    @Test
    public void verifyLogo() {
        boolean isVisible = homePage.isLogoVisible();
        Assert.assertTrue(isVisible, "❌ Logo visible nahi hai!");
    }

    // ✅ Test 4 — Login Button visible hai?
    @Test
    public void verifyLoginButton() {
        boolean isVisible = homePage.isLoginButtonVisible();
        Assert.assertTrue(isVisible, "❌ Login button nahi dikh raha!");
    }
}

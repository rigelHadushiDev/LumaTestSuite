package core.tests;

import core.globals.Globals;
import core.pages.CheckPageFiltersPage;
import core.pages.RegisterPage;
import core.pages.SignInPage;
import core.pages.WishListPage;
import core.utilities.BaseInformation;
import core.utilities.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class UserTests {

    RegisterPage registerPage = new RegisterPage();
    SignInPage signInPage = new SignInPage();
    CheckPageFiltersPage checkPageFiltersPage = new CheckPageFiltersPage();
WishListPage wishListPage = new WishListPage();

    @AfterTest
    public void quit() {
        BaseInformation.quit();
    }

    @Test(priority = 1)
    public void testRegister() {
        registerPage.getUrl(Globals.baseUrl);
        registerPage.clickRegisterButton();
        registerPage.setFirstName("Test");
        registerPage.setLastName("TestLastName");
        registerPage.setEmail("testEmail");
        registerPage.setPassword();
        registerPage.setConfirmPassword();
        registerPage.clickRegisterButtonForm();
        Assert.assertTrue(registerPage.checkRegister());
        Assert.assertTrue(registerPage.isSuccessMessageDisplayed());
        registerPage.signOut();
        WaitUtils.waitFor(3000);
    }

    @Test(priority = 2, dependsOnMethods = "testRegister")
    public void testSignIn() {
        signInPage.getUrl(Globals.baseUrl);
        signInPage.clickSignInLink();

        signInPage.setEmail(Globals.email);
        signInPage.setPassword(Globals.password);
        signInPage.clickSignInButton();

        String expectedWelcomeText = "Welcome, " + Globals.firstName + " " + Globals.lastName + "!";

        String actualWelcomeText = signInPage.getWelcomeMessageText();

        Assert.assertEquals(actualWelcomeText, expectedWelcomeText,
                "The welcome message did not match the expected text.");
        signInPage.clickSignOut();
        WaitUtils.waitFor(3000);
    }

    @Test(priority = 3, dependsOnMethods = "testSignIn")
    public void checkPageFilters() {

        signInPage.getUrl(Globals.baseUrl);
        signInPage.clickSignInLink();

        signInPage.setEmail(Globals.email);
        signInPage.setPassword(Globals.password);
        signInPage.clickSignInButton();
        checkPageFiltersPage.navigateToJacketsPage();
        checkPageFiltersPage.selectRedColorFilter();

        Assert.assertTrue(checkPageFiltersPage.verifyAllProductsHaveRedColorSelected(),
                "Not all products have the red swatch selected.");

        checkPageFiltersPage.selectWantedPriceFilter();
        checkPageFiltersPage.verifyAllProductsHaveCorrectPriceRangeSelected();
        checkPageFiltersPage.verifyAllProductPricesInRange();
        WaitUtils.waitFor(3000);

    }

    @Test(priority = 4, dependsOnMethods = "checkPageFilters")
    public void wishListTest() {

//        signInPage.getUrl(Globals.baseUrl);
//        signInPage.clickSignInLink();
//
//        signInPage.setEmail(Globals.email);
//        signInPage.setPassword(Globals.password);
//        signInPage.clickSignInButton();


        wishListPage.removePriceFilter();
        wishListPage.checkThatCountIsIncreased();
        wishListPage.clickOnProductItemAndWait();
        wishListPage.clickOnAddToWishListButtonAndWait();
        WaitUtils.waitFor(3000);

    }
}

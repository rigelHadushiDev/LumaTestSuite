package core.tests;

import core.globals.Globals;
import core.pages.*;
import core.utilities.BaseInformation;
import core.utilities.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import core.listeners.ScreenshotListener;

@Listeners(ScreenshotListener.class)
public class EcommerceTests {

    RegisterPage registerPage = new RegisterPage();
    SignInPage signInPage = new SignInPage();
    CheckPageFiltersPage checkPageFiltersPage = new CheckPageFiltersPage();
    WishListPage wishListPage = new WishListPage();
    ShoppingCartTestPage shoppingCartTestPage = new ShoppingCartTestPage();
    EmptyShoppingCartPage emptyShoppingCartPage = new EmptyShoppingCartPage();
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

        signInPage.checkEqualityOfMessages(actualWelcomeText,expectedWelcomeText);
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

        checkPageFiltersPage.verifyAllProductsHaveRedColorSelected();

        checkPageFiltersPage.selectWantedPriceFilter();
        checkPageFiltersPage.verifyThatOnlyTwoProductsAreDisplayed(2);
        checkPageFiltersPage.verifyAllProductPricesInRange();
        WaitUtils.waitFor(3000);

    }

    @Test(priority = 4, dependsOnMethods = "checkPageFilters")
    public void wishListTest() {

        wishListPage.removePriceFilter();
        wishListPage.checkThatCountIsIncreased();
        wishListPage.clickOnProductItemAndWait(0);
        wishListPage.clickOnAddToWishListButtonAndWait();
        wishListPage.isProductSuccessfulWishListMessageDisplayed(0);
        // check successcful message
        checkPageFiltersPage.navigateToJacketsPage();
        checkPageFiltersPage.selectRedColorFilter();
        wishListPage.clickOnProductItemAndWait(1);
        wishListPage.clickRedOptionToJackets();
        wishListPage.clickOnAddToWishListButtonAndWait();
        wishListPage.isProductSuccessfulWishListMessageDisplayed(1);


        wishListPage.verifyTwoItemsAreDisplayed(2);
        WaitUtils.waitFor(3000);

    }

    @Test(priority = 5, dependsOnMethods = "checkPageFilters")
    public void shoppingCartTest() {

        checkPageFiltersPage.navigateToJacketsPage();
        checkPageFiltersPage.selectRedColorFilter();
        checkPageFiltersPage.selectWantedPriceFilter();
        shoppingCartTestPage.clickOnProduct1Size();
        shoppingCartTestPage.clickOnProduct2Size();
        shoppingCartTestPage.hoverOverFirstCardAndClickAddToCart();
        shoppingCartTestPage.verifyFirstProductIsAddedToCart();
        shoppingCartTestPage.hoverOverSecondCardAndClickAddToCart();
        shoppingCartTestPage.verifySecondProductIsAddedToCart();
        shoppingCartTestPage.clickOnShoppingCartLink();
        shoppingCartTestPage.verifyThatWeHaveNavigatedToShoppingCartPage();
        shoppingCartTestPage.verifyTotalShoppingSum();
        WaitUtils.waitFor(3000);
    }

    @Test(priority = 6, dependsOnMethods = "shoppingCartTest")
    public void emptyShoppingCartTest() {
        emptyShoppingCartPage.removeTheSecondShoppingItem();
        emptyShoppingCartPage.verifyThatTheNumberOfTheItemsDecreases();
        emptyShoppingCartPage.removeTheFirstShoppingItem();
        emptyShoppingCartPage.verifyThatTheNumberOfTheItemsDecreases();
        emptyShoppingCartPage.verifyThatTheEmptyShoppingCardIsDisplayed();
        emptyShoppingCartPage.closeBrowser();

    }

    }

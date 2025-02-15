package core.tests;

import core.globals.Globals;
import core.pages.RegisterPage;
import core.utilities.BaseInformation;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestRegisterPage {
    RegisterPage registerPage = new RegisterPage();
    @AfterTest
    public void quit(){
        BaseInformation.quit();
    }
    @Test
    public void test(){
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
    }
}

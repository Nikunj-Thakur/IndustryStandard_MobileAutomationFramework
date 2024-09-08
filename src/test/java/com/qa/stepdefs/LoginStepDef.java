package com.qa.stepdefs;

import com.qa.pages.LoginPage;
import com.qa.pages.MenuPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {
    @When("I enter username as {string}")
    public void iEnterUsernameAs(String username) {
        new LoginPage().enterUserName(username);
    }
    @When("I enter password as {string}")
    public void iEnterPasswordAs(String password) {
        new LoginPage().enterPassword(password);
    }
    @When("I tap on Login button")
    public void iTapOnLoginButton() {
        new LoginPage().pressLoginBtn();
    }
    @Then("Login should fail with an error message {string}")
    public void loginShouldFailWithAnErrorMessage(String err) {
        Assert.assertEquals(new LoginPage().getErrTxt(),err);
    }

    @Then("I should see Products screen with {string}")
    public void iShouldSeeProductsScreenWith(String title) {
        Assert.assertEquals(new ProductsPage().getTitle(),title);
    }

    @And("I should be able to log out of the app")
    public void iShouldBeAbleToLogoutOfTheApp(){
        new MenuPage().pressSettingsBtn();
        new SettingsPage().pressLogoutBtn();
    }
}

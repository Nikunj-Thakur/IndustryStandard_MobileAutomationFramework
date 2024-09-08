package com.qa.stepdefs;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class ProductsStepDef {

    @Given("I am logged in to the app")
    public void iAmLoggedInToTheApp() throws InterruptedException {
        new LoginPage().login("standard_user","secret_sauce");
    }
    @Given("I tap on product title {string}")
    public void iTapOnProductTitle(String title) throws Exception {
        new ProductsPage().pressProductTitle(title);
    }
    @Then("I should be navigated to product details screen with title {string}, price {string} and description {string}")
    public void iShouldBeNavigatedToProductDetailsScreenWithTitlePriceAndDescription(String title, String price, String description) throws Exception {
        ProductDetailsPage productDetailsPage=new ProductDetailsPage();
        Boolean titleCheck=productDetailsPage.getTitle().equalsIgnoreCase(title);
        Boolean descriptionCheck=productDetailsPage.getDesc().equalsIgnoreCase(description);
        Boolean priceCheck=productDetailsPage.getPrice().equalsIgnoreCase(price);
        Assert.assertTrue("titleCheck="+titleCheck+"priceCheck="+priceCheck+"descriptionCheck="+descriptionCheck,
                titleCheck && priceCheck && descriptionCheck);
    }

    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) {

    }
}

package tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverManager;

public class CheckoutStepDef {
    WebDriver driver;
    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();
    CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    public CheckoutStepDef() {
        this.driver = DriverManager.getDriver(); // properly fetch WebDriver
    }

    @And("user click cart icon on homepage")
    public void userClickCartIconOnHomepage() {
        homePage.ClickCartIcon();
    }

    @And("user click checkout button on cart page")
    public void userClickCheckoutButtonOnCartPage() {
        cartPage.ClickBtnCheckOut();
    }

    @And("user will redirect to checkout information page")
    public void userWillRedirectToCheckoutInformationPage() {
        checkoutInformationPage.ValidateOnCheckoutInformationPage();
    }

    @And("user input first name field with {string}")
    public void userInputFirstNameFieldWith(String blankFirstName) {
        driver.findElement(checkoutInformationPage.FirstNameField).sendKeys(blankFirstName);
    }

    @And("user input last name field with {string}")
    public void userInputLastNameFieldWith(String blankLastName) {
        driver.findElement(checkoutInformationPage.LastNameField).sendKeys(blankLastName);
    }

    @And("user input postal code field with {string}")
    public void userInputPostalCodeFieldWith(String blankPostalCode) {
        driver.findElement(checkoutInformationPage.PostalCodeField).sendKeys(blankPostalCode);
    }

    @And("user click continue button on checkout information page")
    public void userClickContinueButtonOnCheckoutInformationPage() {
        driver.findElement(checkoutInformationPage.BtnContinue).click();
    }

    @And("user will redirect to checkout overview page")
    public void userWillRedirectToCheckoutOverviewPage() {
        checkoutOverviewPage.ValidateOnCheckoutOverviewPage();
    }

    @And("user click finish button on checkout overview page")
    public void userClickFinishButtonOnCheckoutOverviewPage() {
        driver.findElement(checkoutOverviewPage.BtnFinish).click();
    }

    @Then("user is on the checkout complete page")
    public void userIsOnTheCheckoutCompletePage() {
        checkoutCompletePage.ValidateOnCheckoutCompletePage();
    }
}

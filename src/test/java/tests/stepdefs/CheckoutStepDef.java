package tests.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

public class CheckoutStepDef {
    WebDriver driver;
    HomePage homePage = new HomePage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

    @Before
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
    }

    @After
    public void afterTest(){
        driver.close();
    }

    @And("user click cart icon on homepage")
    public void userClickCartIconOnHomepage() {
        homePage.ClickCartIcon();
    }

    //@And("user will redirect to cart page")
   // public void userWillRedirectToCartPage() {
    //    cartPage.ValidateItemName();
    //}

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
        checkoutOverviewPage.ValidateOnItemName();
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

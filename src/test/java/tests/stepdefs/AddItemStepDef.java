package tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;
import static org.junit.Assert.assertEquals;

public class AddItemStepDef {
    WebDriver driver;
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();

    public AddItemStepDef() {
        this.driver = DriverManager.getDriver(); // properly fetch WebDriver
    }

    @And("user login with username {string} and password {string}")
    public void userLoginWithUsernameAndPassword(String username, String password) {
        driver.findElement(loginPage.UsernameField).sendKeys(username);
        driver.findElement(loginPage.PasswordField).sendKeys(password);
        driver.findElement(loginPage.LoginButton).click();
    }

    @When("user add item to the cart")
    public void userAddItemToTheCart() {
        homePage.ClickBtnAddToCart();
    }

    @Then("the cart icon should show {string}")
    public void theCartIconShouldShow(String expectedCount) {
        String actualCount = cartPage.GetCartShowBadge();
        assertEquals("Cart badge tidak sesuai", expectedCount, actualCount);
    }

    @And("user click on cart icon")
    public void userClickOnCartIcon() {
        homePage.ClickCartIcon();
    }

    @And("user will redirect to cart page")
    public void userWillSeeItemInTheCartPage() {
        cartPage.ValidateItemName();
    }
}

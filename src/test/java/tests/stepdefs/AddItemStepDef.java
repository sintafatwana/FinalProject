package tests.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddItemStepDef {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;

    @Before
    public void BeforeTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless=new");
        //chromeOptions.addArguments("--disable-dev-shm-usage");
        //chromeOptions.addArguments("--no-sandbox");

        // Disable Chrome password manager popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        chromeOptions.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @After
    public void AfterTest(){
        driver.quit();
    }

    @Given("user open the login page")
    public void userOpenTheLoginPage() {
        driver.get("https://www.saucedemo.com/");
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

    @Then("user will redirect to cart page")
    public void userWillSeeItemInTheCartPage() {
        cartPage.ValidateItemName();
    }
}

package tests.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginStepDef {
    WebDriver driver;

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

    @Given("user in on login page")
    public void userInOnLoginPage() {
        driver.get("https://www.saucedemo.com/v1/");
    }

    @When("user input email text box with {string}")
    public void userInputEmailTextBoxWith(String username) {
        By UsernameField = By.id("user-name");
        driver.findElement(UsernameField).sendKeys(username);
    }

    @And("user input password text box with {string}")
    public void userInputPasswordTextBoxWith(String password) {
        By PasswordField = By.id("password");
        driver.findElement(PasswordField).sendKeys(password);
    }

    @And("user click login button")
    public void userClickLoginButton() {
        By LoginButton = By.id("login-button");
        driver.findElement(LoginButton).click();
    }

    @Then("user will redirect to homepage")
    public void userWillRedirectToHomepage() {
        By ProductTitle = By.id("item_0_title_link");
        WebElement productElement = driver.findElement(ProductTitle);
        assertTrue(productElement.isDisplayed());
        assertEquals("Sauce Labs Bike Light", productElement.getText());
    }

    @Then("user will see error message {string}")
    public void userWillSeeErrorMessage(String ErrorMessage) {
        assertTrue(driver.getPageSource().contains(ErrorMessage));
    }

}

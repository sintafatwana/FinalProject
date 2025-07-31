package tests.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080)); // penting untuk headless
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void afterTest(){
        driver.quit();
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
        HomePage homePage = new HomePage(driver);
        homePage.validateOnHomePage();
        //By ProductTitle = By.id("item_0_title_link");
        //WebElement productElement = driver.findElement(ProductTitle);
        //assertTrue(productElement.isDisplayed());
        //assertEquals("Sauce Labs Bike Light", productElement.getText());
    }

    @Then("user will see error message {string}")
    public void userWillSeeErrorMessage(String ErrorMessage) {
        assertTrue(driver.getPageSource().contains(ErrorMessage));
    }

}

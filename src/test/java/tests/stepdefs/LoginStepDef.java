package tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;
import static org.junit.Assert.assertTrue;

public class LoginStepDef {
    WebDriver driver;
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    public LoginStepDef() {
        this.driver = DriverManager.getDriver();// properly fetch WebDriver
    }

    @When("user input email text box with {string}")
    public void userInputEmailTextBoxWith(String username) {
        loginPage.InputUsername(username);
    }

    @And("user input password text box with {string}")
    public void userInputPasswordTextBoxWith(String password) {
        loginPage.InputPassword(password);
    }

    @And("user click login button")
    public void userClickLoginButton() {
        loginPage.ClickLoginBtn();
    }

    @Then("user will redirect to homepage")
    public void userWillRedirectToHomepage() {
        homePage.validateOnHomePage();
    }

    @Then("user will see error message {string}")
    public void userWillSeeErrorMessage(String ErrorMessage) {
        assertTrue(driver.getPageSource().contains(ErrorMessage));
    }

}

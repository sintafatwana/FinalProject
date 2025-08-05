package tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.DetailItemPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class RemoveItemStepDef {
    WebDriver driver;
    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();
    DetailItemPage detailItemPage = new DetailItemPage();
    LoginPage loginPage = new LoginPage();

    public RemoveItemStepDef() {
        this.driver = DriverManager.getDriver(); // properly fetch WebDriver
    }

    @Given("user open login page")
    public void userOpenLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @And("user login with username and password")
    public void userLoginWithUsernameAndPassword() {
        loginPage.InputUsername("standard_user");
        loginPage.InputPassword("secret_sauce");
        loginPage.ClickLoginBtn();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    @And("user click remove button on home page")
    public void userClickRemoveButtonOnHomePage() {
        homePage.ClickRemoveButtonOnHomePage();
    }

    @Then("remove button should change to an add to cart button")
    public void removeButtonShouldChangeToAnAddToCartButton() {
        homePage.ValidateOnAddToCartBtn();
    }

    @And("user click cart icon")
    public void userClickCartIcon() {
        homePage.ClickCartIcon();
    }

    @And("user click remove button on cart page")
    public void userClickRemoveButtonOnCartPage() {
        cartPage.ClickBtnRemove();
    }

    @Then("item should disappear on the cart page")
    public void itemShouldDisappearOnTheCartPage() {
        cartPage.ValidateRemoveBerhasil();
    }

    @And("user click image item on home page")
    public void userClickImageItemOnHomePage() {
        homePage.ClickItemImageOnHomePage();
    }

    @And("user will redirect to detail item page")
    public void userWillRedirectToDetailItemPage() {
        detailItemPage.ValidateItemName();
    }

    @And("user click remove button on detail item page")
    public void userClickRemoveButtonOnDetailItemPage() {
        detailItemPage.ClickRemoveBtn();
    }

    @Then("the remove button should change to an add to cart button on detail item page")
    public void theRemoveButtonShouldChangeToAnAddToCartButtonOnDetailItemPage() {
        detailItemPage.ValidateAddToCartBtn();
    }

    @And("user will see remove button")
    public void userWillSeeRemoveButton() {
        homePage.ValidateOnRemoveButton();
    }

    @And("user click button show alert")
    public void userClickButtonShowAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals(text, "OK");
        alert.accept();
    }
}

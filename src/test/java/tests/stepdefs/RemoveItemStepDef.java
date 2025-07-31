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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.DetailItemPage;
import pages.HomePage;
import pages.LoginPage;

import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RemoveItemStepDef {
    private static final Logger log = LoggerFactory.getLogger(RemoveItemStepDef.class);
    WebDriver driver;
    HomePage homePage;
    CartPage cartPage;
    DetailItemPage detailItemPage;
    LoginPage loginPage;

    @Before
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();

        // Disable Chrome password manager popups
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        detailItemPage = new DetailItemPage(driver);
        loginPage = new LoginPage(driver);
    }

    @After
    public void afterTest(){
        driver.close();
    }

    @Given("user open login page")
    public void userOpenLoginPage() {
        driver.get("https://www.saucedemo.com/");
        log.info("berhasil mengakses web saucedemo");
    }

    @And("user login with username and password")
    public void userLoginWithUsernameAndPassword() throws InterruptedException {
        loginPage.InputUsername("standard_user");
        loginPage.InputPassword("secret_sauce");
        loginPage.ClickLoginBtn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
        homePage.validateOnHomePage();
        Thread.sleep(2000);
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
}

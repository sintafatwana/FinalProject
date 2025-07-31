package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class AddItemTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
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
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddItemToCart() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            CartPage cartPage = new CartPage(driver);
            HomePage homePage = new HomePage(driver);
            homePage.ClickBtnAddToCart();
            homePage.ClickCartIcon();
            cartPage.ValidateItemQuantity();
            cartPage.ValidateItemName();
            cartPage.ValidateItemDesc();
            cartPage.ValidateItemPrice();
    }

}

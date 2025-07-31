package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    WebDriver driver;

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
    public void testValidLogin() {
        WebDriver driver = WebDriverManager.chromiumdriver().create();
        LoginPage loginPage = new LoginPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        loginPage.InputUsername("standard_user");
        loginPage.InputPassword("secret_sauce");
        loginPage.ClickLoginBtn();
        }

        @Test
        public void testInvalidUsernameLogin() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("abdul");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();
        }

        @Test
        public void testWrongPasswordLogin() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("123");
            loginPage.ClickLoginBtn();
        }

        @Test
        public void testBlankUsernameLogin() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();
        }

        @Test
        public void testBlankPasswordLogin() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("");
            loginPage.ClickLoginBtn();
        }
}

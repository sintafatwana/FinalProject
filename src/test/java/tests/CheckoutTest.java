    package tests;

    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import pages.*;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.NoSuchElementException;

    public class CheckoutTest {
        private WebDriver driver;

        @BeforeEach
        public void setUp() {
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
        public void allFieldBlank() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            CartPage cartPage = new CartPage(driver);
            HomePage homePage = new HomePage(driver);
            homePage.ClickBtnAddToCart();
            homePage.validateOnHomePage();
            homePage.ClickCartIcon();
            cartPage.ValidateItemName();
            cartPage.ClickBtnCheckOut();

            CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
            checkoutInformationPage.ValidateOnCheckoutInformationPage();
            checkoutInformationPage.InputFirstNameField("");
            checkoutInformationPage.InputLastNameField("");
            checkoutInformationPage.InputPostalCodeField("");
            checkoutInformationPage.ClickBtnContinue();
        }

        @Test
        public void firstNameFieldBlank() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            HomePage homePage = new HomePage(driver);
            CartPage cartPage = new CartPage(driver);
            homePage.ClickBtnAddToCart();
            homePage.ClickCartIcon();
            cartPage.ClickBtnCheckOut();

            CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
            checkoutInformationPage.ValidateOnCheckoutInformationPage();
            checkoutInformationPage.InputFirstNameField("");
            checkoutInformationPage.InputLastNameField("Fatwana");
            checkoutInformationPage.InputPostalCodeField("60234");
            checkoutInformationPage.ClickBtnContinue();
            checkoutInformationPage.ValidateBlankFirstNameField();
        }

        @Test
        public void lastNameFieldBlank() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            HomePage homePage = new HomePage(driver);
            homePage.validateOnHomePage();
            CartPage cartPage = new CartPage(driver);
            homePage.ClickBtnAddToCart();
            homePage.ClickCartIcon();
            cartPage.ValidateItemName();
            cartPage.ClickBtnCheckOut();

            CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
            checkoutInformationPage.ValidateOnCheckoutInformationPage();
            checkoutInformationPage.InputFirstNameField("Sinta");
            checkoutInformationPage.InputLastNameField("");
            checkoutInformationPage.InputPostalCodeField("60234");
            checkoutInformationPage.ClickBtnContinue();
            checkoutInformationPage.ValidateBlankLastNameField();
        }

        @Test
        public void postalCodeFieldBlank() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            HomePage homePage = new HomePage(driver);
            homePage.validateOnHomePage();
            CartPage cartPage = new CartPage(driver);
            homePage.ClickBtnAddToCart();
            homePage.ClickCartIcon();
            cartPage.ValidateItemName();
            cartPage.ClickBtnCheckOut();

            CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
            checkoutInformationPage.ValidateOnCheckoutInformationPage();
            checkoutInformationPage.InputFirstNameField("Sinta");
            checkoutInformationPage.InputLastNameField("Fatwana");
            checkoutInformationPage.InputPostalCodeField("");
            checkoutInformationPage.ClickBtnContinue();
            checkoutInformationPage.ValidateBlankPostalCodeField();
            //Thread.sleep(2000);
        }

        @Test
        public void checkoutCancel() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            HomePage homePage = new HomePage(driver);
            homePage.validateOnHomePage();
            CartPage cartPage = new CartPage(driver);
            homePage.ClickBtnAddToCart();
            homePage.ClickCartIcon();
            cartPage.ValidateItemName();
            cartPage.ClickBtnCheckOut();

            CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
            checkoutInformationPage.ValidateOnCheckoutInformationPage();
            checkoutInformationPage.InputFirstNameField("Sinta");
            checkoutInformationPage.InputLastNameField("Fatwana");
            checkoutInformationPage.InputPostalCodeField("60234");
            checkoutInformationPage.ClickBtnContinue();

            CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
            checkoutOverviewPage.ValidateOnItemDesc();
            checkoutOverviewPage.ValidateOnItemName();
            checkoutOverviewPage.ValidateOnItemPrice();
            checkoutOverviewPage.ValidateOnItemTotalLabel();
            checkoutOverviewPage.ValidateOnSummaryQTY();
            checkoutOverviewPage.ValidateOnTaxLabel();
            checkoutOverviewPage.ValidateOnValueLabel();
            checkoutOverviewPage.ClickBtnCancel();
            homePage.validateOnHomePage();
        }

        @Test
        public void checkoutSuccess() {
            LoginPage loginPage = new LoginPage(driver);
            driver.get("https://www.saucedemo.com/");

            loginPage.InputUsername("standard_user");
            loginPage.InputPassword("secret_sauce");
            loginPage.ClickLoginBtn();

            HomePage homePage = new HomePage(driver);
            homePage.validateOnHomePage();
            CartPage cartPage = new CartPage(driver);
            homePage.ClickBtnAddToCart();
            homePage.ClickCartIcon();
            cartPage.ValidateItemName();
            cartPage.ClickBtnCheckOut();

            CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
            checkoutInformationPage.ValidateOnCheckoutInformationPage();
            checkoutInformationPage.InputFirstNameField("Sinta");
            checkoutInformationPage.InputLastNameField("Fatwana");
            checkoutInformationPage.InputPostalCodeField("60234");
            checkoutInformationPage.ClickBtnContinue();

            CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
            checkoutOverviewPage.ValidateOnItemDesc();
            checkoutOverviewPage.ValidateOnItemName();
            checkoutOverviewPage.ValidateOnItemPrice();
            checkoutOverviewPage.ValidateOnItemTotalLabel();
            checkoutOverviewPage.ValidateOnSummaryQTY();
            checkoutOverviewPage.ValidateOnTaxLabel();
            checkoutOverviewPage.ValidateOnValueLabel();
            checkoutOverviewPage.ClickBtnFinish();

            CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
            checkoutCompletePage.ValidateOnCheckoutCompletePage();
            checkoutCompletePage.ClickBtnBackToHome();
            homePage.validateOnHomePage();
        }
    }

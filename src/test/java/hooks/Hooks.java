package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.DriverManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless=new");
        //chromeOptions.addArguments("--disable-dev-shm-usage");
        //chromeOptions.addArguments("--no-sandbox");

        // Disable Chrome password manager popups
        Map<String, Object> prefs = new HashMap<>();
        // Disable semua jenis popup dan permission
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2); // 1 = allow, 2 = block
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        prefs.put("profile.default_content_setting_values.popups", 2);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
        prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
        prefs.put("profile.default_content_setting_values.plugins", 2);
        prefs.put("profile.default_content_setting_values.push_messaging", 2);

// Disable info bars
        prefs.put("useAutomationExtension", false);
        prefs.put("excludeSwitches", Collections.singletonList("enable-automation"));

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        //options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-default-apps");
        options.addArguments("--disable-background-timer-throttling");
        options.addArguments("--disable-renderer-backgrounding");
        options.addArguments("--disable-backgrounding-occluded-windows");
        options.addArguments("--disable-features=VizDisplayCompositor,TranslateUI");
        options.addArguments("--disable-features=PasswordChange");
        options.addArguments("--guest");
        options.addArguments("--user-data-dir=C:\\Temp\\chrome-empty-profile");


        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();  // or ChromeOptions if needed
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

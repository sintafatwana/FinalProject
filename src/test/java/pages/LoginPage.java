package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class LoginPage {

    WebDriver driver;

    public By UsernameField = By.id("user-name");
    public By PasswordField = By.id("password");
    public By LoginButton = By.id("login-button");
    public By ErrorMessage  = By.id("error");
    public By ProductTitle = By.id("item_4_title_link");

    public LoginPage() {
        this.driver = DriverManager.getDriver(); // properly fetch WebDriver
    }

    public void InputUsername(String username){
        driver.findElement(UsernameField).sendKeys(username);
    }

    public void InputPassword(String password){
        driver.findElement(PasswordField).sendKeys(password);
    }

    public void ClickLoginBtn(){
        driver.findElement(LoginButton).click();
    }
}
